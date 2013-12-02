package j3chess;

/**
 * interface to provide grouping capabilities by enum type.
 * @param <U> group datastructure
 * @param <T> the enum value to group by
 */
public interface Groupable<U, T extends Enum<T>> {
    /**
     * @brief returns the group datastructure U of group enum value T
     * @param group the enum value to group by
     * @return the group datastructure
     */
    U groupBy(final T group);
}
