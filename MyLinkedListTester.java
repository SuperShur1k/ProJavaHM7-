package org.example.lesson7;

import java.util.Iterator;

public class MyLinkedListTester {
    public static void main(String[] args) {
        MyLinkedList list = new MyLinkedListImpl();
        list.add(10);
        list.add(5);
        System.out.println(list);

        System.out.println(list.contains(20));
        System.out.println(list.contains(5));

        list.set(0, 100);
        System.out.println(list);
//        list.set(2, 1000);
//        System.out.println(list);
        System.out.println(list.get(1));

        list.add(0, 1000);
        System.out.println(list);

        list.add(3, 1);
        System.out.println(list);

        list.add(2, 2);
        System.out.println(list);

        list.remove(0);
        System.out.println(list);

        list.remove(1);
        System.out.println(list);

//        HomeWork
        System.out.println("HomeWork:");
        Iterator<Integer> listIterator = list.iterator();
        while (listIterator.hasNext()) {
            System.out.println("element: " + listIterator.next());
        }
    }
}
