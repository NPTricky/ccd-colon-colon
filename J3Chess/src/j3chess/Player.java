package j3chess;

/**
 * enum of available players.
 */
public enum Player {
    /** @brief the player number one */
    ONE("Player 1"),
    /** @brief the player number two */
    TWO("Player 2"),
    /** @brief the player number three */
    THREE("Player 3");

    /** @brief the name of the player */
    private String mName;

    /**
     * @brief setter for the mName member
     * @param name the name of the player
     */
    public final void setName(final String name) {
        this.mName = name;
    }

    /**
     * @brief private constructor
     * @param name the name of the player
     */
    private Player(final String name) {
        this.mName = name;
    }

    @Override
    public String toString() {
        return mName;
    }
}
