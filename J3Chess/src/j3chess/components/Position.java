package j3chess.components;

import artemis.Component;
import j3chess.Field;

/**
 * entity component for a position on a chessboard.
 */
public class Position extends Component {

    /** @brief current position on the chessboard of an entity */
    private Field mField;

    /**
     * @brief empty default constructor for position component
     */
    public Position() {
    }

    /**
     * @brief constructor for position component
     * @param current current position
     */
    public Position(final Field current) {
        this.mField = current;
    }

    /**
     * @brief getter for the mPosition member
     * @return the current position
     */
    public final Field getField() {
        return this.mField;
    }

    /**
     * @brief setter for the mPosition member
     * @param current the current position
     */
    public final void setField(final Field current) {
        this.mField = current;
    }

}
