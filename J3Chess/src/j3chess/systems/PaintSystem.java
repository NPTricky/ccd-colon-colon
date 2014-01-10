package j3chess.systems;

import j3chess.Field;
import j3chess.J3ChessApp;
import j3chess.components.Paintable;
import j3chess.components.Position;
import j3chess.utility.Vector2d;

import java.awt.Image;

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

        // Get image from the paintable component
        final Image image = paintable.getImage();

        // Draw the image if we have it
        if (image != null) {
            // Draw position is initially (0,0)
            Vector2d drawPos = new Vector2d();

            // If we have a position component, modify drawPos
            if (position != null) {
                Field f = position.getPosition();

                // If the position component has no field, don't draw
                if (f != null) {
                    // Set draw position
                    drawPos = position.getPosition().getDrawPosition(668, 668);
                } else {
                    return;
                }
            }

            Vector2d drawOffset = paintable.getDrawOffset();
            J3ChessApp.getInstance().getDrawGraphics().drawImage(image,
                    (int) (drawPos.x + drawOffset.x),
                    (int) (drawPos.y + drawOffset.y), null);
        }
    }

}
