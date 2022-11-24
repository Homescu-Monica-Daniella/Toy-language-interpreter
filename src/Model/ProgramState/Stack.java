package Model.ProgramState;

import Exceptions.ADTException;

import java.util.ArrayDeque;
import java.util.Deque;

public class Stack<T> implements InterfaceStack<T> {

    Deque<T> stk;

    public Stack() {
        this.stk = new ArrayDeque<>();
    }

    @Override
    public boolean isEmpty() {
        return this.stk.isEmpty();
    }

    @Override
    public void push(T elem) {
        this.stk.push(elem);
    }

    @Override
    public T pop() throws ADTException {
        if (this.stk.isEmpty())
            throw new ADTException("Stack is empty!");
        return this.stk.pop();
    }

    @Override
    public String toString() {
        return this.stk.toString();
    }

}
