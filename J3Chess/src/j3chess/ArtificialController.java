package j3chess;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;

/**
 * an artificial player.
 */
public class ArtificialController implements PlayerController {

    /** @brief defines the importance of each piece type */
    private Map<PieceType, Integer> mPieceValue =
            new EnumMap<PieceType, Integer>(PieceType.class);

    /**
     * @brief default constructor for an artificial player controller
     */
    public ArtificialController() {
        // TODO Auto-generated constructor stub
        initialise();
    }

    @Override
    public final Move calculateMove() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @brief initializes the artificial controller with some context
     */
    private void initialise() {
        initialiseValue(10);
    }

    /**
     * @brief initializes the value of each piece type
     * @param value basic value of a piece to calculate relative
     *        values with it
     */
    private void initialiseValue(final int value) {
        for (PieceType pieceType : EnumSet.allOf(PieceType.class)) {
            Integer result;
            switch (pieceType) {
            case King:
                result = value * value * value;
                break;
            case Queen:
                result = value * value;
                break;
            case Rook:
                result = Math.round(value + (value * value / 2));
                break;
            case Bishop:
                result = Math.round(2 * value + (value / 2));
                break;
            case Knight:
                result = Math.round(2 * value + (value / 2));
                break;
            case Pawn:
                result = value;
                break;
            default:
                result = 0;
                break;
            }
            mPieceValue.put(pieceType, result);
        }

        String eol = System.getProperty("line.separator");
        String log = new String();
        for (PieceType pieceType : EnumSet.allOf(PieceType.class)) {
            log += "Value of " + pieceType.toString() + ": "
                   + mPieceValue.get(pieceType).toString() + eol;
        }
        J3ChessApp.getLogger().debug(log);
    }

}
