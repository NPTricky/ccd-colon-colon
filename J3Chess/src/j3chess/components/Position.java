package j3chess.components;

import artemis.Component;
import j3chess.Field;

/**
 * entity component for a current and last position on a chessboard.
 */
public class Position extends Component {

    /** @brief last position on the chessboard of an entity */
    private Field mLastPosition;
    /** @brief current position on the chessboard of an entity */
    private Field mPosition;

    /**
     * @brief empty default constructor for position component
     */
    public Position() {
    }

    /**
     * @brief constructor for position component
     * @param last last position
     * @param current current position
     */
    public Position(final Field last, final Field current) {
        this.mLastPosition = last;
        this.mPosition = current;
    }

    /**
     * @brief getter for the mLastPosition member
     * @return the last position
     */
    public final Field getLastPosition() {
        return this.mLastPosition;
    }

    /**
     * @brief setter for the mLastPosition member
     * @param last the last position
     */
    public final void setLastPosition(final Field last) {
        this.mLastPosition = last;
    }

    /**
     * @brief getter for the mPosition member
     * @return the current position
     */
    public final Field getPosition() {
        return this.mPosition;
    }

    /**
     * @brief setter for the mPosition member
     * @param current the current position
     */
    public final void setPosition(final Field current) {
        this.mPosition = current;
    }

}
