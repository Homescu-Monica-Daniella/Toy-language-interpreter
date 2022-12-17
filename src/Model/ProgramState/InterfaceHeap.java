package Model.ProgramState;

import Exceptions.ADTException;
import Model.Values.InterfaceValue;
import java.util.HashMap;

public interface InterfaceHeap {

    HashMap<Integer, InterfaceValue> getContent();

    void setContent(HashMap<Integer, InterfaceValue> heap);

    boolean containsKey(Integer pos);

    InterfaceValue get(Integer pos) throws ADTException;

    int add(InterfaceValue val);

    void update(Integer pos, InterfaceValue val) throws ADTException;

}
