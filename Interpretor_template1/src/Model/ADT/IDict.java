package Model.ADT;
import Exceptions.ADTException;
import java.util.HashMap;

public interface IDict<T1,T2>{
    HashMap<T1, T2> getContent();
    void add(T1 v1, T2 v2) throws ADTException;
    void update(T1 v1, T2 v2) throws ADTException;
    void remove(T1 v1, T2 v2) throws ADTException;
    T2 lookup(T1 id) throws ADTException;
    boolean isDefined(T1 key) throws ADTException;
    String toString();
}
