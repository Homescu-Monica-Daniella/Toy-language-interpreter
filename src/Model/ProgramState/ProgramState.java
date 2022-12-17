package Model.ProgramState;

import Exceptions.ADTException;
import Exceptions.ExpressionEvaluationException;
import Exceptions.StatementExecutionException;
import Model.Values.InterfaceValue;
import Model.Statements.InterfaceStatement;
import java.io.BufferedReader;

public class ProgramState {

    InterfaceStack<InterfaceStatement> exeStack;
    InterfaceDictionary<String, InterfaceValue> symTable;
    InterfaceList<InterfaceValue> out;
    InterfaceStatement prg;
    InterfaceDictionary<String, BufferedReader> fileTable;
    InterfaceHeap heap;
    Integer id;
    static Integer lastId = 0;

    public synchronized int setId() {
        lastId += 1;
        return lastId;
    }

    public ProgramState(InterfaceStack<InterfaceStatement> exeStack, InterfaceDictionary<String, InterfaceValue> symTable, InterfaceList<InterfaceValue> out, InterfaceStatement prg, InterfaceDictionary<String, BufferedReader> fileTable, InterfaceHeap heap) {
        this.exeStack = exeStack;
        this.symTable = symTable;
        this.out = out;
        this.prg = prg.deepCopy();
        this.exeStack.push(this.prg);
        this.fileTable = fileTable;
        this.heap = heap;
        this.id = setId();
    }

    public ProgramState(InterfaceStack<InterfaceStatement> exeStack, InterfaceDictionary<String, InterfaceValue> symTable, InterfaceList<InterfaceValue> out, InterfaceDictionary<String, BufferedReader> fileTable, InterfaceHeap heap) {
        this.exeStack = exeStack;
        this.symTable = symTable;
        this.out = out;
        this.fileTable = fileTable;
        this.heap = heap;
        this.id = setId();
    }

    public InterfaceStack<InterfaceStatement> getExeStack() {
        return this.exeStack;
    }

    public InterfaceDictionary<String, InterfaceValue> getSymTable() {
        return this.symTable;
    }

    public InterfaceList<InterfaceValue> getOut() {
        return this.out;
    }

    public InterfaceDictionary<String, BufferedReader> getFileTable() {
        return this.fileTable;
    }

    public InterfaceHeap getHeap() {
        return this.heap;
    }

    public void setExeStack(InterfaceStack<InterfaceStatement> exeStack) {
        this.exeStack = exeStack;
    }

    public void setSymTable(InterfaceDictionary<String, InterfaceValue> symTable) {
        this.symTable = symTable;
    }

    public void setOut(InterfaceList<InterfaceValue> out) {
        this.out = out;
    }

    public void setFileTable(InterfaceDictionary<String, BufferedReader> fileTable) {
        this.fileTable = fileTable;
    }

    public void setHeap(InterfaceHeap heap) {
        this.heap = heap;
    }

    public boolean isNotCompleted() {
        return this.exeStack.isEmpty();
    }

    public ProgramState oneStep() throws ADTException, ExpressionEvaluationException, StatementExecutionException {
        if (this.exeStack.isEmpty())
            throw new StatementExecutionException("Execution stack is empty!");
        InterfaceStatement currentStmt = this.exeStack.pop();
        return currentStmt.execute(this);
    }

    @Override
    public String toString() {
        return "Id: " + this.id + "\nExeStack: " + this.exeStack.toString() + "\nSymTable: " + this.symTable.toString() + "\nOut: " + this.out.toString() + "\nFileTable: " + this.fileTable.toString() + "\nHeap: " + this.heap.toString() + "\n";
    }

}
