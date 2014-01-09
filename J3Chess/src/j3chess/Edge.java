package j3chess;

/**
 * Class for Edges between two fields, to define Creeks and Moats. Edge are defined by a two neighbored Fields.
 */
public class Edge {
    /**
     * First Field of the Edge.
     */
    private final Field mFirstField;
    /**
     * Second Field of the Edge.
     */
    private final Field mSecoundField;

    /**
     * @Brief Constructor - creates an new Edge
     * @param firstField first Field of the Edge
     * @param secoundField second Field of the Edge
     */
    public Edge(final Field firstField, final Field secoundField) {
        this.mFirstField = firstField;
        this.mSecoundField = secoundField;
    }
    /**
     * @Brief checks is the edge is equal to another Edge
     * @param edge The edge to compare with
     * @return return true if the two have the same two horizontal Fields
     */
    public final Boolean isEqual(final Edge edge) {
        if (this.mFirstField == edge.mFirstField
                && this.mSecoundField == edge.mSecoundField) {
            return true;
        } else
            if (this.mFirstField == edge.mSecoundField
                    && this.mSecoundField == edge.mFirstField) {
                return true;
            }
        return false;
    }
}
