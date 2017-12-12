package Collections;

import java.util.*;

public class HashMapM<Key, Value> extends AbstractMap<Key, Value> implements Map<Key, Value>{
    private static final float LOAD_FACTOR = 0.75f;
    private static final int INITIAL_CAPACITY = 16;
    private int size;
    private int capacity;
    Node<Key, Value>[] array;
    private class Node<Key, Value> {
        Key key;
        Value value;
        Node next;

        public Node(Key key, Value value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public HashMapM() {
        capacity = INITIAL_CAPACITY;
        array = (Node<Key, Value>[]) new Object[capacity];
    }

    public Value put(Key key, Value value) {
        if (size * LOAD_FACTOR >= capacity) resize();
        int i = getIndex(key);

        return value;
    }

    private int getIndex(Key key) {
        return (key.hashCode() & 0x7fffffff) % size;
    }

    private void resize() {

    }

    @Override
    public Value remove(Object key) {
        return null;
    }

    @Override
    public void putAll(Map<? extends Key, ? extends Value> m) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set<Key> keySet() {
        return null;
    }

    @Override
    public Collection<Value> values() {
        return null;
    }

    @Override
    public Set<Entry<Key, Value>> entrySet() {
        return null;
    }

    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean containsKey(Object key) {
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public Value get(Object key) {
        return null;
    }

}
