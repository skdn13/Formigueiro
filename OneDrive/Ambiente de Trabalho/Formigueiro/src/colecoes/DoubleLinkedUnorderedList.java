package colecoes;

import java.util.Iterator;
import recursos.interfaces.collections.UnorderedListADT;

public class DoubleLinkedUnorderedList<T> extends DoubleLinkedList<T> implements UnorderedListADT<T> {

    private Iterator<T> iterator;

    public DoubleLinkedUnorderedList() {
        super();
    }

    @Override
    public void addToFront(T element) {
        DoubleNode node = new DoubleNode(element);

        if (super.isEmpty()) {
            super.setFront(node);
            super.setRear(node);
            node.setNext(null);
            node.setPrevious(null);
        } else {
            super.getFront().setPrevious(node);
            node.setNext(super.getFront());
            super.setFront(node);
            node.setPrevious(null);
        }
        super.setNumberOfElements(super.getNumberOfElements() + 1);
    }

    @Override
    public void addToRear(T element) {
        DoubleNode node = new DoubleNode(element);

        if (super.isEmpty()) {
            super.setFront(node);
            super.setRear(node);
            node.setNext(null);
            node.setPrevious(null);
        } else {
            super.getRear().setNext(node);
            node.setPrevious(super.getRear());
            super.setRear(node);
            node.setNext(null);
        }
        super.setNumberOfElements(super.getNumberOfElements() + 1);

    }

    @Override
    public void addAfter(T element, T target) {
        DoubleNode nodeTemp = super.getFront();
        DoubleNode newNode = new DoubleNode(element);
        boolean found = false;

        if (!super.isEmpty()) {
            while ((!found) && (nodeTemp != null)) {
                if (target.equals(nodeTemp.getElement())) {
                    found = true;
                } else {
                    nodeTemp = nodeTemp.getNext();
                }
            }
        }
        if (found) {
            if (nodeTemp == super.getRear()) {
                super.setRear(newNode);
            }
            newNode.setNext(nodeTemp.getNext());
            newNode.setPrevious(nodeTemp);
            nodeTemp.setNext(newNode);

            if (!(newNode == super.getRear())) {
                newNode.getNext().setPrevious(newNode);
            }

            super.setNumberOfElements(super.getNumberOfElements() + 1);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return this.iterator = new DoubleIterator<>(super.getFront());
    }

}
