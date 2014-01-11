package j3chess.systems;

import j3chess.FieldDirection;
import j3chess.J3ChessApp;
import j3chess.Field;
import j3chess.Motion;
import j3chess.MotionPattern;
import j3chess.PieceDirection;
import j3chess.Player;
import j3chess.components.Movement;
import j3chess.components.Position;
import j3chess.components.ValidMovement;
import j3chess.utility.Helper;

import java.util.EnumSet;
import java.util.List;

import artemis.Aspect;
import artemis.ComponentMapper;
import artemis.Entity;
import artemis.annotations.Mapper;
import artemis.systems.EntityProcessingSystem;

/**
 * entity processing system which checks for every possible move of an entity.
 */
public class ValidMovementSystem extends EntityProcessingSystem {

    /** @brief fast component mapper to retrieve position component */
    @Mapper
    private ComponentMapper<Position> mPositionMapper;
    /** @brief fast component mapper to retrieve movement component */
    @Mapper
    private ComponentMapper<Movement> mMovementMapper;
    /** @brief fast component mapper to retrieve valid movement component */
    @Mapper
    private ComponentMapper<ValidMovement> mValidMovementMapper;

    /**
     * @brief the valid movement system interprets the movement capabilities of
     *        entities and creates a list of possible move targets
     */
    public ValidMovementSystem() {
        super(Aspect.getAspectForAll(
                Position.class,
                Movement.class,
                ValidMovement.class));
    }

    /* @see artemis.EntitySystem#initialize() */
    @Override
    protected final void initialize() {
        super.initialize();
    }

    /**
     * @brief checks for valid moves of an movable entity.
     * @param entity the movable entity to process
     * @see artemis.systems.EntityProcessingSystem#process(artemis.Entity)
     */
    @Override
    protected final void process(final Entity entity) {

        // retrieve relevant components from the entity
        mCurrentPosition = mPositionMapper.get(entity);
        mCurrentMovement = mMovementMapper.get(entity);
        mCurrentValidMovement = mValidMovementMapper.get(entity);

        // for each motion pattern do...
        for (final MotionPattern pattern : mCurrentMovement.getPatterns()) {

            // there are no motions in this pattern. skip loop.
            if (pattern.getMotions().isEmpty()) {
                J3ChessApp.getLogger().error("no motions within this pattern");
                continue;
            }

            // retrieve valid moves from the motion pattern recursively
            recurseMotionList(
                    mCurrentPosition.getPosition(),
                    null,
                    pattern,
                    0,
                    0,
                    mCurrentMovement.getCrossedCenter());
        }
    }

/* ------------------------------------------------------------------------- */
/* Entity-Related Members                                                    */
/* ------------------------------------------------------------------------- */

    /** @brief position component of the currently processed entity */
    private Position mCurrentPosition;
    /** @brief movement component of the currently processed entity */
    private Movement mCurrentMovement;
    /** @brief valid movement component of the currently processed entity */
    private ValidMovement mCurrentValidMovement;

/* ------------------------------------------------------------------------- */
/* Recursion                                                                 */
/* ------------------------------------------------------------------------- */

    /**
     * @brief Recursively interprets a motion pattern and saves the results to
     *        a ValidMovement component
     * @param currentField the current field from where to recurse
     * @param lastPieceDirection the piece direction evaluated in the last
     *                           iteration
     * @param currentMotionPattern the motion pattern to evaluate
     * @param currentMotionIndex the current index of the motion in the motion
     *                           pattern to evaluate
     * @param currentStep the current step in the current motion
     * @param currentCrossedCenter the current status whether we crossed the
     *                             center or not while evaluating the motion
     *                             pattern
     */
    private void recurseMotionList(
            final Field currentField,
            final PieceDirection lastPieceDirection,
            final MotionPattern currentMotionPattern,
            final int currentMotionIndex,
            final int currentStep,
            final boolean currentCrossedCenter) {

        ////////////////
        // Basic Data //
        ////////////////

        // get the current motion list from the pattern
        final List<Motion> currentMotionList =
                currentMotionPattern.getMotions();

        // get the current motion from the motion list
        final Motion currentMotion =
                currentMotionList.get(currentMotionIndex);

        /////////////////////////
        // Context Information //
        /////////////////////////

        // the current motion pattern is unblockable (a jump)
        boolean isUnblockable = currentMotionPattern.getUnblockable();

        // the current motion is infinite in it's directions
        boolean isInfinite =
                (currentMotion.getSteps() == 0);

        // the current step is the last step of the current motion
        boolean isLastStepOfMotion =
                !(currentStep < currentMotion.getSteps());

        // whether we already reached the end of the current motion
        boolean isMotionEnd =
                isEndOfMotion(isInfinite, isLastStepOfMotion);

        // whether the current motion is the last motion of the motion list
        boolean isLastMotionOfList =
                !(currentMotionIndex < currentMotionList.size());

        EnumSet<PieceDirection> possibleDirections = getPossibleDirections(
                currentMotion,
                lastPieceDirection,
                currentMotionIndex,
                currentStep);

        ///////////
        // Logic //
        ///////////

        // do recursive step for every possible direction
        for (PieceDirection currentPieceDirection : possibleDirections) {

            final FieldDirection currentFieldDirection =
                    Helper.Direction.toFieldDirection(
                            currentPieceDirection,
                            currentCrossedCenter);

            Field nextField = currentField.getNeighbor(currentFieldDirection);

            /**
             * | current | crossing | (desired) |
             * |    0    |    0     |     0     |
             * |    0    |    1     |     1     |
             * |    1    |    0     |     1     |
             * |    1    |    1     |     0     |
             *
             * (current ^ crossing) == result
             */
            boolean nextCrossedCenter = currentCrossedCenter ^ currentField
                    .getWhetherCrossingCenter(currentFieldDirection);

/* ------------------------------------------------------------------------- */

            if (isBlocked(nextField)) {
                if (isCapturable(myPlayer(), nextField)) {
                    mCurrentValidMovement.getValidCaptureMoves().add(nextField);
                }
                // a blocked motion list doesn't require further processing
                continue;
            }

            mCurrentValidMovement.getValidNonCaptureMoves().add(nextField);

/* ------------------------------------------------------------------------- */

            int nextMotionIndex = -1;
            int nextStep = -1;

            if (!isMotionEnd) {
                // same motion and next step
                nextMotionIndex = currentMotionIndex;
                nextStep = currentStep + 1;
            }

            if (isMotionEnd && !isLastMotionOfList) {
                // next motion and first step
                nextMotionIndex = currentMotionIndex + 1;
                nextStep = 0; // first step of new motion
            }

            if (isMotionEnd && isLastMotionOfList) {
                // processing of the whole motion list is done
                continue;
            }

            if (nextMotionIndex == -1 || nextStep == -1) {
                J3ChessApp.getLogger().error(
                        "logical mistake in valid movement system");
                continue;
            }

/* ------------------------------------------------------------------------- */

            recurseMotionList(
                    nextField,
                    currentPieceDirection,
                    currentMotionPattern,
                    nextMotionIndex,
                    nextStep,
                    nextCrossedCenter);
        }

        return;
    }

/* ------------------------------------------------------------------------- */
/* Other Methods                                                             */
/* ------------------------------------------------------------------------- */

    /**
     * @brief determines whether the motion already reached it's end
     * @param isInfinite the current motion is infinite
     * @param isLastStepOfMotion it is the last step of the current motion
     * @return whether the motion already reached it's end
     */
    private boolean isEndOfMotion(
            final boolean isInfinite,
            final boolean isLastStepOfMotion) {
        /**
         * problem: !(countStep < maxStep) is always true if the motion is
         * infinite because countStep [0..n] is never smaller than 0. thus
         * infinite movement is always a last step.
         *
         * solution: look at this binary table
         *
         *                              (DESIRED)
         * | isInfinite | isLastStep | isMotionEnd |
         * |     0      |     0      |      0      |
         * |     0      |     1      |      1      |
         * |     1      |    (1)     |      0      |
         * |     1      |     1      |      0      |
         *
         * (1) is dependent on the value of isInfinite. isLastStep is 1 if
         * isInfinite is 1. Thus a solution with XOR is correct.
         *
         * XOR of isInfinite and isLastStep defines isMotionEnd, whether the
         * end of the current motion is reached.
         */
        return (isInfinite ^ isLastStepOfMotion);
    }

    /**
     * @brief check whether the target field is blocked
     * @param targetField the target field about to be checked
     * @return whether the target field is blocked
     */
    private boolean isBlocked(final Field targetField) {
        return (targetField.getPiece() != null);
    }

    /**
     * @brief check whether the target field is capturable by the player. the
     * method assumes there is a piece on the target field.
     * @param player the player trying to capture the target field
     * @param targetField the target field the player is trying to capture
     * @return whether the target field is capturable
     */
    private boolean isCapturable(final Player player, final Field targetField) {
        return (player != targetField.getPiece().getPlayer());
    }

    /**
     * @brief evaluate the possible directions of a motion
     * @param motion the motion to evaluate
     * @param lastPieceDirection the direction of the preceding motion
     * @param currentMotionIndex the index of the current motion required for
     *                           the first motion of a list detection
     * @param currentStep the current step count
     * @return the possible directions of a motion
     */
    private EnumSet<PieceDirection> getPossibleDirections(
            final Motion motion,
            final PieceDirection lastPieceDirection,
            final int currentMotionIndex,
            final int currentStep) {

        EnumSet<PieceDirection> directions =
                EnumSet.noneOf(PieceDirection.class);

        // the current step is the first step of the current motion
        boolean isFirstStep = (currentStep == 0);
        // the current step is the first step of the current motion pattern
        boolean isFirstMotionOfList = (currentMotionIndex == 0 && isFirstStep);

        if (isFirstMotionOfList) {
            // mask the starting directions of the motion pattern
            directions = motion.getDirectionsMasked(mCurrentMovement.getMask());
        }

        if (isFirstStep) {
            // retrieve the possible directions from the motion
            directions = motion.getDirections();
        }

        // go on into the same direction as in the step before...
        directions = EnumSet.of(lastPieceDirection);

        if (directions.equals(EnumSet.noneOf(PieceDirection.class))) {
            // the set of directions is empty
            J3ChessApp.getLogger().error("no directions");
        }

        return directions;
    }

/* ------------------------------------------------------------------------- */
/* Convenience                                                               */
/* ------------------------------------------------------------------------- */

        /**
         * @brief getter for the player of the currently processed entity.
         * for convenience purposes only.
         * @return the player of the currently processed entity
         */
        private Player myPlayer() {
            return mCurrentPosition
                    .getPosition()
                    .getPiece()
                    .getPlayer();
        }

}
