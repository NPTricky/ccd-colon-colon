package j3chess.systems;

import j3chess.J3ChessApp;
import j3chess.Field;
import j3chess.Motion;
import j3chess.MotionPattern;
import j3chess.PieceDirection;
import j3chess.components.Movement;
import j3chess.components.Position;
import j3chess.utility.Helper;

import java.util.EnumSet;

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

    /**
     * @brief the valid movement system interprets the movement capabilities of
     *        entities and creates a list of possible move targets
     */
    public ValidMovementSystem() {
        super(Aspect.getAspectForAll(Position.class, Movement.class));
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
        final Field position = mPositionMapper.get(entity).getPosition();
        final Movement movement = mMovementMapper.get(entity);

        // Set of PieceDirection e.g. Forward Only = [ 0 1 0 0 0 0 0 0 ]
        // [ Right, Forward,  ForwardRight, ForwardLeft,
        //   Left,  Backward, BackwardLeft, BackwardRight ]
        // EnumSet retains natural order...
        final EnumSet<PieceDirection> mask = movement.getMask();

        // update the logic
        for (final MotionPattern pattern : movement.getPatterns()) {

            // there are no motions in this pattern. something went wrong...
            if (pattern.getMotions().isEmpty()) {
                J3ChessApp.getLogger().warn(
                        "no motions within this motion pattern. this shouldn't "
                        + "happen because the pattern is invalid.");
                continue; // skip single iteration of outer for loop
            }

            // masked starting directions of the pattern
            final Motion motionStart = pattern
                    .getMotions() // get list of motions of the pattern
                    .get(0); // get first motion of the list
            final EnumSet<PieceDirection> directionsStart = motionStart
                    .getDirectionsMasked(mask); // get the masked directions of
                                                // the first motion

            // the set of start directions is empty after masking
            if (directionsStart.equals(EnumSet.noneOf(PieceDirection.class))) {
                J3ChessApp.getLogger().warn(
                        "after masking possible directions of the piece, there "
                        + "are no directions left. the movement pattern makes "
                        + "no sense and is invalid.");
                continue; // skip single iteration of outer for loop
            }

            // first step
            trySingleMotion(position, motionStart, directionsStart);

            // this pattern is a single step pattern and doesn't require further
            // processing.
            if (pattern.getMotions().size() == 1) {
                continue; // skip single iteration of outer for loop
            }

            for (int i = 1; i < pattern.getMotions().size(); i++) {
                final Motion motion = pattern.getMotions().get(i);
                trySingleMotion(position, motion);
            }

        }
    }

/* ------------------------------------------------------------------------- */

    /**
     * @param position current position of the entity about to move
     * @param motion the motion about to be applied to the entity
     */
    private void trySingleMotion(
            final Field position,
            final Motion motion) {

        // forward using a default parameter for the directions
        trySingleMotion(position, motion, motion.getDirections());
    }
    /**
     * @param position current position of the entity about to move
     * @param motion the motion about to be applied to the entity
     * @param directions the possible directions to move into
     */
    private void trySingleMotion(
            final Field position,
            final Motion motion,
            final EnumSet<PieceDirection> directions) {

        // get some context of the motion
        final boolean unblockable = motion.getUnblockable();
        final int steps = motion.getSteps();

        for (final PieceDirection pieceDirection : directions) {
            
        }
    }

/* ------------------------------------------------------------------------- */

    private void trySingleStep(final Field position) {

    }

/* ------------------------------------------------------------------------- */
/* HELPERS                                                                   */
/* ------------------------------------------------------------------------- */

    private Field getFieldInDirection(
            final Movement movement,
            final Field position,
            final int steps) {
        final boolean crossedCenter = movement.getCrossedCenter();
        return position;
    }

}
