/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formigueiro;

import java.util.Iterator;
import recursos.exceptions.ElementNotFoundException;
import recursos.exceptions.EmptyCollectionException;
import recursos.interfaces.IFormiga;
import recursos.interfaces.ISala;
import recursos.interfaces.collections.UnorderedListADT;

/**
 *
 * @author pmms8
 */
public class Sala implements ISala {

    private int id, x, y;
    private UnorderedListADT<IFormiga> formigas;
    private String descricao;

    /**
     *
     * @param id
     * @param x
     * @param y
     * @param descricao
     */
    public Sala(int id, int x, int y, String descricao) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.descricao = descricao;
        this.formigas = new colecoes.DoubleLinkedUnorderedList<>();
    }

    /**
     *
     * @return
     */
    @Override
    public int getId() {
        return this.id;
    }

    /**
     *
     * @param i
     */
    @Override
    public void setId(int i) {
        this.id = i;
    }

    /**
     *
     * @return
     */
    @Override
    public int getX() {
        return this.x;
    }

    /**
     *
     * @param i
     */
    @Override
    public void setX(int i) {
        this.x = i;
    }

    /**
     *
     * @return
     */
    @Override
    public int getY() {
        return this.y;
    }

    /**
     *
     * @param i
     */
    @Override
    public void setY(int i) {
        this.y = i;
    }

    /**
     *
     * @return
     */
    @Override
    public String getDescricao() {
        return this.descricao;
    }

    /**
     *
     * @param string
     */
    @Override
    public void setDescricao(String string) {
        this.descricao = string;
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
        if (this.formigas.isEmpty()) {
            throw new EmptyCollectionException("lista de formigas");
        }

        Iterator<IFormiga> iterador = this.formigas.iterator();
        while (iterador.hasNext()) {
            IFormiga formiga = iterador.next();
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
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Sala other = (Sala) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Sala{" + "id=" + id + ", x=" + x + ", y=" + y + ", descricao=" + descricao + '}';
    }

}
