package j3chess.components;

import j3chess.MotionPattern;
import j3chess.PieceDirection;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import artemis.Component;

/**
 * entity component for all movable entities.
 */
public class Movement extends Component {

    /** @brief the motion pattern direction mask (0 for forbidden directions) */
    private EnumSet<PieceDirection> mMask;
    /** @brief list of possible move patterns */
    private List<MotionPattern> mMoves;

    /**
     * @brief empty default constructor for movement component
     */
    public Movement() {
        this(new MotionPattern());
    }

    /**
     * @brief constructor for a arbitrary number of motion patterns
     * @param patterns
     *            arbitrary number of possible move patterns
     */
    public Movement(final MotionPattern... patterns) {
        this(EnumSet.allOf(PieceDirection.class), Arrays.asList(patterns));
    }

    /**
     * @brief complete constructor for a movement component for a arbitrary
     *        number of motion patterns
     * @param mask
     *            the motion pattern direction mask
     * @param patterns
     *            arbitrary number of possible move patterns
     */
    public Movement(final Set<PieceDirection> mask,
            final MotionPattern... patterns) {
        this(EnumSet.allOf(PieceDirection.class), Arrays.asList(patterns));
    }

    /**
     * @brief complete constructor for a movement component
     * @param mask
     *            the motion pattern direction mask
     * @param moves
     *            list of possible moves
     */
    public Movement(final Set<PieceDirection> mask,
            final List<MotionPattern> moves) {
        this.mMoves = moves;
    }

    /**
     * @brief getter for the mMask member
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
     * @brief getter for the mMoves member
     * @return list of possible move patterns
     */
    public final List<MotionPattern> getMoves() {
        return mMoves;
    }

    /**
     * @brief setter for the mMoves member
     * @param moves
     *            list of possible move patterns
     */
    public final void setMoves(final List<MotionPattern> moves) {
        this.mMoves = moves;
    }

}
