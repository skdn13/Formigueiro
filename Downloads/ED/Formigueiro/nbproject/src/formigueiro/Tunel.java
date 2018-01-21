/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formigueiro;

import recursos.interfaces.ITunel;

/**
 *
 * @author pmms8
 */
public class Tunel implements ITunel {

    private int distance;
    private int radious;
    private int id;

    public Tunel(int distance, int radious, int id) {
        this.distance = distance;
        this.radious = radious;
        this.id = id;
    }

    @Override
    public int getDistance() {
        return this.distance;
    }

    @Override
    public void setDistance(int i) {
        this.distance = i;
    }

    @Override
    public int getRadious() {
        return this.radious;
    }

    @Override
    public void setRadious(int i) {
        this.radious = i;
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
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + this.id;
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
        final Tunel other = (Tunel) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Tunel{" + "distance=" + distance + ", radious=" + radious + ", id=" + id + '}';
    }

}
