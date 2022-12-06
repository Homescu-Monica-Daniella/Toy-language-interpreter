package Model.ProgramState;

import Exceptions.ADTException;

import java.util.ArrayList;

public class List<T> implements InterfaceList<T> {
    ArrayList<T> lst;

    public List() {
        this.lst = new ArrayList<>();
    }

    @Override
    public void add(T elem) {
        this.lst.add(elem);
    }

    @Override
    public T getFirst() throws ADTException {
        if (this.lst.size() == 0)
            throw new ADTException("List is empty!");
        return this.lst.get(0);
    }

    @Override
    public T getLast() throws ADTException {
        if (this.lst.size() == 0)
            throw new ADTException("List is empty!");
        return this.lst.get(this.lst.size() - 1);
    }

    @Override
    public String toString() {
        return this.lst.toString();
    }

}
