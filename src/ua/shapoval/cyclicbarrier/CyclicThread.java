package ua.shapoval.cyclicbarrier;





public class CyclicThread extends Thread{

    private static int threadQty = 0;
    private int number = ++threadQty;

    private CyclicBarrier cyclicBarrier;


    public CyclicThread( CyclicBarrier cyclicBarrier){
        this.cyclicBarrier = cyclicBarrier;
        start();
    }

    @Override
    public void run(){

        try{
            cyclicBarrier.await();
            System.out.println(number + " has finished");

        } catch(InterruptedException e){
            System.out.println(number + " was interrupted");
        }
    }
}