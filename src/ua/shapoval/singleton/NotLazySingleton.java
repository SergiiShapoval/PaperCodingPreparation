package ua.shapoval.singleton;


public class NotLazySingleton{

    private static NotLazySingleton  instance = new NotLazySingleton();

    private NotLazySingleton(){}

    public static NotLazySingleton getInstance(){ return instance;}
}