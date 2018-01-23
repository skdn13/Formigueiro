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
public class LinkedQueue<T> implements QueueADT<T> {

    private int numberOfElements;
    private LinearNode<T> head;
    private LinearNode<T> tail;

    public LinkedQueue() {
        this.numberOfElements = 0;
        this.head = null;
        this.tail = null;
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
        String output = "";
        LinearNode<T> elementoAtual = this.head;

        if (elementoAtual != null) {
            while (elementoAtual != null) {
                output += elementoAtual.getElement() + " -> ";
                elementoAtual = elementoAtual.getNext();
            }
        }
        return output;
    }

    @Override
    public void enqueue(T element) {

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
        this.numberOfElements++;
    }

    public void enqueueFirst(T element) {
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

    public T dequeueLast() throws recursos.exceptions.EmptyCollectionException {
        T element = null;
        LinearNode<T> atual = this.head;
        LinearNode<T> atual2 = this.tail;
        switch (this.numberOfElements) {
            case 0:
                throw new recursos.exceptions.EmptyCollectionException("Não há elementos a eliminar!");
            case 1:
                element = atual.getElement();
                this.head = null;
                break;
            default:
                element = atual2.getElement();
                while (atual.getNext() != null) {
                    atual = atual.getNext();
                }
                atual.getPrevious().setNext(null);
                atual = tail.getPrevious();
                this.tail = atual;
                break;
        }
        this.numberOfElements--;
        return element;
    }

    @Override
    public T first() throws recursos.exceptions.EmptyCollectionException {
        if (isEmpty()) {
            throw new recursos.exceptions.EmptyCollectionException("Queue Vazia");
        }
        return this.head.getElement();
    }

    public T last() throws recursos.exceptions.EmptyCollectionException {
        if (isEmpty()) {
            throw new recursos.exceptions.EmptyCollectionException("Queue Vazia");
        }
        return this.tail.getElement();
    }

    public LinkedIterator<?> getIterator() {
        return new LinkedIterator<>(this.head);
    }
}
