package Structures;

public class Node<E> {
    private E element;
    private Node<E> next;
    private Node<E> previous;

    public Node(E e) {
        this.element = e;
    }

    public Node<E> getNext() { return next; }
    public void setNext(Node<E> n) { this.next = n; }
    public Node<E> getPrevious() { return previous; }
    public void setPrevious(Node<E> n) { this.previous = n; }
    public E getElement() { return this.element; }
    public void setElement(E e) { this.element = e; }
}