package ua.shapoval.linkedlist;

/**
 * Created by Сергей on 12.02.2015.
 */
public class LinkedListTest {

    public static void main(String[] args) {

        LinkedList linkedList = new LinkedList();

        linkedList.add(3);
        linkedList.add(3);
        linkedList.add(5);
        linkedList.add(null);
        linkedList.add(6);


        System.out.println(linkedList);

        linkedList.remove(null);
        System.out.println(linkedList);

        linkedList.remove(3);
        System.out.println(linkedList);

        System.out.println("Value on index 2: "+ linkedList.get(2));
        System.out.println(linkedList);


    }
}
