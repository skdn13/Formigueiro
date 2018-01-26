/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colecoes;

import recursos.exceptions.EmptyCollectionException;
import recursos.interfaces.collections.StackADT;

/**
 *
 * @author pmms8
 * @param <T>
 */
public class LinkedStack<T> implements StackADT<T> {

    private int numberOfElements;
    private LinearNode<T> head;

    /**
     *
     */
    public LinkedStack() {
        this.numberOfElements = 0;
        this.head = null;
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
    public String toString() {
        this.printAll();
        return "";
    }

    /**
     *
     */
    public void printAll() {
        LinearNode<T> elementoAtual = this.head;

        if (elementoAtual != null) {
            while (elementoAtual.getNext() != null) {
                System.out.print(elementoAtual.getElement() + " -> ");
                elementoAtual = elementoAtual.getNext();
            }
            System.out.println(elementoAtual.getElement());
        }
    }

    /**
     *
     * @param element
     */
    @Override
    public void push(T element) {
        if (head == null) {
            head = new LinearNode<>(element);
        } else {
            LinearNode<T> novoElemento = new LinearNode<>(element);
            novoElemento.setNext(this.head);
            this.head = novoElemento;
        }
        this.numberOfElements++;
    }

    /**
     *
     * @return
     * @throws EmptyCollectionException
     */
    @Override
    public T pop() throws recursos.exceptions.EmptyCollectionException {
        T element = null;
        LinearNode<T> atual = this.head;
        switch (this.numberOfElements) {
            case 0:
                throw new recursos.exceptions.EmptyCollectionException("Não há elementos a eliminar!");
            case 1:
                element = atual.getElement();
                this.head = null;
                break;
            default:
                element = atual.getElement();
                this.head = this.head.getNext();
                break;
        }
        this.numberOfElements--;
        return element;
    }

    /**
     *
     * @return
     * @throws EmptyCollectionException
     */
    @Override
    public T peek() throws recursos.exceptions.EmptyCollectionException {
        if (isEmpty()) {
            throw new recursos.exceptions.EmptyCollectionException("Stack Vazia");
        }
        return this.head.getElement();
    }

    /**
     *
     * @return
     */
    public LinkedIterator<?> getIterator() {
        return new LinkedIterator<>(this.head);
    }
}
