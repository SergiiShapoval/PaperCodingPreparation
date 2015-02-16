package ua.shapoval.nonblocking;

/**
 * Created by Сергей on 13.02.2015.
 */
public class NonBlockingThreadPoll extends Thread{


    private final NonBlockingQueue queue;

    public NonBlockingThreadPoll(NonBlockingQueue queue) {
        this.queue = queue;
        start();
    }

    @Override
    public void run() {
        while (true){
            Object value = queue.poll();
            if (value != null)
                System.out.println(value);
        }
    }
}
