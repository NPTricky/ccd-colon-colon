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
     * @brief default empty constructor for a motion
     */
    public Motion() {
        this(EnumSet.of(PieceDirection.Forward), 1);
    }

    /**
     * @brief complete constructor for a motion
     * @param directions
     *            the directions of the motion
     * @param steps
     *            the enforced number of steps of the motion
     *            [0  ] = infinity
     *            [1..] = enforced finite number of steps
     */
    public Motion(
            final EnumSet<PieceDirection> directions,
            final int steps) {
        this.mDirections = directions;
        this.mSteps = steps;
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
     * @return the enforced number of steps of the motion.
     *         [0  ] = infinity
     *         [1..] = enforced finite number of steps
     */
    public final int getStepCount() {
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

}
