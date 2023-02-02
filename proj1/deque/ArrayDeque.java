package deque;

public class ArrayDeque<T> implements Deque<T>{
    private int initSize = 8;
    private T[] items = (T[]) new Object[initSize];
    private int size;
    private int left;
    private int right;

    public ArrayDeque() {
        size = 0;
        left = 0;
        right = 0;
    }

    public boolean isFull() {
        return size == initSize-1;
    }

    public int size() {
        return (right - left + initSize) % initSize;
    }

    public void addFirst(T item) {
        if (isFull()) {
            resize((int)(initSize * 1.5));
        }
        left = (left + initSize - 1) % initSize;
        items[left] = item;
    }

    public void addLast(T item) {
        if (isFull()) {
            resize((int)(initSize * 1.5));
        }
        items[right] = item;
        right = (right + initSize + 1) % initSize;
    }

    public T removeFirst() {
        if (isEmpty()) return null;
        T item = items[left];
        left = (left + 1) % initSize;
        return item;
    }

    public T removeLast() {
        if (isEmpty()) return null;
        right = (right - 1 + initSize) % initSize;
        T item = items[right];
        return item;
    }

    public void printDeque() {
        if (left < right) {
            for (int i = left; i < right; i++) {
                if (left == right - 1) {
                    System.out.println(items[i]);
                    break;
                }
                System.out.println(items[i]+" ");
            }
        } else if (left > right) {
            for (int i = left; i < initSize; i++) {
                System.out.println(items[i]+" ");
            }
            for (int i = 0; i < right; i++) {
                if (i == right-1) {
                    System.out.println(items[i]);
                    break;
                }
                System.out.println(items[i]+" ");
            }
        }
    }

    public T get(int index) {
        if (index < 0 || index >= size() || isEmpty()) return null;
        if (left < right) return items[index+left];
        else if (left > right) {
            if (index + left < initSize) {
                return items[index+left];
            } else {
                return items[(index+left)%initSize];
            }
        }
        return null;
    }

    public void resize(int newSize) {
        T[] newItems = (T[]) new Object[newSize];
        int curSize = size;
        if (left < right) {
            for (int i = left,j = 0;i < right && j < curSize; i++,j++) {
                newItems[j] = items[i];
            }
        } else if (left > right) {
            int j = 0;
            for (int i = left;j < initSize-left; i++,j++) {
                newItems[j] = items[i];
            }
            for (int i = 0;i < curSize; i++,j++) {
                newItems[j] = items[i];
            }
        }
        left = 0;
        right = curSize;
        items = newItems;
        initSize = newSize;
    }
}
