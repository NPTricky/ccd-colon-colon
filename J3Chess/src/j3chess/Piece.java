package j3chess;

import javax.swing.ImageIcon;

import org.apache.logging.log4j.Level;

import j3chess.components.Paintable;
import j3chess.components.PieceStatus;
import j3chess.components.Position;
import j3chess.components.Selectable;
import j3chess.components.ValidMovement;
import j3chess.utility.Vector2d;
import artemis.ComponentType;
import artemis.Entity;

/**
 * abstract base class for the construction of entities of type piece.
 */
public abstract class Piece {

	/** @brief the entity system to create entities into */
	private final EntitySystem mEntitySystem;
	/** @brief the type of the piece */
	private final PieceType mType;
	/** @brief the entity of the piece */
	private Entity mPiece;

	/**
	 * @brief constructor of the piece class
	 * @param system
	 *            the entity system to create entities into
	 * @param type
	 *            the type of the piece
	 */
	public Piece(final EntitySystem system, final PieceType type) {
		this.mEntitySystem = system;
		this.mType = type;
		initialize();
	}

	/**
	 * @brief do one time processing in this method...
	 */
	private void initialize() {
		J3ChessApp.getLogger().debug("Building " + mType.toString());

		// initialize the entity
		mPiece = mEntitySystem.getWorld().createEntity();

		// initialize the default components for an entity of type piece
		mPiece.addComponent(new Position(),
				ComponentType.getTypeFor(Position.class));
		mPiece.addComponent(new Selectable(),
				ComponentType.getTypeFor(Selectable.class));
		mPiece.addComponent(new Paintable(),
				ComponentType.getTypeFor(Paintable.class));
		mPiece.addComponent(new PieceStatus(),
				ComponentType.getTypeFor(PieceStatus.class));
        mPiece.addComponent(new ValidMovement(),
                ComponentType.getTypeFor(ValidMovement.class));
		getPieceStatusComponent().setPieceType(mType);
	}

	/**
	 * @brief Sets the image and the offset to draw the Piece with
	 * @param Image
	 *            to be set
	 */
	protected void setPieceImage() {
		Paintable paint = getPaintableComponent();
		
		// Construct image path
		String imagePath = J3ChessApp.RESOURCEPATH + "pieces_"
				+ getPieceStatusComponent().getPlayer().name().toLowerCase() + "/"
				+ getPieceStatusComponent().getPieceType().name().toLowerCase()
				+ ".png";
		ImageIcon image = new ImageIcon(imagePath);
		
		J3ChessApp.getLogger().log(Level.INFO, imagePath);
		
		// Set image
		paint.setImage(image);
		paint.setDrawOffset(new Vector2d(-image.getImage().getWidth(null) / 2,
				-image.getImage().getHeight(null) / 2));
	}

	/**
	 * @brief Get the Position component.
	 * @return Position component
	 */
	protected Position getPositionComponent() {
		return (Position) mPiece.getComponent(ComponentType
				.getTypeFor(Position.class));
	}

	/**
	 * @brief Get the Selectable component.
	 * @return Selectable component
	 */
	protected Selectable getSelectableComponent() {
		return (Selectable) mPiece.getComponent(ComponentType
				.getTypeFor(Selectable.class));
	}

	/**
	 * @brief Get the Paintable component.
	 * @return Paintable component
	 */
	protected Paintable getPaintableComponent() {
		return (Paintable) mPiece.getComponent(ComponentType
				.getTypeFor(Paintable.class));
	}

	/**
	 * @brief Get the PieceStatus component.
	 * @return PieceStatus component
	 */
	protected PieceStatus getPieceStatusComponent() {
		return (PieceStatus) mPiece.getComponent(ComponentType
				.getTypeFor(PieceStatus.class));
	}

	/**
	 * @brief do subclass level processing in this method...
	 */
	protected abstract void construct();

	/**
	 * @brief getter for the mPiece member
	 * @return the piece entity
	 */
	public final Entity getEntity() {
		return mPiece;
	}

}
