/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formigueiro;

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
public class Processamento implements IProcessamento {

    private int id, x, y;
    private String descricao;
    private Formiga formiga;
    private Comida comida;

    public Processamento(int id, int x, int y, String descricao) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.descricao = descricao;
    }

    @Override
    public void acrescentaComida(IComida ic) {
        this.comida = (Comida) ic;
    }

    @Override
    public IComida getProximaComida() throws EmptyCollectionException, ProcessedException {
        return this.comida;
    }

    @Override
    public Iterator<IComida> iteratorComida() {
        return null;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public void setId(int i) {
        this.id = i;
    }

    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public void setX(int i) {
        this.x = i;
    }

    @Override
    public int getY() {
        return this.y;
    }

    @Override
    public void setY(int i) {
        this.y = i;
    }

    @Override
    public String getDescricao() {
        return this.descricao;
    }

    @Override
    public void setDescricao(String string) {
        this.descricao = string;
    }

    @Override
    public void entraFormiga(IFormiga i) {
        this.formiga = (Formiga) i;
    }

    @Override
    public IFormiga saiFormiga(int i) throws EmptyCollectionException, ElementNotFoundException {
        return this.formiga;
    }

    @Override
    public UnorderedListADT<IFormiga> listaFormigas() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
