package colecoes;

import java.util.Iterator;
import recursos.exceptions.ElementNotFoundException;
import recursos.exceptions.EmptyCollectionException;
import recursos.interfaces.collections.BinaryTreeADT;

/**
 *
 * @author pmms8
 * @param <T>
 */
public class LinkedBinaryTree<T> implements BinaryTreeADT<T> {

    /**
     *
     */
    public int count;

    /**
     *
     */
    public BinaryTreeNode<T> root;

    /**
     *
     */
    public LinkedBinaryTree() {
        this.count = 0;
        this.root = null;
    }

    /**
     *
     * @param element
     */
    public LinkedBinaryTree(T element) {
        this.count = 1;
        this.root = new BinaryTreeNode<>(element);
    }

    /**
     *
     * @return
     */
    @Override
    public T getRoot() {
        return (T) this.root;
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return (this.count == 0);
    }

    /**
     *
     * @return
     */
    @Override
    public int size() {
        return this.count;
    }

    /**
     *
     * @param targetElement
     * @return
     */
    @Override
    public boolean contains(T targetElement) {
        boolean found = false;

        try {
            T temp = find(targetElement);
            found = true;
        } catch (Exception ex) {
            found = false;
        }

        return found;
    }

    /**
     *
     * @param targetElement
     * @return
     * @throws ElementNotFoundException
     */
    public T find(T targetElement) throws ElementNotFoundException {
        BinaryTreeNode<T> current = findAgain(targetElement, root);

        if (current == null) {
            throw new ElementNotFoundException("binary tree");
        }

        return (current.element);
    }

    /**
     *
     * @return
     */
    @Override
    public Iterator<T> iteratorInOrder() {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<>();
        inorder(root, tempList);
        return tempList.iterator();
    }

    /**
     *
     * @param node
     * @param tempList
     */
    protected void inorder(BinaryTreeNode<T> node, ArrayUnorderedList<T> tempList) {
        if (node != null) {
            inorder(node.left, tempList);
            tempList.addToRear(node.element);
            inorder(node.right, tempList);
        }
    }

    /**
     *
     * @return
     */
    @Override
    public Iterator<T> iteratorPreOrder() {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<>();
        preOrder(this.root, tempList);
        return tempList.iterator();
    }

    /**
     *
     * @param node
     * @param tempList
     */
    protected void preOrder(BinaryTreeNode<T> node, ArrayUnorderedList<T> tempList) {
        if (node != null) {
            tempList.addToRear(node.element);
            preOrder(node.left, tempList);
            preOrder(node.right, tempList);
        }
    }

    /**
     *
     * @return
     */
    @Override
    public Iterator<T> iteratorPostOrder() {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<>();
        postOrder(root, tempList);
        return tempList.iterator();
    }

    /**
     *
     * @param node
     * @param tempList
     */
    protected void postOrder(BinaryTreeNode<T> node, ArrayUnorderedList<T> tempList) {
        if (node != null) {
            postOrder(node.left, tempList);
            postOrder(node.right, tempList);
            tempList.addToRear(node.element);
        }
    }

    /**
     *
     * @return
     * @throws EmptyCollectionException
     */
    @Override
    public Iterator<T> iteratorLevelOrder() throws recursos.exceptions.EmptyCollectionException {
        ArrayUnorderedList<T> nodes = new ArrayUnorderedList<>();
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<>();

        BinaryTreeNode<T> current = null;

        while (!nodes.isEmpty()) {
            try {
                current = (BinaryTreeNode<T>) nodes.removeFirst();
            } catch (recursos.exceptions.EmptyCollectionException ex) {

            }

            if (current != null) {
                tempList.addToRear(current.element);
                nodes.addToRear(current.left.element);
                nodes.addToRear(current.right.element);
            } else {
                tempList.addToRear(null);
            }
        }
        return tempList.iterator();
    }

    private BinaryTreeNode<T> findAgain(T targetElement, BinaryTreeNode<T> next) {
        if (next == null) {
            return null;
        }
        if (next.element.equals(targetElement)) {
            return next;
        }
        BinaryTreeNode<T> temp = findAgain(targetElement, next.left);
        if (temp == null) {
            temp = findAgain(targetElement, next.right);
        }
        return temp;
    }

    /**
     *
     */
    public void removeAllElements() {
        count = 0;
        root = null;
    }
}
