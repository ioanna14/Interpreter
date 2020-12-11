package Model.ADT;

import java.util.HashMap;
import Exceptions.ADTException;

public class Dict<T1, T2> implements IDict<T1, T2> {
    HashMap<T1, T2> dictionary;

    public Dict() {
        dictionary = new HashMap<T1, T2>();
    }

    public Dict(IDict<T1, T2> oldDictionary) {
        this.dictionary = new HashMap<T1, T2>();
        for (T1 key : oldDictionary.getKeys()) {
            this.dictionary.put(key, oldDictionary.lookup(key));
        }
    }

    @Override
    public HashMap<T1, T2> getContent() {
        return this.dictionary;
    }

    @Override
    public void add(T1 key, T2 value) {
        if (this.dictionary.containsKey(key)) {
            throw new ADTException("Duplicate key!");
        }
        this.dictionary.put(key, value);
    }

    @Override
    public void update(T1 key, T2 newValue) {
        if (!this.dictionary.containsKey(key)) {
            throw new ADTException("The key does not exist!");
        }
        this.dictionary.replace(key, newValue);
    }

    @Override
    public void remove(T1 key, T2 value) throws ADTException {
        if (!this.dictionary.containsKey(key)) {
            throw new ADTException("The key does not exist!");
        }
        this.dictionary.remove(key, value);
    }

    @Override
    public T2 lookup(T1 key) {
        if (!this.dictionary.containsKey(key)) {
            throw new ADTException("The key does not exist!");
        }
        return this.dictionary.get(key);
    }

    @Override
    public Iterable<T1> getKeys() {
        return dictionary.keySet();
    }

    @Override
    public boolean isDefined(T1 key) {
        return dictionary.containsKey(key);
    }

    @Override
    public String toString() {
        return "" + dictionary;
    }
}
