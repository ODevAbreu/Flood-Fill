
import java.util.Arrays;

class MyList<T> {
    protected int capacity;
    protected Object[] data;
    protected int size;

    public MyList() {
        this.data = new Object[10];
        this.size = 0;
    }

    public MyList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("O índice deve ser um número inteiro positivo.");
        }
        this.capacity = capacity;
        this.data = new Object[capacity];
        this.size = 0;
    }

    public void add(T valor) {
        if (size == data.length) {
            Object[] newList = new Object[(data.length + 1) * 2];
            for (int i = 0; i < data.length; i++) {
                newList[i] = data[i];
            }
            data = newList;
        }
        data[size] = valor;
        size++;
    }

    public T remove(int index) {
        verifyIndex(index);
        T removed = (T) data[index];

        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }

        size--;
        data[size] = null;

        return removed;
    }

    public void removeValue(T value) {
        int index = indexOf(value);
        if (index >= 0) {
            remove(index);
        }
    }

    public T get(int index) {
        verifyIndex(index);
        return (T) data[index];
    }

    public void set(int index, T value) {
        verifyIndex(index);
        data[index] = value;

    }

    public boolean contains(T value) {
        if (indexOf(value) >= 0) {
            return true;
        } else {
            return false;
        }
    }

    public int indexOf(T value) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    public String toArray() {
        return Arrays.toString(Arrays.copyOf(data, size));
    }

    public void verifyIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Índice fora dos limites da lista.");
        }
    }
}
