package colecoes;

import java.util.Iterator;
import recursos.exceptions.EmptyCollectionException;
import recursos.interfaces.collections.ListADT;

/**
 *
 * @author pmms8
 * @param <T>
 */
public class DoubleLinkedList<T> implements ListADT<T> {

    private DoubleNode<T> front;
    private DoubleNode<T> rear;
    private int numberOfElements;

    /**
     * Creates an empty list using the default capacity
     */
    public DoubleLinkedList() {
        this.front = null;
        this.rear = null;
        this.numberOfElements = 0;
    }

    /**
     *
     * @return @throws EmptyCollectionException
     */
    @Override
    public T removeFirst() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("A lista está vazia");
        }
        T first = this.front.getElement();
        this.front = front.getNext();
        if (this.front == null) {
            this.rear = null;
        } else {
            this.front.setPrevious(null);
        }

        this.numberOfElements--;
        return first;
    }

    /**
     *
     * @return @throws EmptyCollectionException
     */
    @Override
    public T removeLast() throws EmptyCollectionException {
        T last;

        if (isEmpty()) {
            throw new EmptyCollectionException("A lista está vazia");
        }

        last = this.rear.getElement();
        this.rear = this.rear.getPrevious();

        if (this.rear == null) {
            this.front = null;
        } else {
            this.rear.setNext(null);
        }

        this.numberOfElements--;
        return last;
    }

    /**
     *
     * @param element
     * @return
     * @throws EmptyCollectionException
     */
    @Override
    public T remove(T element) throws EmptyCollectionException {
        T removed;
        DoubleNode<T> nodeRemove = this.find(element);

        if (nodeRemove == null) {
            throw new EmptyCollectionException("O elemento não existe na lista");
        }

        removed = nodeRemove.getElement();
        if (nodeRemove == this.front) {
            removed = this.removeFirst();
        } else if (nodeRemove == this.rear) {
            removed = this.removeLast();
        } else {
            nodeRemove.getNext().setPrevious(nodeRemove.getPrevious());
            nodeRemove.getPrevious().setNext(nodeRemove.getNext());
            this.numberOfElements--;
        }
        return removed;
    }

    /**
     *
     * @return @throws EmptyCollectionException
     */
    @Override
    public T first() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("A lista está vazia");
        }
        return this.front.getElement();
    }

    /**
     *
     * @return @throws EmptyCollectionException
     */
    @Override
    public T last() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("A Lista está vazia");
        }
        return this.rear.getElement();
    }

    /**
     *
     * @param target
     * @return
     */
    @Override
    public boolean contains(T target) {
        return find(target) != null;
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return this.numberOfElements == 0;
    }

    /**
     *
     * @return
     */
    @Override
    public int size() {
        return this.numberOfElements;
    }

    @Override
    public Iterator<T> iterator() {
        DoubleIterator<T> newIterator = new DoubleIterator<>(this.front);
        return newIterator;
    }

    private DoubleNode<T> find(T target) {
        boolean found = false;
        DoubleNode<T> compareElement = this.front;
        DoubleNode<T> result = null;

        if (!isEmpty()) {
            while (!found && compareElement != null) {
                if (target.equals((compareElement.getElement()))) {
                    found = true;
                } else {
                    compareElement = compareElement.getNext();
                }
            }
        }
        if (found) {
            result = compareElement;
        }

        return result;
    }

    /**
     *
     * @return
     */
    public DoubleNode<T> getFront() {
        return front;
    }

    /**
     *
     * @param front
     */
    public void setFront(DoubleNode<T> front) {
        this.front = front;
    }

    /**
     *
     * @return
     */
    public DoubleNode<T> getRear() {
        return rear;
    }

    /**
     *
     * @param rear
     */
    public void setRear(DoubleNode<T> rear) {
        this.rear = rear;
    }

    /**
     *
     * @return
     */
    public int getNumberOfElements() {
        return numberOfElements;
    }

    /**
     *
     * @param numberOfElements
     */
    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }
}
