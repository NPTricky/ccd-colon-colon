package j3chess;

import j3chess.utility.Vector2d;
import java.util.ArrayList;

/**
 * Represents the game's board. Keeps track of all the fields an pieces
 * in the game.
 */
public class Chessboard {

    /* ##################################################################
     * global Values
     * #################################################################
     * */

     /** Number of circles the chessboard consists of. */
    public static final int NUMBEROFCIRCLES = 6;
    /** Number of columns the chessboard consists of. */
    public static final int NUMBEROFCOLUMNS = 24;
    /** Size of the center field, fraction of the total diameter. */
    public static final float CENTERSIZE = 0.2f;

    /** 6x24 array of all fields in the game. */
    private Field[][] mFields;

    /** Edges that define moats, i.e. edges unpassable for pawns. */
    private final ArrayList<Edge> mMoats = new ArrayList<Edge>();
    /** Edges that define the creeks, i.e. connected but unpassable. */
    private final ArrayList<Edge> mCreeks = new ArrayList<Edge>();


    /* ##################################################################
     * constructor
     * #################################################################
     * */

    /**
     * @brief Creates new instance of the Chessboard class.
     */
    public Chessboard() {
        // Create all logical fields
        createFields();
        //set the Neighbors of every Field
        setNeighbors();

        //create and added Moats
        defineMoats();

        //create Creeks
        defineCreeks();

        //print all Fields - just for debugging
        printAllFields();

        // Calculate the fields' 2D drawing positions
        calculateDrawPositions(CENTERSIZE);

    }

    /* ##################################################################
     * creeks
     * #################################################################
     * */
    /**
     * @Brief Checks if an edge is a creek
     * @param leftField leftField of the Edge
     * @param rightField right Field of the Edge
     * @return true if the Field is an Edge
     */
    public final Boolean isCreek(final Field leftField,
            final Field rightField) {
        final Edge tmpEdge = new Edge(leftField, rightField);
        for (int i = 0; i < mCreeks.size(); i++) {
            if (mCreeks.get(i).isEqual(tmpEdge)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @Brief defines where the creeks are
     */
    private void defineCreeks() {
        addCreek(mFields[23][1], mFields[0][1]);
        addCreek(mFields[23][2], mFields[0][2]);
        addCreek(mFields[7][1],  mFields[8][1]);
        addCreek(mFields[7][2],  mFields[8][2]);
        addCreek(mFields[15][1], mFields[16][1]);
        addCreek(mFields[15][2], mFields[16][2]);
    }

    /**
     * @Brief add a Creek to an Edge of the cheesboard
     * @param leftField left Field of the Creek
     * @param rightField right Field of the Creek
     */
    public final void addCreek(final Field leftField, final Field rightField) {
        if (leftField == rightField.getNeighbor(FieldDirection.Clockwise)) {
            mCreeks.add(new Edge(leftField, rightField));
        } else {
            // TODO throw exception?
            J3ChessApp.getLogger().error("ERROR Can't create Creek,"
                    + "leftField is not a leftNeighbor of rightField");
        }
    }


    /* ##################################################################
     * Moats
     * #################################################################
     * */
    /**
     * @Brief defines where the moats are
     */
    private void defineMoats() {
        addMoat(mFields[23][0], mFields[0][0]);
        addMoat(mFields[7][0],  mFields[8][0]);
        addMoat(mFields[15][0], mFields[16][0]);
    }

    /**
     * @ Brief NOT IMPLEMENTET - REMOVES a MOAT
     * @param leftField left field of the MOAT
     * @param rightField RIGHT FIELD of the MOAT
     */
    public void removeMoat(final Field leftField, final Field rightField) {
            //TODO NOT implementet yet
            //Moat entfernen - richtige Verbindungen wiederherstellen
    }

    /**
     * @brief adds a Moat which can't crossed by any piece, done by removing Neighbors
     * @param leftField left Field of the Moat
     * @param rightField right Filed of the Moat
     */
    public final void addMoat(final Field leftField, final Field rightField) {
        if (leftField.getNeighbor(FieldDirection.CounterClockwise) == rightField) {
            mMoats.add(new Edge(leftField, rightField));
            //remove inner diagonals connections
            if (leftField.getNeighbor(FieldDirection.InCounterClockwise) != null) {
                leftField.getNeighbor(FieldDirection.InCounterClockwise).setNeighbor(FieldDirection.OutClockwise, null);
                leftField.setNeighbor(FieldDirection.InCounterClockwise, null);
            }
            if (rightField.getNeighbor(FieldDirection.InClockwise) != null) {
                rightField.getNeighbor(FieldDirection.InClockwise).setNeighbor(FieldDirection.OutCounterClockwise, null);
                rightField.setNeighbor(FieldDirection.InClockwise, null);
            }
            //remove outer diagonal connetions
            if (leftField.getNeighbor(FieldDirection.OutCounterClockwise) != null) {
                leftField.getNeighbor(FieldDirection.OutCounterClockwise).setNeighbor(FieldDirection.InClockwise, null);
                leftField.setNeighbor(FieldDirection.OutCounterClockwise, null);
            }
            if (rightField.getNeighbor(FieldDirection.OutClockwise) != null) {
                rightField.getNeighbor(FieldDirection.OutClockwise).setNeighbor(FieldDirection.InCounterClockwise, null);
                rightField.setNeighbor(FieldDirection.OutClockwise, null);
            }
            //remove horizontal connections
            leftField.setNeighbor(FieldDirection.CounterClockwise, null);
            rightField.setNeighbor(FieldDirection.Clockwise, null);
        } else {
            // TODO Throw exception?
            J3ChessApp.getLogger().error("Can't create Moat, leftField is not a"
                + "leftNeighbor of rightField");
        }
    }

    /* ##################################################################
     * Set Neighbors
     * #################################################################
     * */

/**
 * @Brief sets all the Neighbors of every Field
 */
    private void setNeighbors() {
        for (int circle = 0; circle < NUMBEROFCIRCLES; circle++) {
            for (int column = 0; column < NUMBEROFCOLUMNS; column++) {
                final Field field = mFields[column][circle];
                setInner(field, column, circle);
                setOuter(field, column, circle);
                setLeft(field, column, circle);
                setRight(field, column, circle);
                setLeftInner(field, column, circle);
                setLeftOuter(field, column, circle);
                setRightInner(field, column, circle);
                setRightOuter(field, column, circle);
            }
        }
    }

/**
 * @Brief sets the right Neighbor of an Field
 * @param field the Field
 * @param column the Column of the Field
 * @param circle the Circle of the Field
 */
    private void setRight(final Field field, final int column, final int circle) {
        field.setNeighbor(FieldDirection.CounterClockwise, mFields[((column + 1) % NUMBEROFCOLUMNS)][circle]);
    }

    /**
     * @Brief sets the left Neighbor of an Field
     * @param field the Field
     * @param column the Column of the Field
     * @param circle the Circle of the Field
     */
    private void setLeft(final Field field, final int column, final int circle) {
        if (column == 0) {
            field.setNeighbor(FieldDirection.Clockwise, mFields[NUMBEROFCOLUMNS - 1][circle]);
        } else {
            field.setNeighbor(FieldDirection.Clockwise, mFields[column - 1][circle]);
        }
    }
    /**
     * @Brief sets the inner Neighbor of an Field
     * @param field the Field
     * @param column the Column of the Field
     * @param circle the Circle of the Field
     */
    private void setInner(final Field field, final int column, final int circle) {
        if (circle < NUMBEROFCIRCLES - 1) {
            field.setNeighbor(FieldDirection.In, mFields[column][circle + 1]);
        } else {
            field.setNeighbor(FieldDirection.In, mFields[((column + NUMBEROFCOLUMNS / 2) % NUMBEROFCOLUMNS)][circle]);
        }
    }
    /**
     * @Brief sets the outer Neighbor of an Field
     * @param field the Field
     * @param column the Column of the Field
     * @param circle the Circle of the Field
     */
    private void setOuter(final Field field, final int column, final int circle) {
        if (circle > 0) {
            field.setNeighbor(FieldDirection.Out, mFields[column][circle - 1]);
        }
    }
    /**
     * @Brief sets the right outer Neighbor of an Field
     * @param field the Field
     * @param column the Column of the Field
     * @param circle the Circle of the Field
     */
    private void setRightOuter(final Field field, final int column, final int circle) {
        if (circle > 0) {
            field.setNeighbor(FieldDirection.OutCounterClockwise, mFields[(column + 1) % NUMBEROFCOLUMNS][circle - 1]);
        }
    }
    /**
     * @Brief sets the left outer Neighbor of an Field
     * @param field the Field
     * @param column the Column of the Field
     * @param circle the Circle of the Field
     */
    private void setLeftOuter(final Field field, final int column, final int circle) {
        if (circle > 0) {
            if (column > 0) {
                field.setNeighbor(FieldDirection.OutClockwise, mFields[column - 1][circle - 1]);
            } else {
                field.setNeighbor(FieldDirection.OutClockwise, mFields[NUMBEROFCOLUMNS - 1][circle - 1]);
            }
        }
    }
    /**
     * @Brief sets the right inner Neighbor of an Field
     * @param field the Field
     * @param column the Column of the Field
     * @param circle the Circle of the Field
     */
    private void setRightInner(final Field field, final int column, final int circle) {
        if (circle < NUMBEROFCIRCLES - 1) {
            field.setNeighbor(FieldDirection.InCounterClockwise, mFields[(column + 1) % NUMBEROFCOLUMNS][circle + 1]);
        } else {
            //diagonal through the middle
            field.setNeighbor(FieldDirection.InCounterClockwise, mFields[(column + ((NUMBEROFCIRCLES - 1) * 2)) % NUMBEROFCOLUMNS][circle]);
        }
    }
    /**
     * @Brief sets the left inner Neighbor of an Field
     * @param field the Field
     * @param column the Column of the Field
     * @param circle the Circle of the Field
     */
    private void setLeftInner(final Field field, final int column, final int circle) {
        if (circle < NUMBEROFCIRCLES - 1) {
            if (column != 0) {
                field.setNeighbor(FieldDirection.InClockwise, mFields[column - 1][circle + 1]);
            } else {
                field.setNeighbor(FieldDirection.InClockwise, mFields[NUMBEROFCOLUMNS - 1][circle + 1]);
            }
        } else {
            //diagonal through the middle
            field.setNeighbor(FieldDirection.InClockwise, mFields[(column + (NUMBEROFCOLUMNS - ((NUMBEROFCIRCLES - 1) * 2))) % NUMBEROFCOLUMNS][circle]);
        }
    }
    /**
     * @brief Creates all the fields and adds the required connections.
     */
    private void createFields() {
        mFields = new Field[NUMBEROFCOLUMNS][NUMBEROFCIRCLES];
        for (int i = 0; i < NUMBEROFCOLUMNS; i++) {
            for (int j = 0; j < NUMBEROFCIRCLES; j++) {
                mFields[i][j] = new Field(j, i);
            }
        }
    }

    /**
     * @brief Calculates draw positions for all fields from spheric to cartesian coordinates.
     *
     * Sets the X and Y draw coordinates of the field. Coordinates are calculated in the
     * range [-1..1], where [0,0] is the center of the board. The coordinates need to be
     * scaled to the total board size before drawing.
     * Visualization: https://github.com/NPTricky/ccd-colon-colon/issues/11#issuecomment-29002979
     *
     * @param centerSize    Relative size of the center circle, e.g. 0.2 means 20% of the total diameter
     */
    private void calculateDrawPositions(final float centerSize) {
        for (int m = 0; m < NUMBEROFCIRCLES; m++) {
            for (int n = 0; n < NUMBEROFCOLUMNS; n++) {
                // Calculate radius of the fields
                // Divide the total radius by 13, where each 2m+1 is the center of one field
                // Can be visualized using the following 13 chars: |1|2|3|4|5|6|
                // Each number marks a center of a field
                float radius = (1.f - (2.f * m + 1.f) / (NUMBEROFCIRCLES * 2.f));
                // Scale according to centerSize
                radius = (1.f - centerSize) * radius + centerSize;
                // Calculate angle
                final float rho = (float) (2.f * Math.PI * (n / NUMBEROFCOLUMNS));

                // Convert to cartesian
                final Vector2d cartesian = Vector2d.fromPolarCoordinates(radius, rho);

                // Set coordinates of field
                mFields[n][m].setDrawPosition(cartesian);
            }
        }
    }

    /**
     * @brief Returns a field object by its coordinate.
     * Coordinates are m from the center to the outside and n around the circular chessboard.
     * @param column    Coordinate from the center to the outside
     * @param circle    Coordinate around the circular chessboard
     * @return Field object
     */
    public final Field getField(final int column, final int circle) {
        // Safety checks
        if (mFields != null && column < mFields.length
                && circle < mFields[0].length) {
            return mFields[column][circle];
        }
        // TODO log error
        // Return null on error
        return null;
    }
    /**
     * @ Brief prints all Fields just for debuggin
     */
    private void printAllFields() {
        for (int circles = 0; circles < NUMBEROFCIRCLES; circles++) {
            for (int columns = 0; columns < NUMBEROFCOLUMNS; columns++) {
                mFields[columns][circles].print();
            }
        }
    }
}
