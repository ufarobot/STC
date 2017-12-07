package Collections;


import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayListM<T> {
    private static final int INITIAL_CAPACITY = 8;
    private static final int RESIZE_KOEFICIENT = 2;
    private T[] a;
    private int n;

    public ArrayListM() {
        a = (T[]) new Object[INITIAL_CAPACITY];
        n = 0;
    }

    public void add(T item) {
        if (n == a.length) resize(RESIZE_KOEFICIENT * a.length);
        a[n++] = item;
    }

    private void resize(int capacity) {
        T[] newArray = (T[]) new Object[capacity];
        System.arraycopy(a, 0, newArray, 0, n);
        a = newArray;
    }

    private T get(int index) {
        if (index >= n || index < 0) throw new IndexOutOfBoundsException();
        return a[index];
    }

    public int size() {
        return n;
    }

    public Iterator iterator() {
        return new ArrayListIterator();
    }

    public T remove(int index) {
        if (index >= n || index < 0) throw new IndexOutOfBoundsException();
        if (n * RESIZE_KOEFICIENT * RESIZE_KOEFICIENT < a.length)
            resize(a.length / RESIZE_KOEFICIENT);
        T item = a[index];
        for (int i = index; i < n; i++) {
            a[i] = a[i + 1];
        }
        n--;
        return item;
    }

    private class ArrayListIterator implements Iterator {

        private int i = 0;
        @Override
        public boolean hasNext() {
            return i < n;
        }
        @Override
        public Object next() {
            if (!hasNext()) throw new NoSuchElementException();
            return a[i++];
        }

    }
    public static void main(String[] args) {
        ArrayListM<Integer> array = new ArrayListM<>();
        for (int i = 0; i < 10; i++) {
            array.add(i);
        }
        array.remove(1);
        for (int i = 0; i < array.size(); i++) {
            System.out.println(array.get(i));
        }
    }
}
