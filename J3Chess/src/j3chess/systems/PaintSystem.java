package j3chess.systems;

import j3chess.J3ChessApp;
import j3chess.components.Paintable;
import j3chess.components.Position;
import j3chess.utility.Vector2d;

import artemis.Aspect;
import artemis.ComponentMapper;
import artemis.Entity;
import artemis.annotations.Mapper;
import artemis.systems.EntityProcessingSystem;

/**
 * entity processing system which draws every paintable entity at its
 * corresponding position.
 */
public class PaintSystem extends EntityProcessingSystem {

    /** @brief fast component mapper to retrieve paintable component */
    @Mapper
    private ComponentMapper<Paintable> mPaintableMapper;
    /** @brief fast component mapper to retrieve position component */
    @Mapper
    private ComponentMapper<Position> mPositionMapper;

    /**
     * @brief the paint system draws every paintable component, position
     * is optional.
     */
    public PaintSystem() {
        super(Aspect.getAspectForAll(Paintable.class));
    }

    /**
     * @brief draws a paintable component at its respective position.
     * @param entity the paintable entity to process
     * @see artemis.systems.EntityProcessingSystem#process(artemis.Entity)
     */
    @Override
    protected final void process(final Entity entity) {
        final Paintable paintable = mPaintableMapper.get(entity);
        final Position position = mPositionMapper.get(entity);

        final Vector2d drawOffset = paintable.getDrawOffset();

        Vector2d drawPosition = new Vector2d();

        if (position != null) {
            drawPosition = position.getField().getDrawPosition(668, 668);
        }

        J3ChessApp.getInstance().getDrawGraphics().drawImage(
                paintable.getImage(),
                (int) (drawPosition.x + drawOffset.x),
                (int) (drawPosition.y + drawOffset.y),
                null);
    }

}
