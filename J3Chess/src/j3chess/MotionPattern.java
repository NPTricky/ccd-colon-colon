package j3chess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

/**
 * a general description of a kind of motion.
 */
public class MotionPattern {

    /** @brief the motion pattern direction mask (0 for forbidden directions) */
    private Set<PieceDirection> mMask;
    /** @brief the list of motions which aggregates a motion pattern */
    private List<Motion> mMotions;

    /**
     * @brief default empty constructor for a motion pattern
     */
    public MotionPattern() {
        this(EnumSet.allOf(PieceDirection.class),
             new ArrayList<Motion>(Arrays.asList(new Motion())));
    }

    /**
     * @brief constructor for a arbitrary number of motions
     * @param motions
     *            arbitrary number of motions which aggregates a motion pattern
     */
    public MotionPattern(final Motion...motions) {
        this(EnumSet.allOf(PieceDirection.class),
             Arrays.asList(motions));
    }

    /**
     * @brief constructor for a arbitrary number of motions
     * @param mask
     *            the motion pattern direction mask
     * @param motions
     *            the list of motions which aggregates a motion pattern
     */
    public MotionPattern(
            final Set<PieceDirection> mask,
            final Motion...motions) {
        this(mask, Arrays.asList(motions));
    }

    /**
     * @brief complete constructor for a motion pattern
     * @param mask
     *            the motion pattern direction mask
     * @param motions
     *            the list of motions which aggregates a motion pattern
     */
    public MotionPattern(
            final Set<PieceDirection> mask,
            final List<Motion> motions) {
        this.mMask = mask;
        this.mMotions = motions;
    }

    /**
     * @brief getter for the mMotions member
     * @code getMotions().addAll(Arrays.asList( new Motion[] {new Motion(), new
     *       Motion(), new Motion()}) )
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

    /**
     * @brief getter for the mMask member
     * @return the motion pattern direction mask
     */
    public final Set<PieceDirection> getMask() {
        return mMask;
    }

    /**
     * @brief setter for the mMask member
     * @param mask the motion pattern direction mask
     */
    public final void setMask(final Set<PieceDirection> mask) {
        this.mMask = mask;
    }

}
