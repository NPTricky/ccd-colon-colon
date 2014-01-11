package j3chess.components;

import j3chess.Field;

import java.util.ArrayList;
import java.util.List;

import artemis.Component;

/**
 * entity component for all valid movements of a movable entity.
 */
public class ValidMovement extends Component {

    /** @brief the valid non capture move targets */
    private List<Field> mValidNonCaptureMoves;
    /** @brief the valid capturing move targets */
    private List<Field> mValidCaptureMoves;

    /**
     * @brief empty default constructor for valid movement component
     */
    public ValidMovement() {
        mValidNonCaptureMoves = new ArrayList<Field>();
        mValidCaptureMoves = new ArrayList<Field>();
    }

    /**
     * @brief getter for the mValidMoves member
     * @return the valid non capture move targets
     */
    public final List<Field> getValidNonCaptureMoves() {
        return mValidNonCaptureMoves;
    }

    /**
     * @brief setter for the mValidMoves member
     * @param validMoves the valid non capture move targets
     */
    public final void setValidNonCaptureMoves(final List<Field> validMoves) {
        this.mValidNonCaptureMoves = validMoves;
    }

    /**
     * @brief getter for the mValidCaptureMoves member
     * @return the valid capturing move targets
     */
    public final List<Field> getValidCaptureMoves() {
        return mValidCaptureMoves;
    }

    /**
     * @brief setter for the mValidCaptureMoves member
     * @param validCaptureMoves the valid capturing move targets
     */
    public final void setValidCaptureMoves(
            final List<Field> validCaptureMoves) {
        this.mValidCaptureMoves = validCaptureMoves;
    }

}
