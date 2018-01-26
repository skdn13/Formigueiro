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

    public void setCarga(int carga) {
        this.carga = carga;
    }

    public Formiga(int id, int capacidadeCarga) {
        this.capacidadeCarga = capacidadeCarga;
        this.id = id;
        this.carga = 0;
        this.comida = new colecoes.ArrayUnorderedList<>();
    }

    @Override
    public int getCapacidadeCarga() {
        return this.capacidadeCarga;
    }

    @Override
    public void setCapacidadeCarga(int i) {
        this.capacidadeCarga = i;
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
    public void addComida(IComida ic) throws FormigaCheiaException {
        this.comida.addToRear((Comida) ic);
        if (this.carga >= this.capacidadeCarga) {
            throw new FormigaCheiaException();
        }
    }

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

    @Override
    public IComida removeComida(int i) throws EmptyCollectionException, ElementNotFoundException {
        Comida comida = (Comida) this.getComida(i);
        return this.comida.remove(comida);
    }

    @Override
    public IComida removeComida() throws EmptyCollectionException {
        return this.comida.removeLast();
    }

    public void removeTodasAsComidas() throws ElementNotFoundException {
        Iterator<IComida> it = this.comida.iterator();
        while (it.hasNext()) {
            IComida next = it.next();
            this.comida.remove(next);
        }
        this.setCarga(0);
    }

    public UnorderedListADT<IComida> listarComidas() {
        return this.comida;
    }

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
