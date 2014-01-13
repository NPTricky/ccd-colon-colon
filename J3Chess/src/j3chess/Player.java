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
    /** @brief the controller of this player */
    private PlayerController mPlayerController;

    /**
     * @brief setter for the mName member. getter is toString().
     * @param name the name of the player
     */
    public final void setName(final String name) {
        this.mName = name;
    }

    /**
     * @brief private constructor
     * @param name the name of the player
     */
    Player(final String name) {
        this.mName = name;
        this.mPlayerController = new HumanController();
    }

    @Override
    public String toString() {
        return mName;
    }

    /**
     * @brief getter for the mPlayerController member
     * @return the controller of this player
     */
    public PlayerController getPlayerController() {
        return mPlayerController;
    }

    /**
     * @brief setter for the mPlayerController member
     * @param playerController the controller of this player
     */
    public void setPlayerController(final PlayerController playerController) {
        this.mPlayerController = playerController;
    }
}
