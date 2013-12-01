package j3chess;

/**
 * interface to enable the translation of directions into eight neighborhood.
 * 
 * @param <T>
 *            the source direction
 * @param <U>
 *            the target direction
 */
interface Translatable<T extends Direction, U extends Direction> {
    /**
     * @brief translates a direction into eight neighborhood
     * @param direction
     *            a source direction given in a generic type
     * @return a target direction given in eight neighborhood
     */
    U translate(final T direction);
}

/**
 * interface to enable the translation of directions into eight neighborhood.
 * 
 * @param <T>
 *            the source direction
 */
interface TranslatableToGeneric<T extends Direction> extends
        Translatable<T, GenericDirection> {
}

/**
 * interface to enable the translation of eight neighborhood into directions.
 * 
 * @param <U>
 *            the target direction
 */
interface TranslatableFromGeneric<U extends Direction> extends
        Translatable<GenericDirection, U> {
}