package Model.ProgramState;

import Model.Values.InterfaceValue;
import Model.Statements.InterfaceStatement;
import java.io.BufferedReader;

public class ProgramState {

    InterfaceStack<InterfaceStatement> exeStack;
    InterfaceDictionary<String, InterfaceValue> symTable;
    InterfaceList<InterfaceValue> out;
    InterfaceStatement prg;
    InterfaceDictionary<String, BufferedReader> fileTable;

    public ProgramState(InterfaceStack<InterfaceStatement> exeStack, InterfaceDictionary<String, InterfaceValue> symTable, InterfaceList<InterfaceValue> out, InterfaceStatement prg, InterfaceDictionary<String, BufferedReader> fileTable) {
        this.exeStack = exeStack;
        this.symTable = symTable;
        this.out = out;
        this.prg = prg.deepCopy();
        this.exeStack.push(this.prg);
        this.fileTable = fileTable;
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

    @Override
    public String toString() {
        return "ExeStack:\n" + this.exeStack.toString() + "\nSymTable:\n" + this.symTable.toString() + "\nOut:\n" + this.out.toString() + "\nFileTable:\n" + this.fileTable.toString() + "\n";
    }

}
