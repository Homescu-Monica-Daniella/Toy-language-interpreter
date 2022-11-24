package Model.Statements;

import Model.ProgramState.ProgramState;
import Model.ProgramState.InterfaceStack;

public class CompoundStatement implements InterfaceStatement {

    InterfaceStatement stmt1;
    InterfaceStatement stmt2;

    public CompoundStatement(InterfaceStatement stmt1, InterfaceStatement stmt2) {
        this.stmt1 = stmt1;
        this.stmt2 = stmt2;
    }

    @Override
    public ProgramState execute(ProgramState prgState) {
        InterfaceStack<InterfaceStatement> exeStack = prgState.getExeStack();
        exeStack.push(this.stmt2);
        exeStack.push(this.stmt1);
        prgState.setExeStack(exeStack);
        return prgState;
    }

    @Override
    public InterfaceStatement deepCopy() {
        return new CompoundStatement(this.stmt1.deepCopy(), this.stmt2.deepCopy());
    }

    @Override
    public String toString() {
        return this.stmt1.toString() + ";" + this.stmt2.toString();
    }

}
