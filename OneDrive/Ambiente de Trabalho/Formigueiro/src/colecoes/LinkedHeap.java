/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colecoes;

import recursos.exceptions.EmptyCollectionException;
import recursos.interfaces.collections.HeapADT;

/**
 *
 * @author pmms8
 * @param <T>
 */
public class LinkedHeap<T> extends LinkedBinaryTree<T> implements HeapADT<T> {

    /**
     *
     */
    public HeapNode<T> lastNode;

    /**
     *
     */
    public LinkedHeap() {
        super();
    }

    /**
     *
     * @param obj
     */
    @Override
    public void addElement(T obj) {
        HeapNode<T> node = new HeapNode<>(obj);
        if (root == null) {
            root = node;
        } else {
            HeapNode<T> next_parent = getNextParentAdd();
            if (next_parent.left == null) {
                next_parent.left = node;
            } else {
                next_parent.right = node;
            }
            node.parent = next_parent;
        }
        lastNode = node;
        count++;
        if (count > 1) {
            heapifyAdd();
        }
    }

    private HeapNode<T> getNextParentAdd() {
        HeapNode<T> result = lastNode;
        while (result != root && result.parent.left != result) {
            result = result.parent;
        }
        if (result != root) {
            if (result.parent.right == null) {
                result = result.parent;
            } else {
                result = (HeapNode<T>) result.parent.right;
                while (result.left != null) {
                    result = (HeapNode<T>) result.left;
                }

            }
        } else {
            while (result.left != null) {
                result = (HeapNode<T>) result.left;
            }
        }
        return result;
    }

    private void heapifyAdd() {
        T temp;
        HeapNode<T> next = lastNode;
        temp = next.element;
        while (next != root && ((Comparable) temp).compareTo(next.parent.element) < 0) {
            next.element = next.parent.element;
            next = next.parent;
        }
        next.element = temp;
    }

    /**
     *
     * @return @throws EmptyCollectionException
     */
    @Override
    public T removeMin() throws recursos.exceptions.EmptyCollectionException {
        if (isEmpty()) {
            throw new recursos.exceptions.EmptyCollectionException("Empty Heap");
        }
        T minElement = root.element;
        if (count == 1) {
            root = null;
            lastNode = null;
        } else {
            HeapNode<T> next_last = getNewLastNode();
            if (lastNode.parent.left == lastNode) {
                lastNode.parent.left = null;
            } else {
                lastNode.parent.right = null;
            }
            root.element = lastNode.element;
            lastNode = next_last;
            heapifyRemove();
        }
        count--;
        return minElement;
    }

    private HeapNode<T> getNewLastNode() {
        HeapNode<T> result = lastNode;
        while (result != root && result.parent.left == result) {
            result = result.parent;
        }
        if (result != root) {
            result = (HeapNode<T>) result.parent.left;
        }
        while (result.right != null) {
            result = (HeapNode<T>) result.right;
        }
        return result;
    }

    private void heapifyRemove() {
        T temp;
        HeapNode<T> node = (HeapNode<T>) root;
        HeapNode<T> left = (HeapNode<T>) node.left;
        HeapNode<T> right = (HeapNode<T>) node.right;
        HeapNode<T> next;
        if (left == null && right == null) {
            next = null;
        } else if (left == null) {
            next = right;
        } else if (right == null) {
            next = left;
        } else if (((Comparable) left.element).compareTo(right.element) < 0) {
            next = left;
        } else {
            next = right;
        }
        temp = node.element;
        while ((next != null) && (((Comparable) next.element).compareTo(temp) < 0)) {
            node.element = next.element;
            node = next;
            left = (HeapNode<T>) node.left;
            right = (HeapNode<T>) node.right;
            if (left == null && right == null) {
                next = null;
            } else if (left == null) {
                next = right;
            } else if (right == null) {
                next = left;
            } else if (((Comparable) left.element).compareTo(right.element) < 0) {
                next = left;
            } else {
                next = right;
            }
            node.element = temp;
        }
    }

    /**
     *
     * @return @throws EmptyCollectionException
     */
    @Override
    public T findMin() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("Empty Heap");
        }
        return root.element;
    }
}
