/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formigueiro;

import colecoes.LinkedQueue;
import java.util.Iterator;
import recursos.exceptions.ElementNotFoundException;
import recursos.exceptions.EmptyCollectionException;
import recursos.exceptions.ProcessedException;
import recursos.interfaces.IComida;
import recursos.interfaces.IFormiga;
import recursos.interfaces.IProcessamento;
import recursos.interfaces.collections.UnorderedListADT;

/**
 *
 * @author pmms8
 */
public class Processamento extends Sala implements IProcessamento {

    private UnorderedListADT<IFormiga> formigas;
    private LinkedQueue<Comida> comida;
    private int count = 1;

    /**
     *
     * @param id
     * @param x
     * @param y
     * @param descricao
     */
    public Processamento(int id, int x, int y, String descricao) {
        super(id, x, y, descricao);
        formigas = new colecoes.DoubleLinkedUnorderedList<>();
        comida = new colecoes.LinkedQueue<>();
    }

    /**
     *
     * @param ic
     */
    @Override
    public void acrescentaComida(IComida ic) {
        this.comida.enqueue((Comida) ic);
    }

    /**
     *
     * @return
     * @throws EmptyCollectionException
     * @throws ProcessedException
     */
    @Override
    public IComida getProximaComida() throws EmptyCollectionException, ProcessedException {
        if (this.comida.isEmpty()) {
            throw new EmptyCollectionException("Queue vazia!");
        }
        int tamanho = this.comida.first().getTamanho();
        if (tamanho == 1) {
            return this.retiraComida();
        } else {
            Comida retirada = (Comida) this.retiraComida();
            for (int i = 0; i < tamanho; i++) {
                Comida c = new Comida(retirada.getId() + i + count, 1);
                count++;
                this.acrescentaComida(c);
            }
            throw new ProcessedException(retirada);
        }
    }

    /**
     *
     * @return
     * @throws EmptyCollectionException
     */
    public IComida retiraComida() throws recursos.exceptions.EmptyCollectionException {
        if (this.comida.isEmpty()) {
            throw new EmptyCollectionException("Sem comidas em queue!");
        } else {
            return this.comida.dequeue();
        }
    }

    /**
     *
     * @return
     */
    public int getNumeroComidas() {
        return this.comida.size();
    }

    /**
     *
     * @return
     */
    @Override
    public Iterator<IComida> iteratorComida() {
        return (Iterator<IComida>) this.comida.getIterator();
    }

    /**
     *
     * @return
     */
    @Override
    public int getId() {
        return super.getId();
    }

    /**
     *
     * @param i
     */
    @Override
    public void setId(int i) {
        super.setId(i);
    }

    /**
     *
     * @return
     */
    @Override
    public int getX() {
        return super.getX();
    }

    /**
     *
     * @param i
     */
    @Override
    public void setX(int i) {
        super.setX(i);
    }

    /**
     *
     * @return
     */
    @Override
    public int getY() {
        return super.getY();
    }

    /**
     *
     * @param i
     */
    @Override
    public void setY(int i) {
        super.setY(i);
    }

    /**
     *
     * @return
     */
    @Override
    public String getDescricao() {
        return super.getDescricao();
    }

    /**
     *
     * @param string
     */
    @Override
    public void setDescricao(String string) {
        super.setDescricao(string);
    }

    /**
     *
     * @param i
     */
    @Override
    public void entraFormiga(IFormiga i) {
        this.formigas.addToRear((Formiga) i);
    }

    /**
     *
     * @param i
     * @return
     * @throws EmptyCollectionException
     * @throws ElementNotFoundException
     */
    @Override
    public IFormiga saiFormiga(int i) throws EmptyCollectionException, ElementNotFoundException {
        if (this.listaFormigas().isEmpty()) {
            throw new EmptyCollectionException("lista de formigas");
        }
        while (this.listaFormigas().iterator().hasNext()) {
            IFormiga formiga = this.listaFormigas().iterator().next();
            if (i == formiga.getId()) {
                return this.formigas.remove(formiga);
            }
        }
        throw new ElementNotFoundException("Formiga não existe!");
    }

    /**
     *
     * @return
     */
    @Override
    public UnorderedListADT<IFormiga> listaFormigas() {
        return this.formigas;
    }

    @Override
    public String toString() {
        return "Processamento{" + super.toString() + '}';
    }

}
