package j3chess.motion;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

/**
 * a general description of a kind of motion. motion patterns are combinations
 * of motions defining a move from the starting position to the end position.
 */
public class MotionPattern {

    @Override
    public String toString() {
        return "MotionPattern [mMask=" + mMask + ", mMotions=" + mMotions
                + ", mIsJump=" + mIsJump + "]";
    }

    /**
     * @brief the motion pattern direction mask (0 for forbidden directions).
     *        only applied to the first motion of a motion pattern.
     */
    private EnumSet<PieceDirection> mMask;
    /** @brief the list of motions which aggregates a motion pattern */
    private List<Motion> mMotions;
    /**
     * @brief whether the motion pattern is a jump or not (jumping is
     *        unblockable) (if true = jump over pieces)
     */
    private boolean mIsJump;

    /**
     * @brief default empty constructor for a motion pattern
     */
    public MotionPattern() {
        this(EnumSet.allOf(PieceDirection.class), new Motion());
    }

    /**
     * @brief constructor for an arbitrary number of motions. mask will default
     *        to EnumSet.allOf(PieceDirection.class) isJump will default to
     *        false
     * @param motions
     *            arbitrary number of motions which aggregates a motion pattern
     */
    public MotionPattern(final Motion... motions) {
        this(EnumSet.allOf(PieceDirection.class), false, Arrays.asList(motions));
    }

    /**
     * @brief constructor for a mask and an arbitrary number of motions isJump
     *        will default to false
     * @param mask
     *            the motion pattern direction mask
     * @param motions
     *            arbitrary number of motions which aggregates a motion pattern
     */
    public MotionPattern(final EnumSet<PieceDirection> mask,
            final Motion... motions) {
        this(mask, false, Arrays.asList(motions));
    }

    /**
     * @brief constructor for a mask and an arbitrary number of motions
     * @param mask
     *            the motion pattern direction mask
     * @param isJump
     *            whether the motion pattern is a jump or not (jumping is
     *            unblockable) (if true = jump over pieces)
     * @param motions
     *            arbitrary number of motions which aggregates a motion pattern
     */
    public MotionPattern(final EnumSet<PieceDirection> mask,
            final boolean isJump, final Motion... motions) {
        this(mask, isJump, Arrays.asList(motions));
    }

    /**
     * @brief constructor for a mask and an arbitrary number of motions mask
     *        will default to EnumSet.allOf(PieceDirection.class)
     * @param isJump
     *            whether the motion pattern is a jump or not (jumping is
     *            unblockable) (if true = jump over pieces)
     * @param motions
     *            arbitrary number of motions which aggregates a motion pattern
     */
    public MotionPattern(final boolean isJump, final Motion... motions) {
        this(EnumSet.allOf(PieceDirection.class), isJump, Arrays
                .asList(motions));
    }

    /**
     * @brief complete constructor for a motion pattern
     * @param mask
     *            the motion pattern direction mask
     * @param isJump
     *            whether the motion pattern is a jump or not (jumping is
     *            unblockable) (if true = jump over pieces)
     * @param motions
     *            the list of motions which aggregates a motion pattern
     */
    public MotionPattern(final EnumSet<PieceDirection> mask,
            final boolean isJump, final List<Motion> motions) {
        this.mMask = mask;
        this.mIsJump = isJump;
        this.mMotions = motions;
    }

    /**
     * @brief getter for the mMask member
     * 
     *        The Motion Pattern Direction Mask ( alias movement.getMask() ) is
     *        an EnumSet of PieceDirection's.
     * 
     *        EnumSet retains it's natural order: 0 Right 1 Forward 2
     *        ForwardRight 3 ForwardLeft 4 Left 5 Backward 6 BackwardLeft 7
     *        BackwardRight
     * 
     *        e.g. [ Forward ] = [ 0 1 0 0 0 0 0 0 ] [ Horizontal ] = [ 0 0 1 1
     *        0 0 1 1 ] [ Left & Backward ] = [ 0 0 0 0 1 1 0 0 ]
     * 
     * @return the motion pattern direction mask
     */
    public final EnumSet<PieceDirection> getMask() {
        return mMask;
    }

    /**
     * @brief setter for the mMask member
     * @param mask
     *            the motion pattern direction mask
     */
    public final void setMask(final EnumSet<PieceDirection> mask) {
        this.mMask = mask;
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

    /**
     * @brief getter for the mIsJump member
     * @return whether the motion pattern is a jump or not (jumping is
     *         unblockable) (if true = jump over pieces)
     */
    public final boolean isJump() {
        return mIsJump;
    }

    /**
     * @brief setter for the mIsJump member
     * @param isJump
     *            whether the motion pattern is a jump or not (jumping is
     *            unblockable) (if true = jump over pieces)
     */
    public final void setIsJump(final boolean isJump) {
        this.mIsJump = isJump;
    }

}
