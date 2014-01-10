package j3chess.systems;

import j3chess.FieldDirection;
import j3chess.J3ChessApp;
import j3chess.Field;
import j3chess.Motion;
import j3chess.MotionPattern;
import j3chess.PieceDirection;
import j3chess.components.Movement;
import j3chess.components.Position;
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
        final Position position = mPositionMapper.get(entity);
        final Movement movement = mMovementMapper.get(entity);

        for (final MotionPattern pattern : movement.getPatterns()) {

            // there are no motions in this pattern. something went wrong...
            if (pattern.getMotions().isEmpty()) {
                J3ChessApp.getLogger().error("no motions within a pattern");
                continue; // skip single iteration of outer for loop
            }

            // masked starting directions of the pattern
            final EnumSet<PieceDirection> directionsMasked = pattern
                    .getMotions() // get list of motions of the pattern
                    .get(0) // get first motion of the list
                    .getDirectionsMasked(movement.getMask()); // masked motion

            // the set of start directions is empty after masking
            if (directionsMasked.equals(EnumSet.noneOf(PieceDirection.class))) {
                J3ChessApp.getLogger().error("no directions left");
                continue; // skip single iteration of outer for loop
            }

            if (!pattern.getUnblockable()) {
                //recurseMotionList();
            } else {
                //recurseMotionListUnblockable();
            }
        }
    }

/* ------------------------------------------------------------------------- */
/* SINGLE MOTION                                                             */
/* ------------------------------------------------------------------------- */

    private void recurseMotionList(
            final Movement movement,
            final Position position,
            final Field currentField,
            final PieceDirection currentPieceDirection,
            final List<Motion> motionList,
            final int currentMotionIndex,
            final int currentStep) {

        // step - muss in eine richtung abgearbeitet werden
        // currentStep <= currentMotion.getSteps
        // solange das nicht erfüllt, motionIndex nicht erhöhen
        

        
        // motion - muss in alle seine richtungen (currentMotion.getDirections())
        // abgearbeitet werden, rekursiv
        
        final int motionMax = motionList.size();
        final Motion currentMotion = motionList.get(currentMotionIndex);

        final int stepMax = currentMotion.getSteps();

        final boolean inverse = movement.getCrossedCenter();

        final EnumSet<FieldDirection> currentFieldDirections =
                Helper.Direction.toFieldDirections(
                        currentPieceDirections,
                        inverse);

        for (final FieldDirection fieldDirection : currentFieldDirections) {
            Field targetField = currentField.getNeighbor(fieldDirection);
        }
    }

    // solange unblockable
    // kann nur bewegen/schlagen wenn motionIndex == motionPattern.getMotions()

/* ------------------------------------------------------------------------- */
/* HELPERS                                                                   */
/* ------------------------------------------------------------------------- */

}
