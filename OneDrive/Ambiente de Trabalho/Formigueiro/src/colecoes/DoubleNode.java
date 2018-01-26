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

    public DoubleNode() {
        this.next = null;
        this.element = null;
    }

    public DoubleNode(T element) {
        this.next = null;
        this.element = element;
    }

    public DoubleNode<T> getNext() {
        return next;
    }

    public void setNext(DoubleNode<T> next) {
        this.next = next;
    }

    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }

    @Override
    public String toString() {
        return "LinearNode{" + "next=" + next + ", previous=" + previous + ", element=" + element + '}';
    }

    public DoubleNode<T> getPrevious() {
        return previous;
    }

    public void setPrevious(DoubleNode<T> previous) {
        this.previous = previous;
    }
}
