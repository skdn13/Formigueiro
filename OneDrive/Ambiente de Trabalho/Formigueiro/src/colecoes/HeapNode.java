/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colecoes;

/**
 *
 * @author pmms8
 * @param <T>
 */
public class HeapNode<T> extends BinaryTreeNode<T> {

    /**
     *
     */
    protected HeapNode<T> parent;
    
    HeapNode(T obj){
        super(obj);
        parent = null;
    }
    
}
