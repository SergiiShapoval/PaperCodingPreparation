package ua.shapoval.singleton;



public class SyncSingle{

    private static volatile SyncSingle instance;

    private SyncSingle(){}

    public static SyncSingle getInstance(){
        if  (instance == null) {
            synchronized (SyncSingle.class) {
                if (instance == null)
                   instance = new SyncSingle();
            }
        }
        return instance;
    }

    public void function(){}
}