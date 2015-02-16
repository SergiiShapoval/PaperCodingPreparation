package ua.shapoval.cyclicbarrier;

import java.util.*;




public class CyclicBarrier{

    private int threadLimit;

    private Runnable task;
    private boolean isTaskStarted;
    private int taskStartLimit;
    private boolean isBarrierReleasing;
    private Set<Thread> threadSet = new HashSet<>();
    private Set<Thread> threadBarrierSet = new HashSet<>();

    public CyclicBarrier(int threadLimit){
        this.threadLimit = threadLimit;
    }

    public CyclicBarrier(int threadLimit, Runnable task, int taskStartLimit){
        this(threadLimit);
        this.task = task;
        this.taskStartLimit = taskStartLimit > threadLimit ? threadLimit : taskStartLimit;
    }

    public Set<Thread> getThreadBarrierSet() {
        return threadBarrierSet;
    }

    public synchronized void await() throws InterruptedException{

        while(isBarrierReleasing){
            threadBarrierSet.add(Thread.currentThread());
            wait();
        }

        threadSet.add(Thread.currentThread());
        startAdditionalTask();
        while(threadLimit > threadSet.size()){
            if (isBarrierReleasing) break;
            wait();
        }
        isBarrierReleasing = true;

        threadSet.remove(Thread.currentThread());
        if(threadSet.isEmpty()) {
            isBarrierReleasing = false;
            System.out.println("First ten has finished");
            isTaskStarted = false;
        }
        notifyAll();

    }

    private boolean isBarrierReleasing() {return isBarrierReleasing;}

    private void startAdditionalTask(){
        if(task != null && !isTaskStarted && threadSet.size() == taskStartLimit){
            new Thread(task).start();
            isTaskStarted = true;
            System.out.println( "Size: " + threadSet.size() + " and additional task started");
        }

    }

    public synchronized void reset(){
        for( Thread thread: threadSet) thread.interrupt();
        threadSet.clear();
        isTaskStarted = false;
    }


}