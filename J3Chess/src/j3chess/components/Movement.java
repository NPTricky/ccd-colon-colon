package j3chess.components;

import j3chess.MotionPattern;

import java.util.Arrays;
import java.util.List;

import artemis.Component;

/**
 * entity component for all movable entities.
 */
public class Movement extends Component {

    /** @brief list of possible move patterns */
    private List<MotionPattern> mPatterns;
    /** @brief whether the movable entity already crossed the center of the
     *         map. Required to map PieceDirection's into FieldDirection's. */
    private boolean mCrossedCenter = false;

    /**
     * @brief empty default constructor for movement component
     */
    public Movement() {
        this(new MotionPattern());
    }

    /**
     * @brief complete constructor for a movement component for a arbitrary
     *        number of motion patterns
     * @param patterns
     *            arbitrary number of possible move patterns
     */
    public Movement(final MotionPattern... patterns) {
        this(Arrays.asList(patterns));
    }

    /**
     * @brief complete constructor for a movement component
     * @param moves
     *            list of possible moves
     */
    public Movement(final List<MotionPattern> moves) {
        this.mPatterns = moves;
    }

    /**
     * @brief getter for the mPatterns member
     * @return list of possible move patterns
     */
    public final List<MotionPattern> getPatterns() {
        return mPatterns;
    }

    /**
     * @brief setter for the mPatterns member
     * @param moves
     *            list of possible move patterns
     */
    public final void setPatterns(final List<MotionPattern> moves) {
        this.mPatterns = moves;
    }

    /**
     * @brief getter for the mCrossedCenter member
     * @return whether the entity already crossed the center
     */
    public final boolean getCrossedCenter() {
        return mCrossedCenter;
    }

    /**
     * @brief toggles the variable, whether the component already crossed center
     */
    public final void toggleCrossedCenter() {
        this.mCrossedCenter ^= true;
    }

}
