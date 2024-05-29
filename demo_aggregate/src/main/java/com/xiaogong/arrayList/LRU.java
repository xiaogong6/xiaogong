package com.xiaogong.arrayList;

import java.util.LinkedList;

/**
 * @Program: demo-java
 * @Description:
 * @Author: xiongke
 * @Create: 2024-03-29
 */
public class LRU<E> {

    private final int maxSize;

    private final LinkedList<E> list = new LinkedList<>();

    public LRU(int maxSize) {
        this.maxSize = maxSize;
    }

    public void add(E e) {
        if (list.size() < maxSize) {
            list.addFirst(e);
        } else {
            list.removeLast();
            list.addFirst(e);
        }
    }

    public E get(int index) {
        E e = list.get(index);
        list.remove(e);
        add(e);
        return e;
    }

    @Override
    public String toString() {
        return list.toString();
    }

}
