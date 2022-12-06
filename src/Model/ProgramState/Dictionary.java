package Model.ProgramState;

import Exceptions.ADTException;
import Model.Values.InterfaceValue;

import java.util.HashMap;

public class Dictionary<K, V> implements InterfaceDictionary<K, V> {

    HashMap<K, V> dict;

    public Dictionary() {
        this.dict = new HashMap<>();
    }

    @Override
    public V get(K key) throws ADTException {
        if (!isDefined(key))
            throw new ADTException(key + " is not defined!");
        return this.dict.get(key);
    }

    @Override
    public void put(K key, V val) {
        this.dict.put(key, val);
    }

    @Override
    public void remove(K key) throws ADTException {
        if (!isDefined(key))
            throw new ADTException(key + " is not defined!");
        this.dict.remove(key);
    }

    @Override
    public void update(K key, V val) throws ADTException{
        if (!isDefined(key))
            throw new ADTException(key + " is not defined!");
        this.dict.put(key, val);
    }

    @Override
    public boolean isDefined(K key) {
        return this.dict.containsKey(key);
    }

    @Override
    public String toString() {
        return this.dict.toString();
    }

    @Override
    public HashMap<K, V> getContent() {
        return this.dict;
    }

}
