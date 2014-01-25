package j3chess.systems;

import j3chess.J3ChessApp;
import j3chess.J3ChessView;
import j3chess.Move;
import j3chess.components.Paintable;
import j3chess.components.Position;
import j3chess.components.ValidMovement;
import j3chess.utility.Vector2d;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.ImageIcon;

import artemis.Aspect;
import artemis.ComponentMapper;
import artemis.ComponentType;
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

    static final ImageIcon SELECTION_OVERLAY = new ImageIcon(
            J3ChessApp.RESOURCEPATH + "selection.png");

    static final ImageIcon MOVE_OVERLAY = new ImageIcon(J3ChessApp.RESOURCEPATH
            + "move.png");

    static final ImageIcon CAPTURE_OVERLAY = new ImageIcon(
            J3ChessApp.RESOURCEPATH + "capture.png");

    /**
     * @brief the paint system draws every paintable component, position is
     *        optional.
     */
    public PaintSystem() {
        super(Aspect.getAspectForAll(Paintable.class));
    }

    protected void begin() {
        // Clear rect
        final Graphics2D graphics = J3ChessApp.getInstance().getDrawGraphics();
        graphics.setColor(J3ChessApp.getInstance().getMainView().
                getFrame().getBackground());
        graphics.fillRect(0, 0, mDrawPanelSize.x, mDrawPanelSize.y);
    }

    /**
     * @brief draws a paintable component at its respective position.
     * @param entity
     *            the paintable entity to process
     * @see artemis.systems.EntityProcessingSystem#process(artemis.Entity)
     */
    @Override
    protected final void process(final Entity entity) {
        final Paintable paintable = mPaintableMapper.get(entity);
        final Position position = mPositionMapper.get(entity);

        Vector2d drawPosition = new Vector2d();

        if (position == null) {
            drawPosition.x = Math.round(mDrawPanelSize.x * 0.5f);
            drawPosition.y = Math.round(mDrawPanelSize.y * 0.5f);
        } else {
            drawPosition = position.getField().getDrawPosition(
                    mDrawPanelSize.x, mDrawPanelSize.y);
        }

        final Graphics2D graphics = J3ChessApp.getInstance()
                .getDrawGraphics();
        graphics.drawImage(
                        paintable.getImage(),
                        Math.round(drawPosition.x + paintable.getDrawOffset().x
                                + mDrawOffset.x),
                        Math.round(drawPosition.y + paintable.getDrawOffset().y
                                + mDrawOffset.y),
                        null);
        }

    /** @brief size of the draw panel */
    private final Point mDrawPanelSize = new Point(J3ChessView.CHESSBOARDWIDTH,
            J3ChessView.CHESSBOARDHEIGHT);

    /** @brief offset used for drawing on the chessboard */
    private final Vector2d mDrawOffset = new Vector2d((J3ChessApp.getInstance().getView().getDrawPanelWidth() - mDrawPanelSize.x) / 2.0f,
            (J3ChessApp.getInstance().getView().getDrawPanelHight() - mDrawPanelSize.x) / 2.0f);

    /**
     * @brief setter for the mDrawPanelSize member
     * @param width
     *            width of the draw panel
     * @param height
     *            height of the draw panel
     */
    public final void setDrawPanelSize(final int width, final int height) {
        this.mDrawPanelSize.setLocation(width, height);
    }

}
