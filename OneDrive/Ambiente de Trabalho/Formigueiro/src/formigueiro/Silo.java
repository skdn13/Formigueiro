/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formigueiro;

import colecoes.ArrayUnorderedList;
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

    private UnorderedListADT<IFormiga> formigas;
    private colecoes.ArrayUnorderedList<Comida> comida;

    public Silo(int id, int x, int y, String descricao) {
        super(id, x, y, descricao);
        formigas = new colecoes.ArrayUnorderedList<>();
        comida = new colecoes.ArrayUnorderedList<>();
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
        return "Silo{" + super.toString() + '}';
    }

}
