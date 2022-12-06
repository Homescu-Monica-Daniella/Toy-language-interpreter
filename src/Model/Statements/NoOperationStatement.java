package Model.Statements;

import Model.ProgramState.ProgramState;

public class NoOperationStatement implements InterfaceStatement {

    @Override
    public ProgramState execute(ProgramState prgState) {
        return null;
    }

    @Override
    public InterfaceStatement deepCopy() {
        return new NoOperationStatement();
    }

    @Override
    public String toString() {
        return "Nop";
    }

}
