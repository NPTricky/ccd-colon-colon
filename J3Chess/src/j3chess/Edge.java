package j3chess;

/**
 * Class for horizontal Edges between two fields, to define Creeks and Moats. Edge are defined by a left and a right Field.
 */
public class Edge {
    /**
     * Left Field of the Edge.
     */
    private Field mLeftField;
    /**
     * Right Field of the Edge.
     */
    private Field mRightField;

    /**
     * @Brief Construktor - creates an new Edge
     * @param leftField left Field of the Edge
     * @param rightField right Field of the Edge
     */
    public Edge(final Field leftField, final Field rightField) {
        this.mLeftField = leftField;
        this.mRightField = rightField;
    }
    /**
     * @Brief checks is the edge is equal to another Edge
     * @param edge The edge to compare with
     * @return return true if the two have the same two horizontal Fields
     */
    public final Boolean isEqual(final Edge edge) {
        if (this.mLeftField == edge.mLeftField
                && this.mRightField == edge.mRightField) {
            return true;
        }
        return false;
    }
}