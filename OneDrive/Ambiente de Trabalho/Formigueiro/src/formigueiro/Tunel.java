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

    /**
     *
     * @param distance
     * @param radious
     * @param id
     */
    public Tunel(int distance, int radious, int id) throws Exception {
        this.distance = distance;
        if(radious < 1){
            throw new Exception("Raio menor que 1");
        }
        this.radious = radious;
        this.id = id;
    }

    /**
     *
     * @return
     */
    @Override
    public int getDistance() {
        return this.distance;
    }

    /**
     *
     * @param i
     */
    @Override
    public void setDistance(int i) {
        this.distance = i;
    }

    /**
     *
     * @return
     */
    @Override
    public int getRadious() {
        return this.radious;
    }

    /**
     *
     * @param i
     */
    @Override
    public void setRadious(int i) {
        this.radious = i;
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
