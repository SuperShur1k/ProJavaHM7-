package org.example.lesson7;

import java.util.Iterator;

public class MyLinkedListImpl implements MyLinkedList {

    // ссылка на первый элемент списка
    private Node head;

    @Override
    public String toString() {
        // [1,3]
        String result = "[";
        Node n = head;
        while(n != null) {
            int v = n.getValue();
            result += v;
            n = n.getNext();
            if ( n != null)
            {
                result += ", ";
            }
        }
        result += "]";
        return result;
    }

    @Override
    public int removeFirst() {
        int result = get(0);
        remove(0);
        return result;
    }

    @Override
    public void addFirst(int i) {
        add(0, i);
    }

    @Override
    public int getFirst() {
        return get(0);
    }

    // узел листа
    // содержит ссылки на предыдущий/следующий узел и значение
    private static class Node {
        private int value; // значение
        private Node next; // ссылка на следующий узел

        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    // размер контейнера
    private int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean contains(int value) {
        // если ли узел с таким значением
        // пробежаться по всем узлам от head до null
        // вернуть true если найдется узел с нужным значением
        Node n = head;
        while (n != null) {
            if(n.getValue() == value) {
                return true;
            }
            n = n.getNext();
        }
        return false;
    }


    private Node getNodeByIndex(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException();
        }
        // возвращает узел с определенным индексом
        // если при итерации узел за границами контейнера то выбрасываем IndexOutOfBoundsException
        Node n = head;
        for (int i = 0; i < index; i++) {
            if(n != null) {
                n = n.getNext();
            } else {
                throw new IndexOutOfBoundsException();
            }
        }
        if ( n == null) {
            throw new IndexOutOfBoundsException();
        }
        return n;
    }

    @Override
    public void set(int index, int value) {
        // получаем узел по индексу
        Node n = getNodeByIndex(index);
        // устанавлиываем его значение в value
        if (n != null) {
            n.setValue(value);
        }
    }

    // добавляет узел в конец
    @Override
    public void add(int value) {
        // увеличиваем size
        size++;
        // создаем новый Node
        Node node = new Node(value, null);
        // если head == null то наш новый узел это head
        if(head == null) {
            head = node;
            return;
        }

        // начиная с head идем по элементам
        Node n = head;
        // когда доходим до элемента у которого next == null меняем его next на наш узел
        while (n.getNext() != null) {
            n = n.getNext();
        }
        n.setNext(node);
    }

    @Override
    public void add(int index, int value) {
        // добавление элемента в произвольную позицию
        if(index < 0 || (index > size())) {
            throw new IndexOutOfBoundsException();
        }
        size++;
        // элементов нет - вставляем новый узел в head
        if(index == 0) {
            Node prevHead = head;
            head = new Node(value, prevHead);
        } else {
            Node prev = getNodeByIndex(index - 1);
            Node next = prev.next;
            Node newNode = new Node(value, next);
            prev.setNext(newNode);
        }
    }

    @Override
    public void remove(int index) {
        if(index < 0 || (index >= size())) {
            throw new IndexOutOfBoundsException();
        }
        if(index == 0) {
            if(head == null) {
                return;
            }
            head = head.getNext();
            return;
        }
        Node prev = getNodeByIndex(index - 1);
        Node next = getNodeByIndex(index).getNext();
        prev.setNext(next);
        size--;
    }

    @Override
    public int get(int index) {
        // найти узел по индексу
        Node node = getNodeByIndex(index);
        // вернуть его значение
        return node.getValue();
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<>() {

            Node node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public Integer next() {
                int result = node.getValue();
                node = node.getNext();
                return result;
            }
        };
    }

    @Override
    public Iterator<Integer> backward() {
        return null;
    }
}
