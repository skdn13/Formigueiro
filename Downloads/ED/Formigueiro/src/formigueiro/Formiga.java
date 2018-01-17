/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formigueiro;

import recursos.exceptions.ElementNotFoundException;
import recursos.exceptions.EmptyCollectionException;
import recursos.exceptions.FormigaCheiaException;
import recursos.interfaces.IComida;
import recursos.interfaces.IFormiga;

/**
 *
 * @author pmms8
 */
public class Formiga implements IFormiga {

    private colecoes.ArrayUnorderedList<Comida> comida;
    private int capacidadeCarga;
    private int id;
    private int carga;

    public Formiga(int capacidadeCarga, int id, int carga) {
        this.capacidadeCarga = capacidadeCarga;
        this.id = id;
        this.carga = carga;
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
    }

    @Override
    public IComida removeComida(int i) throws EmptyCollectionException, ElementNotFoundException {
        return this.comida.removeLast();
    }

    @Override
    public IComida removeComida() throws EmptyCollectionException {
        return this.comida.removeLast();
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
