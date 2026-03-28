package Structures;

public interface Collection<E> {
    void add(E element);
    E remove();
    boolean isEmpty();
    int size();
    void clear();
    Object[] toArray();
    String toString();
}