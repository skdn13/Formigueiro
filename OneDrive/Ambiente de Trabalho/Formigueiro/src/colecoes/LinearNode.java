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
public class LinearNode<T> {

    private LinearNode<T> next;
    private LinearNode<T> previous;
    private T element;

    /**
     *
     */
    public LinearNode() {
        this.next = null;
        this.element = null;
    }

    /**
     *
     * @param element
     */
    public LinearNode(T element) {
        this.next = null;
        this.element = element;
    }

    /**
     *
     * @return
     */
    public LinearNode<T> getNext() {
        return next;
    }

    /**
     *
     * @param next
     */
    public void setNext(LinearNode<T> next) {
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
    public LinearNode<T> getPrevious() {
        return previous;
    }

    /**
     *
     * @param previous
     */
    public void setPrevious(LinearNode<T> previous) {
        this.previous = previous;
    }

}
