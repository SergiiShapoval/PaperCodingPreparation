package ua.shapoval.nonblocking;

/**
 * Created by Сергей on 13.02.2015.
 */
public class NonBlockingThreadOffer extends Thread{


    private final NonBlockingQueue queue;

    public NonBlockingThreadOffer(NonBlockingQueue queue) {
        this.queue = queue;
        start();
    }

    @Override
    public void run() {
        while (true) {
            int value = (int) (Math.random() * 50);
            queue.offer( value);
            System.out.println(value + " added;");
        }

    }
}
