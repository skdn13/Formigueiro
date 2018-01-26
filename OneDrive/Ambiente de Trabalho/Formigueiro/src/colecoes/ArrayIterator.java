/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colecoes;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author pmms8
 *
 * @param <T>
 */
public class ArrayIterator<T> implements Iterator<T> {

    private int size;
    private int current;
    private T[] elements;

    /**
     *
     * @param collection
     * @param size
     */
    public ArrayIterator(T[] collection, int size) {
        this.elements = collection;
        this.size = size;
        this.current = 0;
    }

    @Override
    public boolean hasNext() {
        return (current < size);
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        current++;
        return this.elements[current - 1];

    }
}
