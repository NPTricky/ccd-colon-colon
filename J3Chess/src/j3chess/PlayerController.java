package j3chess;

/**
 * interface to define the controller of a player.
 */
public interface PlayerController {
    /**
     * @brief execute a move in the arbitrary fashion of the player controller
     * @return the done move
     */
    Move calculateMove();
}
