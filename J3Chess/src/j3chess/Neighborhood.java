package j3chess;

/**
 * enum of an eight neighborhood. sorted by maximum distance.
 */

public enum Neighborhood {
    /** @brief right from the center */
    Right,
    /** @brief top from the center */
    Top,
    /** @brief top right from the center */
    TopRight,
    /** @brief top left from the center */
    TopLeft,
    /** @brief left from the center */
    Left,
    /** @brief bottom from the center */
    Bottom,
    /** @brief bottom left from the center */
    BottomLeft,
    /** @brief bottom right from the center */
    BottomRight;
}
