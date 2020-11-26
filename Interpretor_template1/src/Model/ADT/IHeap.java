package Model.ADT;

import Exceptions.ADTException;
import java.util.HashMap;
import java.util.Map;

public interface IHeap<Integer, T2> {
    HashMap<Integer, T2> getContent();
    void setContent(Map<Integer, T2> heap);
    int add(T2 v2) throws ADTException;
    void update(Integer v1, T2 v2) throws ADTException;
    void remove(Integer v1, T2 v2) throws ADTException;
    T2 lookup(Integer key);
    boolean isDefined(Integer key) throws ADTException;
    String toString();
}
