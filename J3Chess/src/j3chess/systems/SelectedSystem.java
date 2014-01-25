package j3chess.systems;

import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.ImageIcon;

import j3chess.J3ChessApp;
import j3chess.J3ChessView;
import j3chess.Move;
import j3chess.components.Position;
import j3chess.components.Selection;
import j3chess.components.ValidMovement;
import j3chess.utility.Vector2d;
import artemis.Aspect;
import artemis.ComponentMapper;
import artemis.ComponentType;
import artemis.Entity;
import artemis.annotations.Mapper;
import artemis.systems.EntityProcessingSystem;

public class SelectedSystem extends EntityProcessingSystem {
    /** @brief fast component mapper to retrieve position component */
    @Mapper
    private ComponentMapper<Position> mPositionMapper;

    static final ImageIcon SELECTION_OVERLAY = new ImageIcon(
            J3ChessApp.RESOURCEPATH + "selection.png");

    static final ImageIcon MOVE_OVERLAY = new ImageIcon(J3ChessApp.RESOURCEPATH
            + "move.png");

    static final ImageIcon CAPTURE_OVERLAY = new ImageIcon(
            J3ChessApp.RESOURCEPATH + "capture.png");

    /** @brief size of the draw panel */
    private final Point mDrawPanelSize = new Point(J3ChessView.CHESSBOARDWIDTH,
            J3ChessView.CHESSBOARDHEIGHT);

    /** @brief offset used for drawing on the chessboard */
    private final Vector2d mDrawOffset;

	public SelectedSystem(J3ChessView view) {
		super(Aspect.getAspectForAll(Selection.class, Position.class));
		mDrawOffset = new Vector2d((view.getDrawPanelWidth() - mDrawPanelSize.x) / 2.0f,
				(view.getDrawPanelHeight() - mDrawPanelSize.x) / 2.0f);
	}


	@Override
	protected void process(Entity e) {
		J3ChessApp.getLogger().debug("=== PROCESSING SELECTION ===");
        Position position = mPositionMapper.get(e);

        final Graphics2D graphics = J3ChessApp.getInstance()
                .getDrawGraphics();

        Vector2d drawPosition = position.getField().getDrawPosition(
                    mDrawPanelSize.x, mDrawPanelSize.y);

        // Draw selection ring
        graphics.drawImage(
                SELECTION_OVERLAY.getImage(),
                Math.round(drawPosition.x
                        - SELECTION_OVERLAY.getIconWidth() / 2.0f
                        + mDrawOffset.x),
                Math.round(drawPosition.y
                        - SELECTION_OVERLAY.getIconHeight() / 2.0f
                        + mDrawOffset.y), null);

        // Draw valid moves
        final ValidMovement moves = (ValidMovement) e.getComponent(ComponentType.getTypeFor(ValidMovement.class));

        if (moves != null) {
	        for (final Move move : moves.getValidNonCaptureMoves()) {
	            final Vector2d fDrawPosition = move.getTargetField()
	                    .getDrawPosition(mDrawPanelSize.x, mDrawPanelSize.y);
	            graphics.drawImage(
	                    MOVE_OVERLAY.getImage(),
	                    Math.round(fDrawPosition.x - MOVE_OVERLAY.getIconWidth() / 2.0f
	                            + mDrawOffset.x),
	                    Math.round(fDrawPosition.y - MOVE_OVERLAY.getIconHeight() / 2.0f
	                            + mDrawOffset.y), null);
	        }

	        for (final Move move : moves.getValidCaptureMoves()) {
	            final Vector2d fDrawPosition = move.getTargetField()
	                    .getDrawPosition(mDrawPanelSize.x, mDrawPanelSize.y);
	            graphics.drawImage(
	                    CAPTURE_OVERLAY.getImage(),
	                    Math.round(fDrawPosition.x - MOVE_OVERLAY.getIconWidth() / 2.0f
	                            + mDrawOffset.x),
	                    Math.round(fDrawPosition.y - MOVE_OVERLAY.getIconHeight() / 2.0f
	                            + mDrawOffset.y), null);
	        }
        }
	}
}
