package colecoes;

import java.util.Iterator;
import recursos.exceptions.EmptyCollectionException;
import recursos.interfaces.collections.ListADT;

/**
 *
 * @author pmms8
 * @param <T>
 */
public class LinkedList<T> implements ListADT<T> {

    private int numberElements;
    private LinearNode<T> head;
    private LinearNode<T> tail;

    /**
     *
     */
    public LinkedList() {
        this.numberElements = 0;
        this.head = null;
        this.tail = null;
    }

    /**
     *
     * @param front
     */
    public LinkedList(LinearNode<T> front) {
        this.numberElements = 0;
        this.head = front;
        while (front.getNext() != null) {
            front = front.getNext();
            this.numberElements++;
        }
        this.tail = front;

    }

    /**
     *
     * @param element
     */
    public void add(T element) {
        if (head == null) {
            head = new LinearNode<>(element);
        } else {
            LinearNode<T> novoElemento = new LinearNode<>(element);
            LinearNode<T> elementoAtual = this.head;

            while (elementoAtual.getNext() != null) {
                elementoAtual = elementoAtual.getNext();
            }
            elementoAtual.setNext(novoElemento);
            this.tail = elementoAtual.getNext();
            this.tail.setPrevious(elementoAtual);
        }
        this.numberElements++;
    }

    /**
     *
     * @return @throws EmptyCollectionException
     */
    @Override
    public T removeFirst() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("The list is empty");
        }

        LinearNode<T> node = this.head;
        this.head = this.head.getNext();

        if (this.head == null) {
            this.tail = null;
        }
        this.numberElements--;
        return node.getElement();
    }

    /**
     *
     * @return @throws EmptyCollectionException
     */
    @Override
    public T removeLast() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("The list is empty");
        }

        LinearNode<T> currentNode = this.head;
        LinearNode<T> previousNode = null;

        while (currentNode.getNext() != null) {
            previousNode = currentNode;
            currentNode = currentNode.getNext();
        }
        LinearNode<T> node = this.tail;
        this.tail = previousNode;

        if (this.tail == null) {
            this.head = null;
        } else {
            this.tail.setNext(null);
        }
        this.numberElements--;
        return node.getElement();
    }

    /**
     *
     * @param target
     * @return
     * @throws EmptyCollectionException
     */
    @Override
    public T remove(T target) throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("The list is empty");
        }

        boolean found = false;

        LinearNode<T> currentNode = this.head;
        LinearNode<T> previousNode = null;

        while (currentNode != null && !found) {
            if (target.equals(currentNode.getElement())) {
                found = true;
            } else {
                previousNode = currentNode;
                currentNode = currentNode.getNext();
            }
        }

        if (!found) {
            throw new EmptyCollectionException("The element was not found");
        }

        if (size() == 1) {
            this.head = null;
            this.tail = null;
        } else if (currentNode.equals(this.head)) {
            this.head = currentNode.getNext();
        } else if (currentNode.equals(this.tail)) {
            this.tail = previousNode;
            this.tail.setNext(null);
        } else {
            previousNode.setNext(currentNode.getNext());
        }

        this.numberElements--;

        return currentNode.getElement();
    }

    /**
     *
     * @return
     */
    @Override
    public T first() {
        return this.head.getElement();
    }

    /**
     *
     * @return
     */
    @Override
    public T last() {
        return this.tail.getElement();
    }

    /**
     *
     * @param target
     * @return
     * @throws EmptyCollectionException
     */
    @Override
    public boolean contains(T target) throws EmptyCollectionException {

        if (isEmpty()) {
            throw new EmptyCollectionException("The list is empty");
        }

        boolean found = false;

        LinearNode<T> currentNode = this.head;

        while (currentNode != null && !found) {
            if (target.equals(currentNode.getElement())) {
                found = true;
            } else {
                currentNode = currentNode.getNext();
            }
        }
        return found;
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return this.numberElements == 0;
    }

    /**
     *
     * @return
     */
    @Override
    public int size() {
        return this.numberElements;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedIterator<T>(this.head);
    }

    @Override
    public String toString() {
        LinearNode<T> currentNode = this.head;
        String result = "";

        while (currentNode != null) {
            result = result + (currentNode.getElement().toString()) + "\n";
            currentNode = currentNode.getNext();
        }
        return result;
    }

    /**
     *
     * @param node
     */
    public void printRecursive(LinearNode<T> node) {

        if (node == null) {
            return;
        } else {

            System.out.println(node.getElement());
            printRecursive(node.getNext());
        }
    }

    /**
     *
     * @return
     */
    public LinearNode<T> getHead() {
        return this.head;
    }

    /**
     *
     * @param existingItem
     * @param newItem
     * @throws EmptyCollectionException
     */
    public void replace(T existingItem, T newItem) throws EmptyCollectionException {
        LinearNode<T> node = new LinearNode<>(existingItem);
        LinearNode<T> search = this.head;
        if (!this.contains(existingItem)) {
            return;
        }
        while (search != null) {
            if (search.getElement().equals(existingItem)) {
                search.setElement(newItem);
            } else {
                replace(node.getNext().getElement(), newItem);
            }
        }

    }

    /**
     *
     * @return
     */
    public LinkedList<T> reverseIt() {
        LinearNode<T> rev = reverseOrder(this.getTail());
        return new LinkedList<>(rev);
    }

    private LinearNode<T> reverseOrder(LinearNode<T> node) {

        if (node == null) {
            return null;
        } else {
            LinearNode<T> newN = new LinearNode<>(node.getElement());
            newN.setNext(reverseOrder(node.getPrevious()));
            if (newN.getNext() != null) {
                newN.getNext().setPrevious(newN);
            }
            return newN;
        }
    }

    /**
     *
     * @return
     */
    public LinearNode<T> getTail() {
        return tail;
    }
}
