package Model.Statements;

import Exceptions.ADTException;
import Exceptions.ExpressionEvaluationException;
import Exceptions.StatementExecutionException;
import Model.ProgramState.InterfaceDictionary;
import Model.ProgramState.ProgramState;
import Model.ProgramState.InterfaceStack;
import Model.Types.InterfaceType;

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
        return null;
    }

    @Override
    public InterfaceDictionary<String, InterfaceType> typeCheck(InterfaceDictionary<String, InterfaceType> typeEnv) throws ADTException, ExpressionEvaluationException, StatementExecutionException {
        return this.stmt1.typeCheck(this.stmt1.typeCheck(typeEnv));
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
