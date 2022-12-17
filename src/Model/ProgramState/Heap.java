package Model.ProgramState;

import Exceptions.ADTException;
import Model.Values.InterfaceValue;
import java.util.HashMap;

public class Heap implements InterfaceHeap {

    HashMap<Integer, InterfaceValue> heap;
    Integer freeLocation;

    public Heap() {
        this.heap = new HashMap<>();
        this.freeLocation = 1;
    }

    @Override
    public HashMap<Integer, InterfaceValue> getContent() {
        return this.heap;
    }

    @Override
    public void setContent(HashMap<Integer, InterfaceValue> heap) {
        this.heap = heap;
    }

    @Override
    public boolean containsKey(Integer pos) {
        return this.heap.containsKey(pos);
    }

    @Override
    public InterfaceValue get(Integer pos) throws ADTException {
        if (!this.heap.containsKey(pos))
            throw new ADTException(String.format("%d is not in the heap!", pos));
        return this.heap.get(pos);
    }

    public int newValue() {
        this.freeLocation += 1;
        while (this.freeLocation == 0 || this.heap.containsKey(this.freeLocation))
            this.freeLocation += 1;
        return this.freeLocation;
    }

    @Override
    public int add(InterfaceValue val) {
        this.heap.put(this.freeLocation, val);
        Integer pos = this.freeLocation;
        this.freeLocation = newValue();
        return pos;
    }

    @Override
    public void update(Integer pos, InterfaceValue val) throws ADTException {
        if (!this.heap.containsKey(pos))
            throw new ADTException(String.format("%d is not in the heap!", pos));
        this.heap.put(pos, val);
    }

    @Override
    public String toString() {
        return this.heap.toString();
    }

}
