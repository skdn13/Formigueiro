/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formigueiro;

import java.util.ArrayList;
import java.util.Iterator;
import recursos.exceptions.ElementNotFoundException;
import recursos.interfaces.IComida;
import recursos.interfaces.IFormiga;
import recursos.interfaces.IFormigueiro;
import recursos.interfaces.IPair;
import recursos.interfaces.IProcessamento;
import recursos.interfaces.ISala;
import recursos.interfaces.ISilo;
import recursos.interfaces.ITunel;

/**
 *
 * @author pmms8
 */
public class Formigueiro implements IFormigueiro {

    private Sala entrada;
    private Formiga formiga;
    private Comida comida;
    private Processamento processamento;
    private Silo silo;
    private int maxX;
    private int maxY;

    public Formigueiro(Sala entrada, int maxX, int maxY) {
        this.entrada = entrada;
        this.maxX = maxX;
        this.maxY = maxY;
    }

    @Override
    public ISala getEntrada() {
        return this.entrada;
    }

    @Override
    public void setEntrada(ISala isala) {
        this.entrada = (Sala) isala;
    }

    @Override
    public Iterator<ISala> iterator() {
        return new ArrayList<ISala>().iterator();
    }

    @Override
    public Iterator<ISala> iteratorBFS(ISala isala) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iterator<ISala> iteratorBFS() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addSala(ISala isala) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ligaSala(ISala isala, ISala isala1, int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getMaxY() {
        return this.maxY;
    }

    @Override
    public int getMaxX() {
        return this.maxX;
    }

    @Override
    public Iterator<IPair<ISala, ITunel>> ligacoesDe(ISala isala) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ISala getSalaFormiga(int i) throws ElementNotFoundException {
        return this.entrada;
    }

    @Override
    public IFormiga getFormiga(int i) throws ElementNotFoundException {
        return this.formiga;
    }

    @Override
    public ISala getSala(int i) throws ElementNotFoundException {
        return this.entrada;
    }

    @Override
    public boolean vizinhos(ISala isala, ISala isala1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iterator<ISala> iteratorShortestPath(int i, int i1) throws ElementNotFoundException {
        return null;
    }

    @Override
    public Iterator<ISala> iteratorMoveFormigaShortestPath(int i, int i1) throws ElementNotFoundException {
        return null;
    }

    @Override
    public Iterator<ISala> iteratorCarregaEMoveFormigaShortestPath(int i, int i1) throws ElementNotFoundException {
        return null;
    }

    @Override
    public int custoDoCaminho(Iterator<ISala> itrtr) {
        return 0;
    }

    @Override
    public IFormiga criaFormiga(int i, int i1) {
        return this.formiga;
    }

    @Override
    public IComida criaComida(int i, int i1) {
        return this.comida;
    }

    @Override
    public ISala criaSala(int i, String string, int i1, int i2) {
        return this.entrada;
    }

    @Override
    public ISilo criaSilo(int i, String string, int i1, int i2) {
        return this.silo;
    }

    @Override
    public IProcessamento criaProcessamento(int i, String string, int i1, int i2) {
        return this.processamento;
    }

}
