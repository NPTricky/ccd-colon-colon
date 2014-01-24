package j3chess.components;

import j3chess.Move;

import java.util.ArrayList;
import java.util.List;

import artemis.Component;

/**
 * entity component for all valid movements of a movable entity.
 */
public class ValidMovement extends Component {

    /** @brief the valid non capture moves */
    private List<Move> mValidNonCaptureMoves;
    /** @brief the valid capture moves */
    private List<Move> mValidCaptureMoves;

    /**
     * @brief empty default constructor for valid movement component
     */
    public ValidMovement() {
        mValidNonCaptureMoves = new ArrayList<Move>();
        mValidCaptureMoves = new ArrayList<Move>();
    }

    /**
     * @brief getter for the mValidNonCaptureMoves member
     * @return the valid non capture moves
     */
    public final List<Move> getValidNonCaptureMoves() {
        return mValidNonCaptureMoves;
    }

    /**
     * @brief setter for the mValidNonCaptureMoves member
     * @param validNonCaptureMoves the valid non capture moves
     */
    public final void setValidNonCaptureMoves(
            final List<Move> validNonCaptureMoves) {
        this.mValidNonCaptureMoves = validNonCaptureMoves;
    }

    /**
     * @brief getter for the mValidCaptureMoves member
     * @return the valid capturing moves
     */
    public final List<Move> getValidCaptureMoves() {
        return mValidCaptureMoves;
    }

    /**
     * @brief setter for the mValidCaptureMoves member
     * @param validCaptureMoves the valid capturing moves
     */
    public final void setValidCaptureMoves(
            final List<Move> validCaptureMoves) {
        this.mValidCaptureMoves = validCaptureMoves;
    }

}
