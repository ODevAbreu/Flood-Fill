package Structures;

public abstract class AbstractList<E> implements Collection<E> {
    protected Node<E> top;
    protected int size;

    public AbstractList() {
        this.top = null;
        this.size = 0;
    }

    @Override
    public boolean isEmpty() { return top == null; }

    @Override
    public int size() { return size; }

    @Override
    public void clear() {
        top = null;
        size = 0;
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        Node<E> current = top;
        int i = 0;
        while (current != null) {
            array[i++] = current.getElement();
            current = current.getNext();
        }
        return array;
    }

    @Override
    public String toString() {
        if (isEmpty()) return "[]";
        StringBuilder sb = new StringBuilder("[");
        Node<E> current = top;
        while (current != null) {
            sb.append(current.getElement());
            if (current.getNext() != null) sb.append(", ");
            current = current.getNext();
        }
        return sb.append("]").toString();
    }
}