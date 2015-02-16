package ua.shapoval.prioiterator;

import java.util.*;

/**
 * Created by Сергей on 12.02.2015.
 */
public class PriorityLinkedList<T> {

    private LinkedList<T> list = new LinkedList<>();
    private Comparator<T> comparator ;


    public PriorityLinkedList(Comparator<T> comparator){
        this.comparator = comparator;
    }

    public void add(T value) {
        int positionToInsert = 0;
        while (positionToInsert < list.size()){
            if (comparator.compare(list.get(positionToInsert), value) < 0) break;
            positionToInsert++;
        }
        list.add(positionToInsert, value);
    }

    public Iterator<T> iterator(Comparator<T> comparator){
        List<T> listCopy = (List<T>) list.clone();
        Collections.sort(listCopy, comparator);
        return listCopy.iterator();
    }

    public Iterator<T> iterator(){
        return list.iterator();
    }

    public Iterator<T> descendingIterator(){
        return list.descendingIterator();
    }

    public boolean remove(Object value){
        return list.remove(value);
    }


    @Override
    public String toString() {
        return list.toString();
    }
}
