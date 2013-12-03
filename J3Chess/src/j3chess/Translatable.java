package j3chess;

/**
 * interface to enable translation.
 * @param <T> the source
 * @param <U> the target
 */
interface Translatable<T, U> {
    /**
     * @brief translates a source into a target
     * @param source a source
     * @return the target result
     */
    U translate(final T source);
}
