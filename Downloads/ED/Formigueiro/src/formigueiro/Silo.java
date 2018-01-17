/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formigueiro;

import java.util.Iterator;
import recursos.exceptions.ElementNotFoundException;
import recursos.exceptions.EmptyCollectionException;
import recursos.interfaces.IComida;
import recursos.interfaces.IFormiga;
import recursos.interfaces.ISilo;
import recursos.interfaces.collections.UnorderedListADT;

/**
 *
 * @author pmms8
 */
public class Silo extends Sala implements ISilo {

    private colecoes.ArrayUnorderedList<Formiga> formigas;
    private colecoes.ArrayUnorderedList<Comida> comida;

    public Silo(int id, int x, int y, String descricao) {
        super(id, x, y, descricao);
    }

    @Override
    public void guardaComida(IComida ic) {
        this.comida.addToRear((Comida) ic);
    }

    @Override
    public IComida retiraComida() throws recursos.exceptions.EmptyCollectionException {
        return this.comida.removeLast();
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
        return this.formigas.removeLast();
    }

    @Override
    public UnorderedListADT<IFormiga> listaFormigas() {
        return (UnorderedListADT<IFormiga>) this.formigas.getIterator();
    }

    @Override
    public String toString() {
        return "Silo{" + super.toString() + '}';
    }

}
