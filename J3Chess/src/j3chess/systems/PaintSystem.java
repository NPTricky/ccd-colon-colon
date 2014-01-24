package j3chess.systems;

import java.awt.Point;

import javax.swing.ImageIcon;

import j3chess.Game;
import j3chess.J3ChessApp;
import j3chess.components.Paintable;
import j3chess.components.Position;
import j3chess.utility.Vector2d;
import artemis.Aspect;
import artemis.ComponentMapper;
import artemis.Entity;
import artemis.annotations.Mapper;
import artemis.managers.GroupManager;
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

	/**
	 * @brief the paint system draws every paintable component, position is
	 *        optional.
	 */
	public PaintSystem() {
		super(Aspect.getAspectForAll(Paintable.class));
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

		J3ChessApp
				.getInstance()
				.getDrawGraphics()
				.drawImage(
						paintable.getImage(),
						Math.round(drawPosition.x + paintable.getDrawOffset().x),
						Math.round(drawPosition.y + paintable.getDrawOffset().y),
						null);

		if (J3ChessApp.getInstance().getGame().getSelectedPiece() == entity) {
			J3ChessApp
					.getInstance()
					.getDrawGraphics()
					.drawImage(
							SELECTION_OVERLAY.getImage(),
							Math.round(drawPosition.x - SELECTION_OVERLAY.getIconWidth() / 2.0f),
							Math.round(drawPosition.y - SELECTION_OVERLAY.getIconHeight() / 2.0f),
							null);
		}
	}

	/** @brief size of the draw panel */
	private Point mDrawPanelSize = new Point(668, 668);

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
