package com.leogomesdev;

import java.util.ArrayList;
import java.util.List;

/**
 * An simple example of FIFO - First In First Out
 *
 * @param <E>
 */
public class MyQueue<E> {
    private Integer index = 0;
    private List<E> items = new ArrayList<>();

    public void addItem(E item) {
        this.items.add(item);
    }

    public E next() {
        E item = this.items.get(this.index);
        this.items.remove(this.index);
        return item;
    }
}
