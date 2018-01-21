/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formigueiro;

import recursos.interfaces.IPair;

/**
 *
 * @author pmms8
 */
public class Pair implements IPair {

    private Object first;
    private Object second;

    public Pair(Object first, Object second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public Object getFirst() {
        return this.first;
    }

    @Override
    public Object getSecond() {
        return this.second;
    }

    @Override
    public String toString() {
        return "Pair{" + "first=" + first + ", second=" + second + '}';
    }
    
}
