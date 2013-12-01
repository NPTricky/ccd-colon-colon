package j3chess;

import j3chess.utility.Vector2d;
import java.util.EnumMap;


/**
 * Represents a field on the chessboard.
 */
public class Field {

    /** @brief Position where the field is drawn */
    private Vector2d mDrawPos;

    /** @brief Neighbors of this field, some might be null. */
    EnumMap<FieldDirection, Field> mNeighbors =
            new EnumMap<FieldDirection, Field>(FieldDirection.class);

    public Integer mCircle;
    public int mColumn;
    
    public char[] columnNames = {'A', 'B', 'C', 'D', 'E', 'F', 'G',
            'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
            'S', 'T', 'U', 'V', 'W', 'X'};

    /**
     * Creates an instance of a Field on the chessboard.
     * @param circle Inner / outer coordinate of the field from in the
     * range [0..5] where 0 is the outermost circle
     * @param column Coordinate around the circle, increasing CCW and
     * starting with the white player
     */
    public Field(final int circle, final int column) {
        mDrawPos = new Vector2d();
        mCircle = circle;
        mColumn = column;
    }

    /**
     * Returns a neighbor of this field in the specified direction.
     * @param dir Direction of the neighbor
     * @return Reference to the neighboring field
     */
    public final Field getNeighbor(final FieldDirection dir) {
        return mNeighbors.get(dir);
    }

    /**
     * Sets the neighbor of this field in the specified direction.
     * @param dir Direction of the neighbor
     * @param field Neighbor to be set
     */
    public final void setNeighbor(final FieldDirection dir,
            final Field field) {
        mNeighbors.put(dir, field);
    }

    /** @brief prints the field with his Neighbors - just for debugging */
    public final void print() {
        J3ChessApp.getLogger().debug(this.toString()
            + " CW:" + getNeighbor(FieldDirection.Clockwise)
            + " CCW:" + getNeighbor(FieldDirection.CounterClockwise)
            + " Inner:" + getNeighbor(FieldDirection.In)
            + " Outer:" + getNeighbor(FieldDirection.Out)
            + " CW Outer:" + getNeighbor(FieldDirection.OutClockwise)
            + " CCW Outer:" + getNeighbor(FieldDirection.OutCounterClockwise)
            + " CW Inner:" + getNeighbor(FieldDirection.InClockwise)
            + " CCW Inner:" + getNeighbor(FieldDirection.InCounterClockwise));
    }

    /**
     * Converts the field an easily readable string.
     * @return String [A-X][0-5]
     */
    public final String toString() {
        return columnNames[mColumn] + mCircle.toString();
    }


    /**
     * @brief Sets the position where the field (i.e. its piece) will be drawn.
     * @param vec    Cartesian 2-dimensional vector specifying the draw position
     */
    public final void setDrawPosition(final Vector2d vec) {
        mDrawPos.x = vec.x;
        mDrawPos.y = vec.y;
    }



}
