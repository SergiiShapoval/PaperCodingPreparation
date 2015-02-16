package ua.shapoval.singleton;

/**
 * Created by Сергей on 11.02.2015.
 */
public class SingletonWithHolder {
    private SingletonWithHolder() {}

    private static class SingletonHolder {
        public static final SingletonWithHolder instance = new SingletonWithHolder();
    }

    public static SingletonWithHolder getInstance()  {
        return SingletonHolder.instance;
    }
}