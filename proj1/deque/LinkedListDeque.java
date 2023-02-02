package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T> {
    private int size;

    private LinkedListNode sentinel;

    public LinkedListDeque() {
        sentinel = new LinkedListNode(null,null,null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    public LinkedListDeque(T item) {
        sentinel.next = new LinkedListNode(item,sentinel,sentinel);
        sentinel.prev = sentinel.next;
        size = 1;
    }

    private class LinkedListNode {
        private final T item;
        private LinkedListNode prev;
        private LinkedListNode next;

        private LinkedListNode(T item, LinkedListNode prev, LinkedListNode next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }

        @Override
        public String toString() {
            if (item == null) {
                return "null";
            }
            return item.toString();
        }
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        if (!isEmpty()) {
            LinkedListNode node = sentinel.next;
            while (node != null) {
                System.out.println(node.item);
                node = node.next;
            }
        }
    }

    public void addFirst(T item) {
        sentinel.next = new LinkedListNode(item,sentinel,sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size += 1;
    }

    public void addLast(T item) {
        sentinel.prev = new LinkedListNode(item,sentinel.prev,sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        size += 1;
    }

    public T removeFirst() {
        if (isEmpty()) return null;
        T item = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size --;
        return item;
    }

    public T removeLast() {
        if (isEmpty()) return null;
        T item = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size --;
        return item;
    }

    public T get(int index) {
        if (index < 0 || index > size-1) return null;
        LinkedListNode node = sentinel.next;
        while (index >= 0) {
            node = node.next;
            index--;
        }
        return node.item;
    }

    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        private LinkedListNode node;
        @Override
        public boolean hasNext() {
            return node == sentinel.next;
        }

        @Override
        public T next() {
            T item = sentinel.item;
            node = node.next;
            return item;
        }
    }

    public boolean equals(Object o) {
        if (o == null) return false;
        if (o == this) return true;
        if (!(o instanceof LinkedListDeque)) return false;
        LinkedListDeque<T> ld = (LinkedListDeque<T>) o;
        if (ld.size != this.size) return false;
        for (int i = 0;i < this.size; i++) {
            if (ld.get(i) != this.get(i)) {
                return false;
            }
        }
        return true;
    }

    public T getRecursive(int index) {
        if (index < 0 || index > size-1) return null;
        return getRecursiveHandler(index,sentinel.next);
    }

    private T getRecursiveHandler(int index,LinkedListNode node) {
        if (index == 0) return node.item;
        return getRecursiveHandler(index-1,node.next);
    }
}
