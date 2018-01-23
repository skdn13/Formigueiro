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

    public Processamento(int id, int x, int y, String descricao) {
        super(id, x, y, descricao);
        formigas = new colecoes.ArrayUnorderedList<>();
        comida = new colecoes.LinkedQueue<>();
    }

    @Override
    public void acrescentaComida(IComida ic) {
        this.comida.enqueue((Comida) ic);
    }

    @Override
    public IComida getProximaComida() throws EmptyCollectionException, ProcessedException {
        if (this.comida.isEmpty()) {
            throw new EmptyCollectionException("Queue vazia!");
        }
        int tamanho = 0;
        if (this.comida.size() == 1) {
            tamanho = this.comida.first().getTamanho();
        } else {
            tamanho = this.comida.last().getTamanho();
        }
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

    public IComida retiraComida() throws recursos.exceptions.EmptyCollectionException {
        if (this.comida.isEmpty()) {
            throw new EmptyCollectionException("Sem comidas em queue!");
        } else {
            return this.comida.dequeue();
        }
    }

    public int getNumeroComidas() {
        return this.comida.size();
    }

    @Override
    public Iterator<IComida> iteratorComida() {
        return (Iterator<IComida>) this.comida.getIterator();
    }

    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public void setId(int i) {
        super.setId(i);
    }

    @Override
    public int getX() {
        return super.getX();
    }

    @Override
    public void setX(int i) {
        super.setX(i);
    }

    @Override
    public int getY() {
        return super.getY();
    }

    @Override
    public void setY(int i) {
        super.setY(i);
    }

    @Override
    public String getDescricao() {
        return super.getDescricao();
    }

    @Override
    public void setDescricao(String string) {
        super.setDescricao(string);
    }

    @Override
    public void entraFormiga(IFormiga i) {
        this.formigas.addToRear((Formiga) i);
    }

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

    @Override
    public UnorderedListADT<IFormiga> listaFormigas() {
        return this.formigas;
    }

    @Override
    public String toString() {
        return "Processamento{" + super.toString() + '}';
    }

}
