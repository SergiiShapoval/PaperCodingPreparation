package ua.shapoval.priority;

/**
 * Created by Сергей on 12.02.2015.
 */
public class PriorityQueueTest {

    public static void main(String[] args) {
        PriorityQueue priorityQueue = new PriorityQueue();

        System.out.println(priorityQueue.offer(null));
        System.out.println(priorityQueue);

        priorityQueue.offer(3);
        priorityQueue.offer(4);
        priorityQueue.offer(2);
        priorityQueue.offer(10);

        System.out.println(priorityQueue);

        priorityQueue.offer(4);
        System.out.println(priorityQueue);

        System.out.println(priorityQueue.poll());
        System.out.println(priorityQueue);
        System.out.println(priorityQueue.poll());
        System.out.println(priorityQueue);
        System.out.println(priorityQueue.peek());
        System.out.println(priorityQueue);

    }
}
