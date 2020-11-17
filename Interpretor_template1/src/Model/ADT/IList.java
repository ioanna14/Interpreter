package Model.ADT;
import Exceptions.ADTException;

public interface IList<T> {
    T get(int index);
    void add(T v) throws ADTException;
    T pop() throws ADTException;
    String toString();
    void clear();
}
