package j3chess.components;

import j3chess.controller.Player;
import j3chess.pieces.PieceType;
import artemis.Component;

/**
 * entity component for piece context information.
 */
public class PieceContext extends Component {

    /** @brief the owning player of the piece */
    private Player mPlayer;
    /** @brief type of the piece */
    private PieceType mPieceType;

    /**
     * @brief empty default constructor for piece context component.
     */
    public PieceContext() {
    }

    /**
     * @brief constructor for piece context component
     * @param player
     *            the owning player of the piece
     * @param pieceType
     *            type of the piece
     */
    public PieceContext(final Player player, final PieceType pieceType) {
        this.mPlayer = player;
        this.mPieceType = pieceType;
    }

    /**
     * @brief getter for the mPlayer member
     * @return the owning player of the piece
     */
    public final Player getPlayer() {
        return mPlayer;
    }

    /**
     * @brief setter for the mPlayer member
     * @param player
     *            the owning player of the piece
     */
    public final void setPlayer(final Player player) {
        this.mPlayer = player;
    }

    /**
     * @brief getter for the mPieceType member
     * @return type of the piece
     */
    public final PieceType getPieceType() {
        return mPieceType;
    }

    /**
     * @brief setter for the mPieceType member
     * @param pieceType
     *            type of the piece
     */
    public final void setPieceType(final PieceType pieceType) {
        this.mPieceType = pieceType;
    }
}
