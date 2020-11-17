package Model.ADT;
import Exceptions.ADTException;

public interface IDict<T1,T2>{

    void add(T1 v1, T2 v2) throws ADTException;
    void update(T1 v1, T2 v2) throws ADTException;
    void remove(T1 v1, T2 v2) throws ADTException;
    T2 lookup(T1 id) throws ADTException;
    boolean isDefined(T1 key) throws ADTException;
    String toString();
}
