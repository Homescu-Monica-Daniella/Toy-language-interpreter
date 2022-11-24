package Model.ProgramState;

import Exceptions.ADTException;

public interface InterfaceDictionary<K, V> {

    V get(K key) throws ADTException;

    void put(K key, V val);

    void remove(K key) throws ADTException;

    void update(K key, V val) throws ADTException;

    boolean isDefined(K key);

}
