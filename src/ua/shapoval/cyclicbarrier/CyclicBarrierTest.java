package ua.shapoval.cyclicbarrier;

import java.util.*;




public class CyclicBarrierTest{



    public static void main (String ... args){

        int threadLimit = 10;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(threadLimit, new AdditionalTask(), 1 );
        int threadQty = 7;

        List<Thread> threadList = createThreads(threadQty, cyclicBarrier);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        cyclicBarrier.reset();

        waitThreads(threadList);
        System.out.println("Reset has been done");
        threadQty = 20;

        threadList = createThreads(threadQty, cyclicBarrier);


        waitThreads(threadList);
        System.out.println("Test has finished");

        System.out.println(cyclicBarrier.getThreadBarrierSet().size());
        System.out.println(cyclicBarrier.getThreadBarrierSet());
    }

    public static List<Thread> createThreads( int threadQty, CyclicBarrier cyclicBarrier){
        List<Thread> threads = new ArrayList<>();

        for ( int i = 0; i < threadQty; i++){
            threads.add( new CyclicThread( cyclicBarrier));
        }

        return threads;
    }

    public static void waitThreads(List<Thread> threads){
        for( Thread thread: threads)
            try{
                thread.join();
            } catch(InterruptedException e){
                System.out.println("Main interrupted");
            }
    }
}