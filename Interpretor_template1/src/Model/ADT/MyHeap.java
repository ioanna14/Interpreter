package Model.ADT;

import Exceptions.ADTException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MyHeap<T2> implements IHeap<Integer, T2> {
    HashMap<Integer, T2> heap;
    int next = 1;
    ArrayList<Integer> freeSlots;

    public MyHeap(HashMap<Integer, T2> heap) {
        this.heap = heap;
        this.freeSlots = new ArrayList<Integer>();
    }

    public MyHeap() {
        this.heap = new HashMap<>();
        this.freeSlots = new ArrayList<Integer>();
    }

    @Override
    public HashMap<Integer, T2> getContent() {
        return this.heap;
    }

    @Override
    public void setContent(Map<Integer, T2> heap) {
        this.heap = (HashMap<Integer, T2>) heap;
    }

    @Override
    public int add(T2 value) {
        this.heap.put(next, value);
        return this.next++;
    }

    @Override
    public void update(Integer key, T2 newValue) {
        if (!this.heap.containsKey(key)) {
            throw new ADTException("The key does not exist!");
        }
        this.heap.replace(key, newValue);
    }

    @Override
    public void remove(Integer key, T2 value) throws ADTException {
        if (!this.heap.containsKey(key)) {
            throw new ADTException("The key does not exist!");
        }
        this.heap.remove(key, value);
    }

    @Override
    public T2 lookup(Integer key) {
        if (!this.heap.containsKey(key)) {
            throw new ADTException("The key does not exist!");
        }
        return this.heap.get(key);
    }

    @Override
    public boolean isDefined(Integer key) {
        return heap.containsKey(key);
    }

    @Override
    public String toString() {
        return "" + heap;
    }
}
