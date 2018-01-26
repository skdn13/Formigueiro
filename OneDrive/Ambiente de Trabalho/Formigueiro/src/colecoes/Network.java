package colecoes;

import formigueiro.Tunel;
import java.util.Iterator;
import recursos.exceptions.ElementNotFoundException;
import recursos.interfaces.ITunel;
import recursos.interfaces.collections.NetworkADT;

public class Network<T> extends Graph<T> implements NetworkADT<T> {

    private ITunel[][] adjMatrix;    // adjacency matrix

    /**
     * ****************************************************************
     * Creates an empty network.
     * ****************************************************************
     */
    public Network() {
        numVertices = 0;
        this.adjMatrix = new Tunel[DEFAULT_CAPACITY][DEFAULT_CAPACITY];
        this.vertices = (T[]) (new Object[DEFAULT_CAPACITY]);
    }

    /**
     * ****************************************************************
     * Returns a string representation of the adjacency matrix.
     * ****************************************************************
     */
    @Override
    public String toString() {
        if (numVertices == 0) {
            return "Graph is empty";
        }

        String result = "";

        /**
         * Print the adjacency Matrix
         */
        result += "Adjacency Matrix\n";
        result += "----------------\n";
        result += "index\t";

        for (int i = 0; i < numVertices; i++) {
            result += "" + i;
            if (i < 10) {
                result += " ";
            }
        }
        result += "\n\n";

        for (int i = 0; i < numVertices; i++) {
            result += "" + i + "\t";

            for (int j = 0; j < numVertices; j++) {
                if (adjMatrix[i][j] != null) {
                    result += "1 ";
                } else {
                    result += "0 ";
                }

            }
            result += "\n";
        }

        /**
         * Print the vertex values
         */
        result += "\n\nVertex Values";
        result += "\n-------------\n";
        result += "index\tvalue\n\n";

        for (int i = 0; i < numVertices; i++) {
            result += "" + i + "\t";
            result += vertices[i].toString() + "\n";
        }

        /**
         * Print the weights of the edges
         */
        result += "\n\nWeights of Edges";
        result += "\n----------------\n";
        result += "index\tweight\n\n";

        for (int i = 0; i < numVertices; i++) {
            for (int j = numVertices - 1; j > i; j--) {
                if (adjMatrix[i][j] != null) {
                    result += i + " to " + j + "\t";
                    result += adjMatrix[i][j] + "\n";
                }
            }
        }

        result += "\n";
        return result;
    }

    @Override
    public void addEdge(T t, T t1, ITunel itunel) throws ElementNotFoundException {
        addEdge(super.getIndex(t), super.getIndex(t1), itunel);
    }

    /**
     * ****************************************************************
     * Inserts an edge between two vertices of the graph.
     *
     * **************************************************************** @param
     * index1
     * @param index1
     * @param index2
     * @param weight
     */
    public void addEdge(int index1, int index2, ITunel weight) {
        if (indexIsValid(index1) && indexIsValid(index2)) {
            adjMatrix[index1][index2] = weight;
            adjMatrix[index2][index1] = weight;
        }
    }

    public ITunel getElement(int index1, int index2) {
        return this.adjMatrix[index1][index2];
    }

    /**
     * ****************************************************************
     * Removes an edge between two vertices of the graph.
     * ****************************************************************
     */
    @Override
    public void removeEdge(int index1, int index2) {
        if (indexIsValid(index1) && indexIsValid(index2)) {
            adjMatrix[index1][index2] = null;
            adjMatrix[index2][index1] = null;
        }
    }

    /**
     * ****************************************************************
     * Removes an edge between two vertices of the graph.
     * ****************************************************************
     */
    @Override
    public void removeEdge(T vertex1, T vertex2) {
        removeEdge(getIndex(vertex1), getIndex(vertex2));
    }

    /**
     * ****************************************************************
     * Adds a vertex to the graph, expanding the capacity of the graph if
     * necessary.
     * ****************************************************************
     */
    @Override
    public void addVertex() {
        if (numVertices == vertices.length) {
            expandCapacity();
        }

        vertices[numVertices] = null;
        for (int i = 0; i <= numVertices; i++) {
            adjMatrix[numVertices][i] = null;
            adjMatrix[i][numVertices] = null;
        }
        numVertices++;
    }

    /**
     * ****************************************************************
     * Adds a vertex to the graph, expanding the capacity of the graph if
     * necessary. It also associates an object with the vertex.
     * **************************************************************** //
     */
    @Override
    public void addVertex(T vertex) {
        if (numVertices == vertices.length) {
            expandCapacity();
        }
        vertices[numVertices] = vertex;
        for (int i = 0; i <= numVertices; i++) {
            adjMatrix[numVertices][i] = null;
            adjMatrix[i][numVertices] = null;
        }
        numVertices++;
    }

    /**
     * ****************************************************************
     * Removes a vertex at the given index from the graph. Note that this may
     * affect the index values of other vertices.
     * ****************************************************************
     */
    @Override
    public void removeVertex(int index) {
        if (indexIsValid(index)) {
            numVertices--;

            for (int i = index; i < numVertices; i++) {
                vertices[i] = vertices[i + 1];
            }

            for (int i = index; i < numVertices; i++) {
                for (int j = 0; j <= numVertices; j++) {
                    adjMatrix[i][j] = adjMatrix[i + 1][j];
                }
            }

            for (int i = index; i < numVertices; i++) {
                for (int j = 0; j < numVertices; j++) {
                    adjMatrix[j][i] = adjMatrix[j][i + 1];
                }
            }
        }
    }

    /**
     * ****************************************************************
     * Removes a single vertex with the given value from the graph.
     * ****************************************************************
     */
    @Override
    public void removeVertex(T vertex) {
        for (int i = 0; i < numVertices; i++) {
            if (vertex.equals(vertices[i])) {
                removeVertex(i);
                return;
            }
        }
    }

    /**
     * ****************************************************************
     * Returns an iterator that performs a depth first search traversal starting
     * at the given index.
     *
     * **************************************************************** @return
     * @return
     */
    @Override
    public Iterator<T> iteratorDFS(int startIndex) {
        Integer x;
        boolean found;
        LinkedStack<Integer> traversalStack = new LinkedStack<>();
        ArrayUnorderedList<T> resultList = new ArrayUnorderedList<>();
        boolean[] visited = new boolean[numVertices];

        if (!indexIsValid(startIndex)) {
            return resultList.iterator();
        }

        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }

        traversalStack.push(startIndex);
        resultList.addToRear(vertices[startIndex]);
        visited[startIndex] = true;

        while (!traversalStack.isEmpty()) {
            x = traversalStack.peek();
            found = false;

            /**
             * Find a vertex adjacent to x that has not been visited and push it
             * on the stack
             */
            for (int i = 0; (i < numVertices) && !found; i++) {
                if ((adjMatrix[x][i].getDistance() < Double.POSITIVE_INFINITY)
                        && !visited[i]) {
                    traversalStack.push(i);
                    resultList.addToRear(vertices[i]);
                    visited[i] = true;
                    found = true;
                }
            }
            if (!found && !traversalStack.isEmpty()) {
                traversalStack.pop();
            }
        }
        return resultList.iterator();
    }

    /**
     * ****************************************************************
     * Returns an iterator that performs a depth first search traversal starting
     * at the given vertex.
     *
     * **************************************************************** @return
     * @return
     */
    @Override
    public Iterator<T> iteratorDFS(T startVertex) {
        return iteratorDFS(getIndex(startVertex));
    }

    /**
     * ****************************************************************
     * Returns an iterator that performs a breadth first search traversal
     * starting at the given index.
     *
     * **************************************************************** @return
     * @return
     */
    @Override
    public Iterator<T> iteratorBFS(int startIndex) {
        Integer x;
        LinkedQueue<Integer> traversalQueue = new LinkedQueue<>();
        ArrayUnorderedList<T> resultList = new ArrayUnorderedList<>();

        if (!indexIsValid(startIndex)) {
            return resultList.iterator();
        }

        boolean[] visited = new boolean[numVertices];
        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }

        traversalQueue.enqueue(startIndex);
        visited[startIndex] = true;

        while (!traversalQueue.isEmpty()) {
            x = traversalQueue.dequeue();
            resultList.addToRear(vertices[x]);

            /**
             * Find all vertices adjacent to x that have not been visited and
             * queue them up
             */
            for (int i = 0; i < numVertices; i++) {
                if (adjMatrix[x][i] != null && !visited[i]) {
                    traversalQueue.enqueue(i);
                    visited[i] = true;
                }
            }
        }
        return resultList.iterator();
    }

    /**
     * ****************************************************************
     * Returns an iterator that performs a breadth first search traversal
     * starting at the given vertex.
     *
     * **************************************************************** @return
     * @return
     */
    @Override
    public Iterator<T> iteratorBFS(T startVertex) {
        return iteratorBFS(getIndex(startVertex));
    }

    /**
     * ****************************************************************
     * Returns an iterator that contains the indices of the vertices that are in
     * the shortest path between the two given vertices.
     *
     * **************************************************************** @return
     * @return
     */
    @Override
    protected Iterator<Integer> iteratorShortestPathIndices(int startIndex, int targetIndex) {
        int index;
        double weight;
        int[] predecessor = new int[numVertices];
        LinkedHeap<Double> traversalMinHeap = new LinkedHeap<>();
        ArrayUnorderedList<Integer> resultList
                = new ArrayUnorderedList<>();
        LinkedStack<Integer> stack = new LinkedStack<>();

        int[] pathIndex = new int[numVertices];
        double[] pathWeight = new double[numVertices];
        for (int i = 0; i < numVertices; i++) {
            pathWeight[i] = Double.POSITIVE_INFINITY;
        }

        boolean[] visited = new boolean[numVertices];
        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }

        if (!indexIsValid(startIndex) || !indexIsValid(targetIndex)
                || (startIndex == targetIndex) || isEmpty()) {
            return resultList.iterator();
        }

        pathWeight[startIndex] = 0;
        predecessor[startIndex] = -1;
        visited[startIndex] = true;
        weight = 0;

        /**
         * Update the pathWeight for each vertex except the startVertex. Notice
         * that all vertices not adjacent to the startVertex will have a
         * pathWeight of infinity for now.
         */
        for (int i = 0; i < numVertices; i++) {
            if (!visited[i] && adjMatrix[startIndex][i] != null) {
                pathWeight[i] = pathWeight[startIndex] + adjMatrix[startIndex][i].getDistance();
                predecessor[i] = startIndex;
                traversalMinHeap.addElement(pathWeight[i]);
            }

        }

        do {
            weight = (traversalMinHeap.removeMin());
            traversalMinHeap.removeAllElements();
            if (weight == Double.POSITIVE_INFINITY) // no possible path
            {
                return resultList.iterator();
            } else {
                index = getIndexOfAdjVertexWithWeightOf(visited, pathWeight,
                        weight);
                visited[index] = true;
            }

            /**
             * Update the pathWeight for each vertex that has has not been
             * visited and is adjacent to the last vertex that was visited.
             * Also, add each unvisited vertex to the heap.
             */
            for (int i = 0; i < numVertices; i++) {
                if (!visited[i]) {
                    if ((adjMatrix[index][i] != null)
                            && (pathWeight[index] + adjMatrix[index][i].getDistance()) < pathWeight[i]) {
                        pathWeight[i] = pathWeight[index] + adjMatrix[index][i].getDistance();
                        predecessor[i] = index;
                    }
                    traversalMinHeap.addElement(pathWeight[i]);
                }
            }
        } while (!traversalMinHeap.isEmpty() && !visited[targetIndex]);

        index = targetIndex;
        stack.push(index);
        do {
            index = predecessor[index];
            stack.push(index);
        } while (index != startIndex);

        while (!stack.isEmpty()) {
            resultList.addToRear((stack.pop()));
        }

        return resultList.iterator();

    }

    protected Iterator<Integer> iteratorShortestPathIndicesTunel(int startIndex, int targetIndex, int cargaFormiga) {
        int index;
        double weight;
        int[] predecessor = new int[numVertices];
        LinkedHeap<Double> traversalMinHeap = new LinkedHeap<>();
        ArrayUnorderedList<Integer> resultList
                = new ArrayUnorderedList<>();
        LinkedStack<Integer> stack = new LinkedStack<>();

        int[] pathIndex = new int[numVertices];
        double[] pathWeight = new double[numVertices];
        for (int i = 0; i < numVertices; i++) {
            pathWeight[i] = Double.POSITIVE_INFINITY;
        }

        boolean[] visited = new boolean[numVertices];
        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }

        if (!indexIsValid(startIndex) || !indexIsValid(targetIndex)
                || (startIndex == targetIndex) || isEmpty()) {
            return resultList.iterator();
        }

        pathWeight[startIndex] = 0;
        predecessor[startIndex] = -1;
        visited[startIndex] = true;
        weight = 0;

        /**
         * Update the pathWeight for each vertex except the startVertex. Notice
         * that all vertices not adjacent to the startVertex will have a
         * pathWeight of infinity for now.
         */
        for (int i = 0; i < numVertices; i++) {
            if (!visited[i] && adjMatrix[startIndex][i] != null && cargaFormiga <= adjMatrix[startIndex][i].getRadious()) {
                pathWeight[i] = pathWeight[startIndex] + adjMatrix[startIndex][i].getDistance();
                predecessor[i] = startIndex;
                traversalMinHeap.addElement(pathWeight[i]);
            }

        }

        do {
            weight = (traversalMinHeap.removeMin());
            traversalMinHeap.removeAllElements();
            if (weight == Double.POSITIVE_INFINITY) // no possible path
            {
                return resultList.iterator();
            } else {
                index = getIndexOfAdjVertexWithWeightOf(visited, pathWeight,
                        weight);
                visited[index] = true;
            }
            for (int i = 0; i < numVertices; i++) {
                if (!visited[i]) {
                    if ((adjMatrix[index][i] != null)
                            && (pathWeight[index] + adjMatrix[index][i].getDistance()) < pathWeight[i] && cargaFormiga <= adjMatrix[index][i].getRadious()) {
                        pathWeight[i] = pathWeight[index] + adjMatrix[index][i].getDistance();
                        predecessor[i] = index;
                    }
                    traversalMinHeap.addElement(pathWeight[i]);
                }
            }
        } while (!traversalMinHeap.isEmpty() && !visited[targetIndex]);

        index = targetIndex;
        stack.push(index);
        do {
            index = predecessor[index];
            stack.push(index);
        } while (index != startIndex);

        while (!stack.isEmpty()) {
            resultList.addToRear((stack.pop()));
        }

        return resultList.iterator();
    }

    /**
     * ****************************************************************
     * Returns the index of the the vertex that that is adjacent to the vertex
     * with the given index and also has a pathWeight equal to weight.
     *
     * **************************************************************** @param
     * visited
     * @param visited
     * @param pathWeight
     * @param weight
     * @return
     */
    protected int getIndexOfAdjVertexWithWeightOf(boolean[] visited,
            double[] pathWeight, double weight) {
        for (int i = 0; i < numVertices; i++) {
            if ((pathWeight[i] == weight) && !visited[i]) {
                for (int j = 0; j < numVertices; j++) {
                    if (adjMatrix[i][j] != null && visited[j]) {
                        return i;
                    }
                }
            }
        }

        return -1;
    }

    /**
     * ****************************************************************
     * Returns an iterator that contains the shortest path between the two
     * vertices.
     *
     * **************************************************************** @return
     * @return
     */
    @Override
    public Iterator<T> iteratorShortestPath(int startIndex, int targetIndex) {
        ArrayUnorderedList templist = new ArrayUnorderedList();
        if (!indexIsValid(startIndex) || !indexIsValid(targetIndex)) {
            return templist.iterator();
        }

        Iterator<Integer> it = iteratorShortestPathIndices(startIndex,
                targetIndex);
        while (it.hasNext()) {
            templist.addToRear(vertices[(it.next())]);
        }
        return templist.iterator();
    }

    public Iterator<T> iteratorShortestPathTunel(int startIndex, int targetIndex, int cargaFormiga) {
        ArrayUnorderedList templist = new ArrayUnorderedList();
        if (!indexIsValid(startIndex) || !indexIsValid(targetIndex)) {
            return templist.iterator();
        }

        Iterator<Integer> it = iteratorShortestPathIndicesTunel(startIndex,
                targetIndex, cargaFormiga);
        while (it.hasNext()) {
            templist.addToRear(vertices[(it.next())]);
        }
        return templist.iterator();
    }

    /**
     * ****************************************************************
     * Returns an iterator that contains the shortest path between the two
     * vertices.
     *
     * **************************************************************** @return
     * @return
     */
    @Override
    public Iterator<T> iteratorShortestPath(T startVertex, T targetVertex) {
        return iteratorShortestPath(getIndex(startVertex),
                getIndex(targetVertex));
    }

    /**
     * ****************************************************************
     * Returns the weight of the least weight path in the network. Returns
     * positive infinity if no path is found.
     *
     * **************************************************************** @param
     * startIndex
     * @param startIndex
     * @param targetIndex
     * @return
     */
    public double shortestPathWeight(int startIndex, int targetIndex) {
        double result = 0;
        if (!indexIsValid(startIndex) || !indexIsValid(targetIndex)) {
            return Double.POSITIVE_INFINITY;
        }

        int index1, index2;
        Iterator<Integer> it = iteratorShortestPathIndices(startIndex,
                targetIndex);

        if (it.hasNext()) {
            index1 = (it.next());
        } else {
            return Double.POSITIVE_INFINITY;
        }

        while (it.hasNext()) {
            index2 = (it.next());
            result += adjMatrix[index1][index2].getDistance();
            index1 = index2;
        }

        return result;
    }

    /**
     * ****************************************************************
     * Returns the weight of the least weight path in the network. Returns
     * positive infinity if no path is found.
     *
     * **************************************************************** @param
     * startVertex
     * @param startVertex
     * @param targetVertex
     * @return
     */
    @Override
    public double shortestPathWeight(T startVertex, T targetVertex) {
        return shortestPathWeight(getIndex(startVertex),
                getIndex(targetVertex));
    }

    /**
     * ****************************************************************
     * Returns a minimum spanning tree of the network.
     *
     * **************************************************************** @return
     * @return
     * @throws java.lang.Exception
     */
    public Network mstNetwork() throws Exception {
        int x, y;
        int index;
        double weight;
        int[] edge = new int[2];
        LinkedHeap<ITunel> minHeap = new LinkedHeap<>();
        Network<T> resultGraph = new Network<>();

        if (isEmpty() || !isConnected()) {
            return resultGraph;
        }

        resultGraph.adjMatrix = new Tunel[numVertices][numVertices];
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                resultGraph.adjMatrix[i][j].setDistance((int) Double.POSITIVE_INFINITY);
               // resultGraph.adjMatrix[i][j] = null;
            }
        }
        resultGraph.vertices = (T[]) (new Object[numVertices]);

        boolean[] visited = new boolean[numVertices];
        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }

        edge[0] = 0;
        resultGraph.vertices[0] = this.vertices[0];
        resultGraph.numVertices++;
        visited[0] = true;

        /**
         * Add all edges, which are adjacent to the starting vertex, to the heap
         */
        for (int i = 0; i < numVertices; i++) {
            minHeap.addElement(adjMatrix[0][i]);
        }

        while ((resultGraph.size() < this.size()) && !minHeap.isEmpty()) {
            /**
             * Get the edge with the smallest weight that has exactly one vertex
             * already in the resultGraph
             */
            do {
                weight = (minHeap.removeMin()).getDistance();
                edge = getEdgeWithWeightOf(weight, visited);
            } while (!indexIsValid(edge[0]) || !indexIsValid(edge[1]));

            x = edge[0];
            y = edge[1];
            if (!visited[x]) {
                index = x;
            } else {
                index = y;
            }

            /**
             * Add the new edge and vertex to the resultGraph
             */
            resultGraph.vertices[index] = this.vertices[index];
            visited[index] = true;
            resultGraph.numVertices++;

            resultGraph.adjMatrix[x][y] = this.adjMatrix[x][y];
            resultGraph.adjMatrix[y][x] = this.adjMatrix[y][x];

            /**
             * Add all edges, that are adjacent to the newly added vertex, to
             * the heap
             */
            for (int i = 0; i < numVertices; i++) {
                if (!visited[i] && (this.adjMatrix[i][index].getDistance()
                        < Double.POSITIVE_INFINITY)) {
                    edge[0] = index;
                    edge[1] = i;
                    minHeap.addElement(adjMatrix[index][i]);
                }
            }
        }
        return resultGraph;
    }

    /**
     * ****************************************************************
     * Returns the edge with the given weight. Exactly one vertex of the edge
     * must have been visited.
     *
     * **************************************************************** @param
     * weight
     * @param weight
     * @param visited
     * @return
     */
    protected int[] getEdgeWithWeightOf(double weight, boolean[] visited) {
        int[] edge = new int[2];
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                if ((adjMatrix[i][j].getDistance() == weight) && (visited[i] ^ visited[j])) {
                    edge[0] = i;
                    edge[1] = j;
                    return edge;
                }
            }
        }
        edge[0] = -1;
        edge[1] = -1;
        return edge;
    }

    /**
     * ****************************************************************
     * Creates new arrays to store the contents of the graph with twice the
     * capacity.
     * ****************************************************************
     */
    @Override
    protected void expandCapacity() {
        T[] largerVertices = (T[]) (new Object[vertices.length * 2]);
        ITunel[][] largerAdjMatrix
                = new Tunel[vertices.length * 2][vertices.length * 2];

        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                try {
                    largerAdjMatrix[i][j].setDistance(adjMatrix[i][j].getDistance());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            largerVertices[i] = vertices[i];
        }

        vertices = largerVertices;
        adjMatrix = largerAdjMatrix;
    }

    public Iterator<T> iteratorVertex() {
        ArrayUnorderedList<T> vertices = new ArrayUnorderedList<>();
        for (int i = 0; i < numVertices; i++) {
            vertices.addToRear(super.vertices[i]);
        }
        return (Iterator<T>) vertices.iterator();
    }

    public Iterator<T> iteratorNeighbour(int position) {
        ArrayUnorderedList<T> neighbours = new ArrayUnorderedList<>();
        for (int i = 0; i < numVertices; i++) {
            if (this.adjMatrix[position][i] != null) {
                neighbours.addToRear(super.vertices[i]);
            }
        }
        return (Iterator<T>) neighbours.iterator();
    }

    public int getElementIndex(T vertex) {
        for (int i = 0; i < this.numVertices; i++) {
            if (vertices[i].equals(vertex)) {
                return i;
            }
        }
        return 0;
    }

    public int numberOfVertices() {
        return this.numVertices;
    }

}
