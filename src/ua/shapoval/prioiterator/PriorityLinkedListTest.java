package ua.shapoval.prioiterator;

import java.util.Comparator;
import java.util.Iterator;

/**
 * Created by Сергей on 12.02.2015.
 */
public class PriorityLinkedListTest {

    public static void main(String[] args) {
        PriorityLinkedList<Integer> list = new PriorityLinkedList<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return -o1.compareTo(o2);
            }
        });
        list.add(1);
        list.add(3);
        list.add(5);
        list.add(2);
        list.add(4);
        System.out.println(list);



        Iterator<Integer> iterator = list.iterator(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });

        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }


    }
}
