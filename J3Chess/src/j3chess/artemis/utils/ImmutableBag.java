package j3chess.artemis.utils;

public interface ImmutableBag<E> {

	E get(int index);

	int size();

	boolean isEmpty();
	
	boolean contains(E e);

}
