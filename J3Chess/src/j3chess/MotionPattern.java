package j3chess;

import java.util.Arrays;
import java.util.List;

/**
 * a general description of a kind of motion. motion patterns are combinations
 * of motions defining a move from the starting position to the end position.
 */
public class MotionPattern {

    /** @brief the list of motions which aggregates a motion pattern */
    private List<Motion> mMotions;

    /**
     * @brief default empty constructor for a motion pattern
     */
    public MotionPattern() {
        this(new Motion());
    }

    /**
     * @brief constructor for a arbitrary number of motions
     * @param motions
     *            arbitrary number of motions which aggregates a motion pattern
     */
    public MotionPattern(final Motion... motions) {
        this(Arrays.asList(motions));
    }

    /**
     * @brief complete constructor for a motion pattern
     * @param motions
     *            the list of motions which aggregates a motion pattern
     */
    public MotionPattern(final List<Motion> motions) {
        this.mMotions = motions;
    }

    /**
     * @brief getter for the mMotions member
     * @return the list of motions which aggregates a motion pattern
     */
    public final List<Motion> getMotions() {
        return mMotions;
    }

    /**
     * @brief setter the mMotions member
     * @param motions
     *            the list of motions which aggregates a motion pattern
     */
    public final void setMotions(final List<Motion> motions) {
        this.mMotions = motions;
    }

}
