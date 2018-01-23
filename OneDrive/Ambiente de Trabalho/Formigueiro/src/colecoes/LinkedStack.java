/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colecoes;

import recursos.interfaces.collections.QueueADT;

/**
 *
 * @author pmms8
 * @param <T>
 */
public class LinkedStack<T> implements QueueADT<T> {

    private int numberOfElements;
    private LinearNode<T> head;

    public LinkedStack() {
        this.numberOfElements = 0;
        this.head = null;
    }

    @Override
    public boolean isEmpty() {
        return this.numberOfElements == 0;
    }

    @Override
    public int size() {
        return this.numberOfElements;
    }

    @Override
    public String toString() {
        this.printAll();
        return "";
    }

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

    @Override
    public void enqueue(T element) {
        if (head == null) {
            head = new LinearNode<>(element);
        } else {
            LinearNode<T> novoElemento = new LinearNode<>(element);
            novoElemento.setNext(this.head);
            this.head = novoElemento;
        }
        this.numberOfElements++;
    }

    @Override
    public T dequeue() throws recursos.exceptions.EmptyCollectionException {
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

    @Override
    public T first() throws recursos.exceptions.EmptyCollectionException {
        if (isEmpty()) {
            throw new recursos.exceptions.EmptyCollectionException("Stack Vazia");
        }
        return this.head.getElement();
    }

    public LinkedIterator<?> getIterator() {
        return new LinkedIterator<>(this.head);
    }
}
