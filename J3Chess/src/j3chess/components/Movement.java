package j3chess.components;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import artemis.Component;
import j3chess.MotionPattern;

/**
 * entity component for all movable entities.
 */
public class Movement extends Component {

    /** @brief list of possible move patterns */
    private List<MotionPattern> mMoves;

    /**
     * @brief empty default constructor for movement component
     */
    public Movement() {
        this(new ArrayList<MotionPattern>(Arrays.asList(new MotionPattern())));
    }

    /**
     * @brief constructor for a arbitrary number of motion patterns
     * @param patterns arbitrary number of possible move patterns
     */
    public Movement(final MotionPattern...patterns) {
        this(Arrays.asList(patterns));
    }

    /**
     * @brief complete constructor for a movement component
     * @param moves list of possible moves
     */
    public Movement(final List<MotionPattern> moves) {
        this.mMoves = moves;
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
     * @param moves list of possible move patterns
     */
    public final void setMoves(final List<MotionPattern> moves) {
        this.mMoves = moves;
    }

}
