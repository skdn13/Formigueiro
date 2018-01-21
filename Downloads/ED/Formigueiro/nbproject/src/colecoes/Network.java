/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colecoes;

import formigueiro.Tunel;
import recursos.exceptions.ElementNotFoundException;
import recursos.interfaces.ITunel;
import recursos.interfaces.collections.NetworkADT;


public class Network<T> extends Grafo<T> implements NetworkADT<T> {

    public Network() {
        super();
    }
    
   public Network mstNetwork () {
    int x,y;
    int index;
    double weight;
    int[] edge = new int[2];
    LinkedHeap<Integer> minHeap = new LinkedHeap<Integer>();
    Network<T> resultGraph = new Network<T>();
    
    
    
    if(isEmpty() || !isConnected())
        return resultGraph;
    

       resultGraph.adjMatrix = new double[numVertices][numVertices];

       for (int i = 0; i < numVertices; i++) 
           for (int j = 0; j < numVertices; i++) 
               resultGraph.adjMatrix[i][j] = Double.POSITIVE_INFINITY;
               resultGraph.vertices = (T[]) (new Object[numVertices]);
           
       
    
       boolean[] visited = new boolean[numVertices];
       for(int i=0;i<numVertices; i++)
           visited[i]=false;
       
       edge[0] = 0;
       resultGraph.vertices[0]=this.vertices[0];
       resultGraph.numVertices++;
       visited[0]=true;
       
       for (int i=0; i<numVertices; i++)
           minHeap.addElement(new Double(adjMatrix[0][i]));
       
       while((resultGraph.size()< this.size()) && !minHeap.isEmppty()){
           
           do{
               weight=(minHeap.removeMin()).doubleValue();
               edge=getEdgeWithWeightOf(weight,visited);
               
           }while(!indexIsValid(edge[0]) || !indexIsValid(edge[1]));
        
           x=edge[0];
           y=edge[1];
           if(!visited[x])
               index = x;
           else
               index =y;
           
           resultGraph.vertices[index]=this.vertices[index];
           visited[index]=true;
           resultGraph.numVertices++;
           
           resultGraph.adjMatrix[x][y] = this.adjMatrix[x][y];
           resultGraph.adjMatrix[y][x] = this.adjMatrix[y][x];
           
           for(int i=0; i<numVertices; i++){
               if(!visited[i] && (this.adjMatrix[i][index] < Double.POSITIVE_INFINITY)){
                   edge[0]= index;
                   edge[1]=I;
                   minHeap.addElement(new Double(adjMatrix[index][i]));
               }
           }
       }
       return resultGraph;
    }

    public Network() {
    }

    public Network() {
    }
    
    
    @Override
    public void addEdge(T t, T t1, ITunel itunel) throws ElementNotFoundException {

        super.addEdge(t, t1);
    }

    @Override
    public double shortestPathWeight(T t, T t1) throws ElementNotFoundException {

        return super.iteratorShortestPath(t, t1);
    
    }
    
 
    
}
