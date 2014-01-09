package j3chess;

import java.util.EnumSet;

/**
 * a generalized abstraction of a motion.
 */
public class Motion {

    /** @brief the directions of the motion */
    private EnumSet<PieceDirection> mDirections;
    /**
     * @brief the enforced number of steps of the motion
     *        [0  ] = infinity
     *        [1..] = enforced finite number of steps
     */
    private int mSteps;
    /**
     * @brief whether the motion is blockable or not (e.g. jumping is
     *        unblockable) (if true = unblockable by pieces)
     */
    private boolean mUnblockable;

    /**
     * @brief default empty constructor for a motion
     */
    public Motion() {
        this(EnumSet.of(PieceDirection.Forward), 1, false);
    }

    /**
     * @brief complete constructor for a motion
     * @param directions
     *            the directions of the motion
     * @param steps
     *            the enforced number of steps of the motion
     *            [0  ] = infinity
     *            [1..] = enforced finite number of steps
     * @param unblockable
     *            whether the motion is unblockable or not
     *            (e.g. jumping is unblockable)
     *            (if true = unblockable by pieces)
     */
    public Motion(
            final EnumSet<PieceDirection> directions,
            final int steps,
            final boolean unblockable) {
        this.mDirections = directions;
        this.mSteps = steps;
        this.mUnblockable = unblockable;
    }

    /**
     * @brief apply a mask to the directions of the motion and return the result
     * @param mask the direction mask
     * @return masked motion directions
     */
    public final EnumSet<PieceDirection> getDirectionsMasked(
            final EnumSet<PieceDirection> mask) {
        EnumSet<PieceDirection> result = mDirections;
        result.retainAll(mask);
        return result;
    }

    /**
     * @brief getter for the mDirections member
     * @return the directions of the motion
     */
    public final EnumSet<PieceDirection> getDirections() {
        return mDirections;
    }

    /**
     * @brief setter for the mDirections member
     * @param directions
     *            the directions of the motion
     */
    public final void setDirections(final EnumSet<PieceDirection> directions) {
        this.mDirections = directions;
    }

    /**
     * @brief getter for the mSteps member
     * @return the enforced number of steps of the motion. zero if infinite.
     */
    public final int getSteps() {
        return mSteps;
    }

    /**
     * @brief setter for the mSteps member
     * @param steps
     *            the enforced number of steps of the motion
     *            [0  ] = infinity
     *            [1..] = enforced finite number of steps
     */
    public final void setSteps(final int steps) {
        this.mSteps = steps;
    }

    /**
     * @brief getter for the mUnblockable member
     * @return whether the motion is unblockable or not
     *         (e.g. jumping is unblockable)
     *         (if true = unblockable by pieces)
     */
    public final boolean getUnblockable() {
        return mUnblockable;
    }

    /**
     * @brief setter for the mUnblockable member
     * @param unblockable
     *            whether the motion is a jump or not
     *            (e.g. jumping is unblockable)
     *            (if true = unblockable by pieces)
     */
    public final void setUnblockable(final boolean unblockable) {
        this.mUnblockable = unblockable;
    }

}
