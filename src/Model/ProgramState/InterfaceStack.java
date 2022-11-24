package Model.ProgramState;

import Exceptions.ADTException;

public interface InterfaceStack<T> {

    boolean isEmpty();

    void push(T elem);

    T pop() throws ADTException;

}
