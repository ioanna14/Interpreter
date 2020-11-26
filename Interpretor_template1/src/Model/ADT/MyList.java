package Model.ADT;

import Exceptions.ADTException;

import java.util.ArrayList;
import java.util.List;

public class MyList<T> implements IList<T> {
    List<T> list;

    public MyList() {
        list = new ArrayList<T>();
    }

    @Override
    public T get(int index) {
        return list.get(index);
    }

    @Override
    public void add(T v) {
        if (this.list.contains(v)) {
            throw new ADTException("Duplicate element!");
        }
        this.list.add(v);
    }

    @Override
    public T pop() {
        if (this.list.isEmpty()) {
            throw new ADTException("The list is empty!");
        }
        return this.list.remove(list.size() - 1);
    }

    @Override
    public String toString() {
        return "" + list;
    }

    @Override
    public void clear() {
        list.clear();
    }
}
