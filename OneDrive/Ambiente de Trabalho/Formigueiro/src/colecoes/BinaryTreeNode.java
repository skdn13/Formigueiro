package colecoes;

/**
 *
 * @author pmms8
 * @param <T>
 */
public class BinaryTreeNode<T> {

    /**
     *
     */
    public T element;

    /**
     *
     */
    public BinaryTreeNode<T> left;

    /**
     *
     */
    public BinaryTreeNode<T> right;

    /**
     *
     * @param element
     */
    public BinaryTreeNode(T element) {
        this.element = element;
        this.left = null;
        this.right = null;
    }

    /**
     *
     * @return
     */
    public int numChildren() {
        int children = 0;
        if (left != null) {
            children = 1 + left.numChildren();
        }
        if (right != null) {
            children = children + 1 + right.numChildren();
        }
        return children;
    }

}
