/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colecoes;

/**
 *
 * @author pmms8
 * @param <T>
 */
public class DoubleNode<T> {

    private DoubleNode<T> next;
    private DoubleNode<T> previous;
    private T element;

    /**
     *
     */
    public DoubleNode() {
        this.next = null;
        this.element = null;
    }

    /**
     *
     * @param element
     */
    public DoubleNode(T element) {
        this.next = null;
        this.element = element;
    }

    /**
     *
     * @return
     */
    public DoubleNode<T> getNext() {
        return next;
    }

    /**
     *
     * @param next
     */
    public void setNext(DoubleNode<T> next) {
        this.next = next;
    }

    /**
     *
     * @return
     */
    public T getElement() {
        return element;
    }

    /**
     *
     * @param element
     */
    public void setElement(T element) {
        this.element = element;
    }

    @Override
    public String toString() {
        return "LinearNode{" + "next=" + next + ", previous=" + previous + ", element=" + element + '}';
    }

    /**
     *
     * @return
     */
    public DoubleNode<T> getPrevious() {
        return previous;
    }

    /**
     *
     * @param previous
     */
    public void setPrevious(DoubleNode<T> previous) {
        this.previous = previous;
    }
}
