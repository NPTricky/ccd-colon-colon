package j3chess.systems;

import java.util.EnumSet;
import java.util.List;

import artemis.Aspect;
import artemis.ComponentMapper;
import artemis.Entity;
import artemis.annotations.Mapper;
import artemis.systems.EntityProcessingSystem;
import j3chess.Field;
import j3chess.Motion;
import j3chess.MotionPattern;
import j3chess.PieceDirection;
import j3chess.components.Position;
import j3chess.components.Movement;

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

    /**
     * @brief checks for valid moves of an movable entity.
     * @param e the movabe entity to process
     * @see artemis.systems.EntityProcessingSystem#process(artemis.Entity)
     */
    @Override
    protected final void process(final Entity e) {
        // retrieve the relevant data from the mappers
        Field position = mPositionMapper.get(e).getPosition();
        Movement movement = mMovementMapper.get(e);
        EnumSet<PieceDirection> mask = movement.getMask();

        // update the logic
        for (MotionPattern pattern : movement.getMoves()) {
            Field goal; // the target field that can be reached
            
            for (Motion motion : pattern.getMotions()) {
                
            }
        }
    }

}
