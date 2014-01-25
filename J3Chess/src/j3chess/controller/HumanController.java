package j3chess.controller;

import j3chess.Field;
import j3chess.Game;
import j3chess.J3ChessApp;
import j3chess.components.PieceContext;
import j3chess.components.ValidMovement;
import j3chess.motion.Move;
import artemis.ComponentType;
import artemis.Entity;

/**
 * a human player.
 */
public class HumanController extends PlayerController {

    /**
     * @brief Create an instance of a HumanController for a given player.
     * @param player
     *            The player which owns the PlayerController.
     */
    public HumanController(final Player player) {
        super(player);
    }

    /**
     * @brief Notify the HumanController about a click on a field
     * @param clickedField
     *            The field the user clicked on
     */
    public final void notifyFieldClicked(final Field clickedField,
            final Game game) {
        if (game == null || clickedField == null) {
            return;
        }

        // Get piece on the field
        final Entity clickedPiece = clickedField.getPiece();

        // Test wether the player clicked on his/her own piece
        if (clickedPiece != null) {
            // Get the piece's owning player
            final Player owner = ((PieceContext) clickedPiece
                    .getComponent(ComponentType.getTypeFor(PieceContext.class)))
                    .getPlayer();

            // Check the piece's owner
            if (owner == mPlayer) {
                // Clicked on my own piece --> select
                game.selectPiece(clickedPiece);
                return;
            }
        }

        // At this point, we know that the player didn't click on his own piece
        // We need to get the currently selected piece and check its valid moves
        final Entity selectedPiece = game.getSelectedPiece();

        if (selectedPiece != null) {
            final ValidMovement validMovement = (ValidMovement) selectedPiece
                    .getComponent(ComponentType.getTypeFor(ValidMovement.class));

            J3ChessApp.getLogger().error(
                    "NON" + validMovement.getValidNonCaptureMoves().toString());
            J3ChessApp.getLogger().error(
                    "CAP" + validMovement.getValidCaptureMoves().toString());
            J3ChessApp.getLogger().error(
                    "ALL" + validMovement.getValidMoves().toString());

            for (final Move move : validMovement.getValidMoves()) {
                if (move.getTargetField() == clickedField) {
                    game.doMove(move);
                    return;
                }
            }
        }
    }
}
