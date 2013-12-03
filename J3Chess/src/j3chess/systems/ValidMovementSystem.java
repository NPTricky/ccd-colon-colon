package j3chess.systems;

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
     * @brief
     * @param aspect
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
     * @param e
     *            the movabe entity to process
     * @see artemis.systems.EntityProcessingSystem#process(artemis.Entity)
     */
    @Override
    protected final void process(final Entity e) {
        // retrieve the relevant data from the mappers
        final Field position = mPositionMapper.get(e).getPosition();
        final Movement movement = mMovementMapper.get(e);
        final EnumSet<PieceDirection> mask = movement.getMask();
        //e.g. Forward Only
        // 0 1 0 0 0 0 0 0
        
        // @todo get field direction from piece direction solution

        // The EnumSet implements all methods in the Set interface, so
        // union == addAll(),
        // intersection == retainAll(),
        // difference == a combination of retainAll(), addAll() and removeAll().

        // update the logic
        for (final MotionPattern pattern : movement.getPatterns()) {
            // apply the mask on the motion pattern
            if (true) { continue; }
            for (final Motion motion : pattern.getMotions()) {
                // Field goal; // the target field that can be reached by this pattern
                final boolean unblockable = motion.getUnblockable();
                final int steps = motion.getSteps();
                final EnumSet<PieceDirection> group = motion.getDirections();

                
                // e.g. Diagonal -> ForwardRight, ForwardLeft, BackwardLeft, BackwardRight
                // 0 0 1 1 0 0 1 1
            }
        }
    }

}
