package colecoes;

import java.util.Iterator;
import recursos.interfaces.collections.UnorderedListADT;

/**
 *
 * @author pmms8
 * @param <T>
 */
public class ArrayUnorderedList<T> extends ArrayList<T> implements UnorderedListADT<T> {

    private Iterator<T> iterator;

    /**
     *
     * @param element
     */
    @Override
    public void addToFront(T element) {
        T[] elementTemp = super.getList();

        if (super.size() == elementTemp.length) {
            super.expandCapacity();
        }
        for (int i = super.getLast(); i > 0; --i) {
            elementTemp[i] = elementTemp[i - 1];
        }
        elementTemp[0] = element;
        super.setLast(super.getLast() + 1);
        super.setList(elementTemp);
    }

    /**
     *
     * @param element
     */
    @Override
    public void addToRear(T element) {
        T[] elementTemp = super.getList();
        if (super.size() == elementTemp.length) {
            super.expandCapacity();
        }
        elementTemp[super.getLast()] = element;
        super.setList(elementTemp);
        super.setLast(super.getLast() + 1);
    }

    /**
     *
     * @param element
     * @param target
     */
    @Override
    public void addAfter(T element, T target) {

        T[] temp = super.getList();
        int tamanhoMaximo = super.getLast();
        if (super.size() == temp.length) {
            super.expandCapacity();
        }
        int i = 0;

        while (!target.equals(temp[i])) {
            i++;
        }

        if (i == super.getLast()) {
            super.expandCapacity();
        }
        int k = 0, j = 0;
        while (k <= i) {
            temp[k] = temp[j];
            k++;
            j++;
        }
        T troca = temp[i + 1];
        temp[k] = element;
        k++;
        while (k > i && k <= tamanhoMaximo) {
            if (k == tamanhoMaximo) {
                temp[k] = troca;
                k++;
            }
        }
        super.setList(temp);
        super.setLast(k);
    }

    @Override
    public Iterator<T> iterator() {
        return this.iterator = new ArrayIterator<>(super.getList(), super.getLast());
    }
}
