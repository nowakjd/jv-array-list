package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private int size = 0;
    private T[] items;

    public ArrayList() {
        items = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == items.length) {
            items = growArray(items, 3 * items.length / 2);
        }
        items[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {

        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + " is out of bounds");
        }

        if (size == items.length) {
            items = growArray(items, 3 * items.length / 2);
        }

        System.arraycopy(items, index, items, index + 1, size - index);
        items[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (size + list.size() > items.length) {
            int newLenght = items.length;
            while (newLenght < size + list.size()) {
                newLenght = 3 * newLenght / 2;
            }

            items = growArray(items, newLenght);

        }
        for (int i = 0; i < list.size(); i++) {
            items[size] = list.get(i);
            size++;
        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + " is out of bounds");
        }
        return items[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + " is out of bounds");
        }
        items[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + " is out of bounds");
        }
        T result = items[index];
        System.arraycopy(items, index + 1, items, index, size - index - 1);
        size--;
        return result;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(element, items[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private T[] growArray(T[] array, int newSize) {
        T[] newItems = (T[]) new Object[newSize];
        System.arraycopy(array, 0, newItems, 0, array.length);
        return newItems;
    }
}
