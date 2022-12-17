package Model.Statements;

import Exceptions.ADTException;
import Exceptions.ExpressionEvaluationException;
import Exceptions.StatementExecutionException;
import Model.ProgramState.*;
import Model.Types.InterfaceType;
import Model.Values.InterfaceValue;

import java.util.Map;

public class ForkStatement implements InterfaceStatement {

    InterfaceStatement stmt;

    public ForkStatement(InterfaceStatement stmt) {
        this.stmt = stmt;
    }

    @Override
    public ProgramState execute(ProgramState prgState) throws ADTException, ExpressionEvaluationException, StatementExecutionException {
        InterfaceStack<InterfaceStatement> newStack = new Stack<>();
        newStack.push(this.stmt);
        InterfaceDictionary<String, InterfaceValue> newSymTable = new Dictionary<>();
        for (Map.Entry<String, InterfaceValue> entry: prgState.getSymTable().getContent().entrySet())
            newSymTable.put(entry.getKey(), entry.getValue());
        return new ProgramState(newStack, newSymTable, prgState.getOut(), prgState.getFileTable(), prgState.getHeap());
    }

    @Override
    public InterfaceDictionary<String, InterfaceType> typeCheck(InterfaceDictionary<String, InterfaceType> typeEnv) throws ADTException, ExpressionEvaluationException, StatementExecutionException {
        this.stmt.typeCheck(typeEnv.deepCopy());
        return typeEnv;
    }

    @Override
    public InterfaceStatement deepCopy() {
        return new ForkStatement(this.stmt.deepCopy());
    }

    @Override
    public String toString() {
        return "Fork(" + this.stmt.toString() + ")";
    }

}
