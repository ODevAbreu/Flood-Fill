package Structures;

public class Queue<E> extends AbstractList<E> {
    private Node<E> first;

    public Queue() { super(); }


    @Override
    public void add(E element) {
        Node<E> newNode = new Node<>(element);
        if (isEmpty()) {
            top = first = newNode;
        } else {
            first.setNext(newNode);
            first = newNode;
        }
        size++;
    }


    @Override
    public E remove() {
        if (isEmpty()) throw new RuntimeException("Queue está vazia");
        E element = top.getElement();
        top = top.getNext();
        if (top == null) first = null;
        size--;
        return element;
    }

    public void enqueue(E element) { add(element); }
    public E dequeue() { return remove(); }
    public Node<E> front() { return top; }

    @Override
    public void clear() {
        super.clear();
        first = null;
    }
}