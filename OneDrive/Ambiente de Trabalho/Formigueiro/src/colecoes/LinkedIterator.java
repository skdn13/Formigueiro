package colecoes;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author pmms8
 * @param <T>
 */
public class LinkedIterator<T> implements Iterator<T> {

    private LinearNode<T> current;

    /**
     *
     * @param list
     */
    public LinkedIterator(LinearNode<T> list) {
        this.current = list;
    }

    @Override
    public boolean hasNext() {
        return current != null;
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        T result = this.current.getElement();
        this.current = this.current.getNext();
        return result;
    }

}
