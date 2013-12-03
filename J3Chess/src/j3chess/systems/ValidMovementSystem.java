package j3chess.systems;

import j3chess.Field;
import j3chess.Motion;
import j3chess.MotionPattern;
import j3chess.PieceDirection;
import j3chess.components.Movement;
import j3chess.components.Position;

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
        // retrieve the relevant data from the mappers
        final Field position = mPositionMapper.get(entity).getPosition();
        final Movement movement = mMovementMapper.get(entity);
        final EnumSet<PieceDirection> mask = movement.getMask();
        // e.g. Forward Only = [ 0 1 0 0 0 0 0 0 ]

        // update the logic
        for (final MotionPattern pattern : movement.getPatterns()) {

            final EnumSet<PieceDirection> start = pattern
                    .getMotions() // get list of motions of the pattern
                    .get(0) // get first motion of the list
                    .getDirectionsMasked(mask); // get the masked directions of
                                                // the first motion

            // direction is empty...
            if (start.equals(EnumSet.noneOf(PieceDirection.class))) {
                // ... and thus the pattern will be skipped
                continue;
            }

            for (final Motion motion : pattern.getMotions()) {
                final boolean unblockable = motion.getUnblockable();
                final int steps = motion.getSteps();
                final EnumSet<PieceDirection> group = motion.getDirections();
                // e.g. Diagonal = [ 0 0 1 1 0 0 1 1 ]
            }

        }
    }

}
