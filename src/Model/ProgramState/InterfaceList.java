package Model.ProgramState;

import Exceptions.ADTException;

public interface InterfaceList<T> {

    void add(T elem);

    T getFirst() throws ADTException;

    T getLast() throws ADTException;

}
