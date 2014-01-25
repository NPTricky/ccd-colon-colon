package artemis.utils;

/**
 * Collection type a bit like ArrayList but does not preserve the order of its
 * entities, speedwise it is very good, especially suited for games.
 */

public class Bag<E> implements ImmutableBag<E> {
    private E[] data;
    private int size = 0;

    /**
     * Constructs an empty Bag with an initial capacity of 64.
     * 
     */
    public Bag() {
        this(64);
    }

    /**
     * Constructs an empty Bag with the specified initial capacity.
     * 
     * @param capacity
     *            the initial capacity of Bag
     */
    @SuppressWarnings("unchecked")
    public Bag(final int capacity) {
        data = (E[]) new Object[capacity];
    }

    /**
     * Removes the element at the specified position in this Bag. does this by
     * overwriting it was last element then removing last element
     * 
     * @param index
     *            the index of element to be removed
     * @return element that was removed from the Bag
     */
    public E remove(final int index) {
        final E e = data[index]; // make copy of element to remove so it can be
                                 // returned
        data[index] = data[--size]; // overwrite item to remove with last
                                    // element
        data[size] = null; // null last element, so gc can do its work
        return e;
    }

    /**
     * Remove and return the last object in the bag.
     * 
     * @return the last object in the bag, null if empty.
     */
    public E removeLast() {
        if (size > 0) {
            final E e = data[--size];
            data[size] = null;
            return e;
        }

        return null;
    }

    /**
     * Removes the first occurrence of the specified element from this Bag, if
     * it is present. If the Bag does not contain the element, it is unchanged.
     * does this by overwriting it was last element then removing last element
     * 
     * @param e
     *            element to be removed from this list, if present
     * @return <tt>true</tt> if this list contained the specified element
     */
    public boolean remove(final E e) {
        for (int i = 0; i < size; i++) {
            final E e2 = data[i];

            if (e == e2) {
                data[i] = data[--size]; // overwrite item to remove with last
                                        // element
                data[size] = null; // null last element, so gc can do its work
                return true;
            }
        }

        return false;
    }

    /**
     * Check if bag contains this element.
     * 
     * @param e
     * @return
     */
    @Override
    public boolean contains(final E e) {
        for (int i = 0; size > i; i++) {
            if (e == data[i]) {
                return true;
            }
        }
        return false;
    }

    /**
     * Removes from this Bag all of its elements that are contained in the
     * specified Bag.
     * 
     * @param bag
     *            Bag containing elements to be removed from this Bag
     * @return {@code true} if this Bag changed as a result of the call
     */
    public boolean removeAll(final ImmutableBag<E> bag) {
        boolean modified = false;

        for (int i = 0; i < bag.size(); i++) {
            final E e1 = bag.get(i);

            for (int j = 0; j < size; j++) {
                final E e2 = data[j];

                if (e1 == e2) {
                    remove(j);
                    j--;
                    modified = true;
                    break;
                }
            }
        }

        return modified;
    }

    /**
     * Returns the element at the specified position in Bag.
     * 
     * @param index
     *            index of the element to return
     * @return the element at the specified position in bag
     */
    @Override
    public E get(final int index) {
        return data[index];
    }

    /**
     * Returns the number of elements in this bag.
     * 
     * @return the number of elements in this bag
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns the number of elements the bag can hold without growing.
     * 
     * @return the number of elements the bag can hold without growing.
     */
    public int getCapacity() {
        return data.length;
    }

    /**
     * Checks if the internal storage supports this index.
     * 
     * @param index
     * @return
     */
    public boolean isIndexWithinBounds(final int index) {
        return index < getCapacity();
    }

    /**
     * Returns true if this list contains no elements.
     * 
     * @return true if this list contains no elements
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Adds the specified element to the end of this bag. if needed also
     * increases the capacity of the bag.
     * 
     * @param e
     *            element to be added to this list
     */
    public void add(final E e) {
        // is size greater than capacity increase capacity
        if (size == data.length) {
            grow();
        }

        data[size++] = e;
    }

    /**
     * Set element at specified index in the bag.
     * 
     * @param index
     *            position of element
     * @param e
     *            the element
     */
    public void set(final int index, final E e) {
        if (index >= data.length) {
            grow(index * 2);
        }
        size = index + 1;
        data[index] = e;
    }

    private void grow() {
        final int newCapacity = (data.length * 3) / 2 + 1;
        grow(newCapacity);
    }

    @SuppressWarnings("unchecked")
    private void grow(final int newCapacity) {
        final E[] oldData = data;
        data = (E[]) new Object[newCapacity];
        System.arraycopy(oldData, 0, data, 0, oldData.length);
    }

    public void ensureCapacity(final int index) {
        if (index >= data.length) {
            grow(index * 2);
        }
    }

    /**
     * Removes all of the elements from this bag. The bag will be empty after
     * this call returns.
     */
    public void clear() {
        // null all elements so gc can clean up
        for (int i = 0; i < size; i++) {
            data[i] = null;
        }

        size = 0;
    }

    /**
     * Add all items into this bag.
     * 
     * @param added
     */
    public void addAll(final ImmutableBag<E> items) {
        for (int i = 0; items.size() > i; i++) {
            add(items.get(i));
        }
    }

}
