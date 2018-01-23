package colecoes;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedIterator<T> implements Iterator<T> {

    private LinearNode<T> current;

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
