/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colecoes;

import Excecoes.EmptyCollectionException;
import java.util.Iterator;
import recursos.interfaces.collections.ListADT;

/**
 *
 * @author pmms8
 */
public class ArrayList<T> implements ListADT<T> {

    private final int DEFAULT_CAPACITY = 100;
    private int first;
    protected int last;
    private int position;
    protected T[] orederedList;

    public ArrayList() {
        this.first = 0;
        this.last = 0;
        this.position = -1;
        this.orederedList = (T[]) new Object[this.DEFAULT_CAPACITY];
    }

    public ArrayList(int initialCapacity) {
        this.first = 0;
        this.last = 0;
        this.position = -1;
        this.orederedList = (T[]) new Object[initialCapacity];
    }

    @Override
    public T removeFirst() throws EmptyCollectionException {
        T result;

        if (isEmpty()) {
            throw new EmptyCollectionException("Empty List");
        } else {
            result = this.orederedList[0];
            this.last--;
            for (int i = 0; i < this.last; i++) {
                this.orederedList[i] = this.orederedList[i + 1];
            }
            this.orederedList[this.last] = null;
        }
        return result;
    }

    @Override
    public T removeLast() throws EmptyCollectionException {
        T result;
        if (isEmpty()) {
            throw new EmptyCollectionException("Empty List");
        } else {
            this.last--;
            result = this.orederedList[this.last];
            this.orederedList[this.last] = null;
        }
        return result;
    }

    @Override
    public T remove(T element) throws EmptyCollectionException {
        T result = null;
        if (contains(element)) {
            result = this.orederedList[this.position];
            this.last--;
            for (int i = 0; i < this.last; i++) {
                this.orederedList[i] = this.orederedList[i + 1];
            }
        }
        return result;
    }

    @Override
    public T first() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("Empty List");
        } else {
            return this.orederedList[this.first];
        }
    }

    @Override
    public T last() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("Empty List");
        } else {
            return this.orederedList[this.last - 1];
        }
    }

    @Override
    public boolean contains(T target) {
        boolean contain = false;
        int i = 0;
        if (!isEmpty()) {
            while (!contain && i < this.last) {
                if (target.equals(this.orederedList[i])) {
                    contain = true;
                    this.position = i;
                } else {
                    ++i;
                }
            }
        }
        return contain;
    }

    @Override
    public boolean isEmpty() {
        return this.last == 0;
    }

    @Override
    public int size() {
        int count = 0;

        for (int i = 0; i < this.orederedList.length; ++i) {
            if (this.orederedList[i] != null) {
                ++count;
            }
        }
        return count;
    }

    @Override
    public Iterator<T> iterator() {
        ArrayIterator<T> iterator = new ArrayIterator<T>(this.orederedList, this.last);
        return iterator;
    }

    protected void expandCapacity() {
        T[] temporaryArray = (T[]) new Object[this.orederedList.length * 2];
        for (int i = 0; i < this.last; ++i) {
            temporaryArray[i] = this.orederedList[i];
        }
        this.orederedList = temporaryArray;
    }

    protected void setList(T[] list) {
        this.orederedList = list;
    }

    public void setLast(int newLast) {
        this.last = newLast;
    }

    protected int getLast() {
        return this.last;
    }

    protected T[] getList() {
        return this.orederedList;
    }
}
