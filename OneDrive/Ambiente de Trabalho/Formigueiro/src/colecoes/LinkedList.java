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
public class LinkedList<T> {

    private LinearNode<T> head;
    private int contador;

    public LinkedList() {
        this.head = null;
        this.contador = 0;
    }

    public void add(T dados) {

        if (head == null) {
            head = new LinearNode<>(dados);
        } else {
            LinearNode<T> novoElemento = new LinearNode<>(dados);
            LinearNode<T> elementoAtual = this.head;
            while (elementoAtual.getNext() != null) {
                elementoAtual = elementoAtual.getNext();
            }
            elementoAtual.setNext(novoElemento);
        }
        this.contador++;
    }

    public void remove(int indice) throws Exception {

        if (indice < 0 || indice >= this.contador) {
            throw new Exception("Indice incorreto!");
        }
        if (indice == 0) {
            this.head = this.head.getNext();
        } else {
            LinearNode<T> anterior = this.head;
            for (int i = 0; i < indice - 1; i++) {
                anterior = anterior.getNext();

            }
            if (indice == contador - 1) {
                anterior.setNext(null);
            } else {
                anterior.setNext(anterior.getNext().getNext());
            }
        }
        this.contador--;
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

    public T getData(int indice) throws Exception {
        if (indice < 0 || indice >= this.contador) {
            throw new Exception("Indice incorreto!");
        }
        LinearNode<T> elementoAtual = this.head;
        for (int i = 0; i < indice; i++) {
            elementoAtual = elementoAtual.getPrevious();
        }
        return elementoAtual.getElement();
    }

    public int dataExists(T dados) throws Exception {

        LinearNode<T> elementoAtual = this.head;

        for (int i = 0; i < this.contador; i++) {
            if (elementoAtual.getElement().equals(dados)) {
                return i;
            } else {
                elementoAtual = elementoAtual.getNext();
            }
        }
        throw new Exception("Elemento não existe!");
    }

    public boolean isEmpty() {
        return this.contador == 0;
    }
    public int size(){
        return this.contador;
    }
}
