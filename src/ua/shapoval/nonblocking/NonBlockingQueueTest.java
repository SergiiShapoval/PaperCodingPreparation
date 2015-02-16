package ua.shapoval.nonblocking;

/**
 * Created by Сергей on 13.02.2015.
 */
public class NonBlockingQueueTest {

    public static void main(String[] args) {
        NonBlockingQueue<Integer> queue = new NonBlockingQueue<>();
        NonBlockingThreadOffer threadOffer = new NonBlockingThreadOffer(queue);
        NonBlockingThreadPoll threadPoll = new NonBlockingThreadPoll(queue);

    }
}
