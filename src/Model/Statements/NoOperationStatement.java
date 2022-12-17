package Model.Statements;

import Exceptions.ADTException;
import Exceptions.ExpressionEvaluationException;
import Exceptions.StatementExecutionException;
import Model.ProgramState.InterfaceDictionary;
import Model.ProgramState.ProgramState;
import Model.Types.InterfaceType;

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
    public InterfaceDictionary<String, InterfaceType> typeCheck(InterfaceDictionary<String, InterfaceType> typeEnv) throws ADTException, ExpressionEvaluationException, StatementExecutionException {
        return typeEnv;
    }

    @Override
    public String toString() {
        return "Nop";
    }

}
