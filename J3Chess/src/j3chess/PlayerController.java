package j3chess;

/**
 * Abstract base class for player controllers.
 */
public abstract class PlayerController {
    /**
     * @brief Create an instance of a PlayerController for a given player.
     * @param player The player which owns the PlayerController.
     */
    public PlayerController(final Player player) {
        mPlayer = player;
    }

    /** @brief The Player which owns the PlayerController. */
    protected Player mPlayer;
}
