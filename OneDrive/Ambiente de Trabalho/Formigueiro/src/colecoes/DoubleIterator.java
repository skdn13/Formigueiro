package colecoes;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoubleIterator<T> implements Iterator<T> {

    private DoubleNode<T> current;

    public DoubleIterator(DoubleNode<T> list) {
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
