package ua.shapoval.cyclicbarrier;



public class AdditionalTask implements Runnable{

    @Override
    public void run(){
        System.out.println("Additional task processing");
    }

}