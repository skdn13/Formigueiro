/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formigueiro;

import java.util.Iterator;
import recursos.exceptions.ElementNotFoundException;
import recursos.exceptions.EmptyCollectionException;
import recursos.exceptions.FormigaCheiaException;
import recursos.interfaces.IComida;
import recursos.interfaces.IFormiga;
import recursos.interfaces.collections.UnorderedListADT;

/**
 *
 * @author pmms8
 */
public class Formiga implements IFormiga {

    private UnorderedListADT<IComida> comida;
    private int capacidadeCarga, id, carga;

    /**
     *
     * @param carga
     */
    public void setCarga(int carga) {
        this.carga = carga;
    }

    /**
     *
     * @param id
     * @param capacidadeCarga
     */
    public Formiga(int id, int capacidadeCarga) {
        this.capacidadeCarga = capacidadeCarga;
        this.id = id;
        this.carga = 0;
        this.comida = new colecoes.ArrayUnorderedList<>();
    }

    /**
     *
     * @return
     */
    @Override
    public int getCapacidadeCarga() {
        return this.capacidadeCarga;
    }

    /**
     *
     * @param i
     */
    @Override
    public void setCapacidadeCarga(int i) {
        this.capacidadeCarga = i;
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
     * @param ic
     * @throws FormigaCheiaException
     */
    @Override
    public void addComida(IComida ic) throws FormigaCheiaException {
        this.comida.addToRear((Comida) ic);
        if (this.carga >= this.capacidadeCarga) {
            throw new FormigaCheiaException();
        }
    }

    /**
     *
     * @param position
     * @return
     * @throws ElementNotFoundException
     */
    public IComida getComida(int position) throws ElementNotFoundException {
        Iterator<IComida> it = this.comida.iterator();
        while (it.hasNext()) {
            IComida next = it.next();
            if (next.getId() == position) {
                return next;
            }
        }
        throw new recursos.exceptions.ElementNotFoundException("Comida não existe");
    }

    /**
     *
     * @param i
     * @return
     * @throws EmptyCollectionException
     * @throws ElementNotFoundException
     */
    @Override
    public IComida removeComida(int i) throws EmptyCollectionException, ElementNotFoundException {
        Comida comida = (Comida) this.getComida(i);
        return this.comida.remove(comida);
    }

    /**
     *
     * @return @throws EmptyCollectionException
     */
    @Override
    public IComida removeComida() throws EmptyCollectionException {
        return this.comida.removeLast();
    }

    /**
     *
     * @throws ElementNotFoundException
     */
    public void removeTodasAsComidas() throws ElementNotFoundException {
        Iterator<IComida> it = this.comida.iterator();
        while (it.hasNext()) {
            IComida next = it.next();
            this.comida.remove(next);
        }
        this.setCarga(0);
    }

    /**
     *
     * @return
     */
    public UnorderedListADT<IComida> listarComidas() {
        return this.comida;
    }

    /**
     *
     * @return
     */
    @Override
    public int getCarga() {
        return this.carga;
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
        final Formiga other = (Formiga) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Formiga{" + "capacidadeCarga=" + capacidadeCarga + ", id=" + id + ", carga=" + carga + '}';
    }

}
