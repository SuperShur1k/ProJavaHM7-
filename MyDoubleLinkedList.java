package org.example.lesson7;

import java.util.Iterator;

public class MyDoubleLinkedList implements MyLinkedList {
    private static class Node {
        int value;
        Node prev = null;
        Node next = null;

        public Node(int value) {
            this.value = value;
        }

        public Node(int value, Node prev, Node next) {
            this.value = value;

            this.prev = prev;
            if(prev != null)
                prev.next = this;

            this.next = next;
            if(next != null)
                next.prev = this;

        }
    }

    private int size = 0;
    private Node head;
    private Node tail;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean contains(int value) {
        Node node = head;
        while (node != null) {
            if(node.value == value) {
                return true;
            }
            node = node.next;
        }
        return false;
    }

    @Override
    public void set(int index, int value) {
        Node node = getNodeByIndex(index);
        node.value = value;
    }

    @Override
    public void add(int value) {
        Node node = null;
        if(tail == null) { // элементов нет
            node = new Node(value);
            head = node;
        } else {
            node = new Node(value, tail, null);
            tail.next = node;
        }
        tail = node;
        size++;
    }

    @Override
    public void add(int index, int value) {
        if(index < 0 || index > size()) {
            throw new IndexOutOfBoundsException();
        }
        if(size() == 0 && index == 0) { // когда нет никаких элементов
            add(value);
            return;
        }
        if(index == 0) {
            Node node = new Node(value, null, head);
            head = node;
        } else if(index == size()) {
            Node node = new Node(value, tail, null);
            tail = node;
        } else {
            Node node = getNodeByIndex(index);
            Node prev = node.prev;
            new Node(value, prev, node);
        }
        size++;
    }

    @Override
    public void remove(int index) {
        if(index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }
        if(index == 0 && head != null) { // удаление первого
            head = head.next;
            if (head != null)
            {
                head.prev = null;
            }
        } else if (index == size() - 1) {
            tail = tail.prev;
            tail.next = null;
        }
        else {
            Node c = getNodeByIndex(index);
            Node p = c.prev;
            Node n = c.next;
            if(p != null)
                p.next = n;
            if(n != null)
                n.prev = p;
        }
        size--;
    }

    @Override
    public int get(int index) {
        Node node = getNodeByIndex(index);
        return node.value;
    }

    private Node getNodeByIndex(int index) {
        if(index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }
        Node node = head;
        while (node != null && index > 0) {
            index--;
            node = node.next;
        }
        return node;
    }

    @Override
    public Iterator<Integer> iterator() {
        return null;
    }

    @Override
    public Iterator<Integer> backward() {
        return null;
    }

    @Override
    public int removeFirst() {
        return 0;
    }

    @Override
    public void addFirst(int i) {

    }

    @Override
    public int getFirst() {
        return 0;
    }
}
