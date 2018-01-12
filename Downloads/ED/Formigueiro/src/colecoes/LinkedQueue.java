/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colecoes;

import Excecoes.EmptyCollectionException;
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

    @Override
    public T dequeue() throws EmptyCollectionException {
        T element = null;
        LinearNode<T> atual = this.head;
        switch (this.numberOfElements) {
            case 0:
                throw new EmptyCollectionException("Não há elementos a eliminar!");
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
    public T first() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("Queue Vazia");
        }
        return this.head.getElement();
    }
}
