/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formigueiro;

import colecoes.ArrayIterator;
import colecoes.Network;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import recursos.exceptions.ElementNotFoundException;
import recursos.interfaces.IComida;
import recursos.interfaces.IFormiga;
import recursos.interfaces.IFormigueiro;
import recursos.interfaces.IPair;
import recursos.interfaces.IProcessamento;
import recursos.interfaces.ISala;
import recursos.interfaces.ISilo;
import recursos.interfaces.ITunel;
import recursos.interfaces.collections.NetworkADT;

/**
 *
 * @author pmms8
 */
public class Formigueiro implements IFormigueiro {

    private Sala entrada;
    private Processamento processamento;
    private colecoes.ArrayUnorderedList<Sala> salas;
    private colecoes.ArrayUnorderedList<Formiga> formigas;
    private Silo silo;
    private colecoes.Graph<ISala> grafo;
    private NetworkADT<ISala> network;
    private int maxX;
    private int maxY;

    public Formigueiro(Sala entrada, int maxX, int maxY) {
        formigas = new colecoes.ArrayUnorderedList<>();
        salas = new colecoes.ArrayUnorderedList<>();
        this.network = new Network<>();
        this.entrada = entrada;
        this.salas.addToRear(entrada);
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
        return (Iterator<ISala>) this.salas.getIterator();
    }

    @Override
    public Iterator<ISala> iteratorBFS(ISala isala) {
        return grafo.iteratorBFS(entrada);
    }

    @Override
    public Iterator<ISala> iteratorBFS() {
        return grafo.iteratorBFS(entrada);
    }

    @Override
    public void addSala(ISala isala) {
        this.salas.addToFront((Sala) isala);
    }

    @Override
    public void ligaSala(ISala isala, ISala isala1, int i) {
        Tunel tunel = new Tunel(100, i, 1);
        try {
            this.network.addEdge(isala, isala1, tunel);
        } catch (ElementNotFoundException ex) {
            ex.printStackTrace();
        }
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
        return new ArrayList<IPair<ISala, ITunel>>().iterator();
    }

    @Override
    public ISala getSalaFormiga(int i) throws ElementNotFoundException {
        return this.entrada;
    }

    @Override
    public IFormiga getFormiga(int i) throws ElementNotFoundException {
        return this.formigas.first();
    }

    @Override
    public ISala getSala(int i) throws ElementNotFoundException {
        return this.entrada;
    }

    @Override
    public boolean vizinhos(ISala isala, ISala isala1) {
        return ((isala.getX() == isala1.getX()) || (isala.getY() == isala1.getY()));
    }

    @Override
    public Iterator<ISala> iteratorShortestPath(int i, int i1) throws ElementNotFoundException {
 //devolve o caminho mais curto mas acho que tem de ver as exepçoes
        
        return grafo.iteratorBFS(entrada);
    }

    @Override
    public Iterator<ISala> iteratorMoveFormigaShortestPath(int i, int i1) throws ElementNotFoundException {
        
//formiga com carga, o raio do tunel tem de ser igual ou menor a carga atual
        
        return grafo.iteratorBFS(entrada);
    }

    @Override
    public Iterator<ISala> iteratorCarregaEMoveFormigaShortestPath(int i, int i1) throws ElementNotFoundException {
    //formiga é carregada com o maximo da sua capacidade e logo a seguir procura a rota mais curta
    //formiga com carga, o raio do tunel tem de ser igual ou menor a carga atual
        return grafo.iteratorBFS(entrada);
    }

    @Override
    public int custoDoCaminho(Iterator<ISala> itrtr) {
        int custo = 0;
        while (itrtr.hasNext()) {
            itrtr.next().getX();
        }
        return custo;
    }

    @Override
    public IFormiga criaFormiga(int i, int i1) {
        Formiga formiga = new Formiga(i1, i);
        this.formigas.addToRear(formiga);
        return formiga;
    }

    @Override
    public IComida criaComida(int i, int i1) {
        return new Comida(i, i1);
    }

    @Override
    public ISala criaSala(int i, String string, int i1, int i2) {
        return new Sala(i, i1, i2, string);
    }

    @Override
    public ISilo criaSilo(int i, String string, int i1, int i2) {
        return new Silo(i, i1, i2, string);
    }

    @Override
    public IProcessamento criaProcessamento(int i, String string, int i1, int i2) {
        return new Processamento(i, i1, i2, string);
    }

}
