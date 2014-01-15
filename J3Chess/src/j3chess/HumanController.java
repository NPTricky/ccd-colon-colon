package j3chess;

import j3chess.components.PieceContext;
import j3chess.components.Position;
import j3chess.components.ValidMovement;
import artemis.ComponentType;
import artemis.Entity;

/**
 * a human player.
 */
public class HumanController extends PlayerController {

    /**
     * @brief Create an instance of a HumanController for a given player.
     * @param player The player which owns the PlayerController.
     */
    public HumanController(final Player player) {
        super(player);
    }

    /**
     * @brief Notify the HumanController about a click on a field
     * @param clickedField
     *            The field the user clicked on
     */
    public final void notifyFieldClicked(final Field clickedField) {
        // Get the Game object
        Game game = J3ChessApp.getInstance().getGame();

        // Get piece on the field
        Entity clickedPiece = clickedField.getPiece();

        // Test wether the player clicked on his/her own piece
        if (clickedPiece != null) {
            // Get the piece's owning player
            Player owner = ((PieceContext) clickedPiece.getComponent(ComponentType.getTypeFor(PieceContext.class))).getPlayer();

            // Check the piece's owner
            if (owner == mPlayer) {
                // Clicked on my own piece --> select
                game.selectPiece(clickedPiece);
                return;
            }
        }

        // At this point, we know that the player didn't click on his own piece
        // We need to get the currently selected piece and check its valid moves
        Entity selectedPiece = game.getSelectedPiece();

        if (selectedPiece != null) {
            ValidMovement moves = (ValidMovement) selectedPiece.getComponent(ComponentType.getTypeFor(ValidMovement.class));

            if (moves.getValidNonCaptureMoves().contains(clickedField) || moves.getValidCaptureMoves().contains(clickedField)) {
                Field source = ((Position) selectedPiece.getComponent(ComponentType.getTypeFor(Position.class))).getField();
                game.doMove(new Move(MoveType.Common, source, clickedField));
                // Next player's turn
                game.nextPlayer();
            }
        }
    }
}
