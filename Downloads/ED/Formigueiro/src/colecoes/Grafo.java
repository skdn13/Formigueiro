/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colecoes;

import java.util.Iterator;
import recursos.interfaces.collections.GraphADT;

/**
 *
 * @author pmms8
 * @param <T>
 */
public class Grafo<T> implements GraphADT<T> {

    protected final int DEFAULT_CAPACITY = 10;
    protected int numVertices;
    protected boolean[][] adjMatrix;
    protected T[] vertices;

    public Grafo() {
        this.numVertices = 0;
        this.adjMatrix = new boolean[DEFAULT_CAPACITY][DEFAULT_CAPACITY];
        this.vertices = (T[]) (new Object[DEFAULT_CAPACITY]);
    }

    @Override
    public void addVertex(T t) {
        if (numVertices == vertices.length) {
            expandCapacity();
        }
        vertices[numVertices] = t;
        for (int i = 0; i <= numVertices; i++) {
            adjMatrix[numVertices][i] = false;
            adjMatrix[i][numVertices] = false;
        }
        numVertices++;
    }

    @Override
    public void removeVertex(T t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void expandCapacity() {

    }

    @Override
    public void addEdge(T t, T t1) {
        addEdge(getIndex(t), getIndex(t1));
    }

    public void addEdge(int index1, int index2) {
        if (indexIsValid(index1) && indexIsValid(index2)) {
            adjMatrix[index1][index2] = true;
            adjMatrix[index2][index1] = true;
        }
    }

    public int getIndex(T vertex) {
        int index = 0;
        for (T v : vertices) {
            if (v.equals(vertex)) {
                break;
            }
            index++;
        }
        return index;
    }

    public boolean indexIsValid(int index) {
        return index < numVertices;
    }

    @Override
    public void removeEdge(T t, T t1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iterator iteratorBFS(T t) {
        Integer x;
        LinkedQueue<Integer> transversalQueue = new LinkedQueue<>();
        ArrayUnorderedList<T> resultList = new ArrayUnorderedList<>();
        if (!indexIsValid(getIndex(t))) {
            return resultList.iterator();
        }

        boolean[] visited = new boolean[numVertices];
        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }
        transversalQueue.enqueue(new Integer(getIndex(t)));
        visited[getIndex(t)] = true;
        while (!transversalQueue.isEmpty()) {
            x = transversalQueue.dequeue();
            resultList.addToRear(vertices[x.intValue()]);
            for (int i = 0; i < numVertices; i++) {
                if (adjMatrix[x.intValue()][i] && !visited[i]) {
                    transversalQueue.enqueue(new Integer(i));
                    visited[i] = true;
                }
            }
        }
        return resultList.iterator();
    }

    @Override
    public Iterator iteratorDFS(T t) {
        Integer x;
        boolean found;
        LinkedStack<Integer> transversalStack = new LinkedStack<>();
        ArrayUnorderedList<T> resultList = new ArrayUnorderedList<T>();
        boolean[] visited = new boolean[numVertices];
        if (!indexIsValid(getIndex(t))) {
            return resultList.iterator();
        }
        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }
        transversalStack.push(new Integer(getIndex(t)));
        resultList.addToRear(vertices[getIndex(t)]);
        visited[getIndex(t)] = true;
        while (!transversalStack.isEmpty()) {
            x = transversalStack.peek();
            found = false;
            for (int i = 0; (i < numVertices) && !found; i++) {
                if (adjMatrix[x.intValue()][i] && !visited[i]) {
                    transversalStack.push(new Integer(i));
                    resultList.addToRear(vertices[i]);
                    visited[i] = true;
                    found = true;
                }
            }
            if (!found && !transversalStack.isEmpty()) {
                transversalStack.pop();
            }

        }
        return resultList.iterator();
    }

    @Override
    public Iterator iteratorShortestPath(T t, T t1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isEmpty() {
        return this.numVertices == 0;
    }

    @Override
    public boolean isConnected() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int size() {
        return this.numVertices;
    }

    @Override
    public T[] getVertices() {
        return this.vertices;
    }

}
