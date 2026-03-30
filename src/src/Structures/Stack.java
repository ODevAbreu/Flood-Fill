package Structures;

public class Stack<E> extends AbstractList<E> {

    public Stack() { super(); }


    @Override
    public void add(E element) {
        Node<E> newNode = new Node<>(element);
        if (!isEmpty()) {
            newNode.setPrevious(top);
            top.setNext(newNode);
        }
        top = newNode;
        size++;
    }


    @Override
    public E remove() {
        if (isEmpty()) throw new RuntimeException("Stack está vazia");
        E element = top.getElement();
        top = top.getPrevious();
        if (top != null) top.setNext(null);
        size--;
        return element;
    }

    public void push(E element) { add(element); }
    public E pop() { return remove(); }
    public Node<E> top() { return top; }
}