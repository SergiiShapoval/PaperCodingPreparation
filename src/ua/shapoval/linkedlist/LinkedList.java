package ua.shapoval.linkedlist;


public class LinkedList{

    private Node first;
    private Node last;
    private int size;

    private class Node{
        private Node next;
        private Node previous;
        private Integer value;

        private Node(){}
    }


    public int size(){return size;}
    public boolean isEmpty(){return size()==0;}

    public void add (Integer value){
        Node current = new Node();
        current.value = value;

        if (isEmpty()){
            first = current;
            last = current;
        }   else{
            last.next = current;
            current.previous = last;
            last = last.next;
        }
        size++;
    }

    public boolean remove(Object value){
        if (isEmpty()) return false;


            if (value == first.value  ||
                    (value != null && value.equals(first.value)) ){
                first = first.next;
                if (first!=null) first.previous = null;
                size--;
                return true;
            }

            Node parentDeleteNode = findParentNodeByValue(value);
            if  (parentDeleteNode == null) return false;
            parentDeleteNode.next = parentDeleteNode.next.next;
            if(parentDeleteNode.next != null ) parentDeleteNode.next.previous = parentDeleteNode;
            size--;
            return true;
    }

    private Node findParentNodeByValue(Object value){
        if  (first == null) return null;
        Node current = first;
        while(current.next != null ){
            if( value == current.next.value ||
                    (value != null && value.equals(current.next.value)))
            return current;
            current = current.next;
        }
        return null;
    }

    public Integer get(int index){
        if(index >= size) throw new IndexOutOfBoundsException();
        int endIndex = size() - index - 1;
        Node valueNode;
        if (index > endIndex){
            valueNode = last;
            for(int i = 0; i < endIndex; i++){
                valueNode = valueNode.previous;
            }
        } else{
            valueNode = first;
            for(int i = 0; i < index; i++){
                valueNode = valueNode.next;
            }
        }
        return valueNode.value;
    }


    @Override
    public String toString() {
        return "LinkedList{" +
                toStringBuilder()
                +'}';
    }

    private String toStringBuilder() {
        if (first == null)  return "";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(first.value);
        Node current = first;
        while (current.next != null) {
            stringBuilder.append(", ");
            current = current.next;
            stringBuilder.append(current.value);
        }
        return stringBuilder.toString();
    }
}

