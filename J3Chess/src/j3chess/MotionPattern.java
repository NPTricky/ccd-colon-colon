package j3chess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * a general description of a kind of motion.
 */
public class MotionPattern {

    /** @brief the list of motions which aggregates a motion pattern */
    private List<Motion> mMotions;

    /**
     * @brief default empty constructor for a motion pattern
     */
    public MotionPattern() {
        this(new ArrayList<Motion>(Arrays.asList(new Motion())));
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
     * @code getMotions().addAll(Arrays.asList(
     *           new Motion[] {new Motion(), new Motion(), new Motion()})
     *       )
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
