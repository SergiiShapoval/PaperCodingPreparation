package ua.shapoval.nonblocking;


import java.util.concurrent.atomic.*;


public class NonBlockingQueue<T>{

    AtomicReference<Node<T>> first = new AtomicReference<>();
    AtomicReference<Node<T>> last = new AtomicReference<>();
    AtomicInteger size = new AtomicInteger();

    private class Node<T>{
        T value;
        AtomicReference<Node<T>> next = new AtomicReference<>();

        private Node(T value){
            this.value = value;
        }
    }

    public NonBlockingQueue(){}

    public int size(){
        return size.get();
    }

    public boolean isEmpty(){
        return size.get() == 0 ;
    }

    public boolean offer (T value){

        Node<T> current = new Node<>(value);

        while(true){
            if(last.compareAndSet(null, current)){
                first.getAndSet(current);
                size.incrementAndGet();
                return true;
            }

            Node <T> currentLast = last.get();
            if (currentLast == null){
                continue;
            }
            currentLast.next.getAndSet(current);

            if (!last.compareAndSet(currentLast, current)){
                continue;
            }
            size.incrementAndGet();
            return true;
        }
    }

    public T poll(){
        Node firstNode;
        while(true){
            if((firstNode = first.get()) == null ) return null;

            if(last.compareAndSet(firstNode, null)){
                size.decrementAndGet();
                first.getAndSet(null);
                return (T) firstNode.value;
            }

            if( first.compareAndSet(firstNode, (Node<T>) firstNode.next.get())){
                size.decrementAndGet();
                return (T) firstNode.value;
            }
        }
    }

    public T peek(){
        Node firstNode = first.get();
        if  (firstNode == null) return null;
        return (T) firstNode.value;
    }
}