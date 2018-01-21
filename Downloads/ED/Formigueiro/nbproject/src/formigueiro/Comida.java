/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formigueiro;

import recursos.interfaces.IComida;

/**
 *
 * @author pmms8
 */
public class Comida implements IComida {

    private int id;
    private int tamanho;

    public Comida(int id, int tamanho) {
        this.id = id;
        this.tamanho = tamanho;
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
    public int getTamanho() {
        return this.tamanho;
    }

    @Override
    public void setTamanho(int i) {
        this.tamanho = i;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + this.id;
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
        final Comida other = (Comida) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Comida{" + "id=" + id + ", tamanho=" + tamanho + '}';
    }

}
