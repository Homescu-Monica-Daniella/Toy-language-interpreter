package Model.Statements;

import Exceptions.ADTException;
import Exceptions.ExpressionEvaluationException;
import Exceptions.StatementExecutionException;
import Model.Expressions.InterfaceExpression;
import Model.ProgramState.InterfaceDictionary;
import Model.ProgramState.InterfaceStack;
import Model.ProgramState.ProgramState;
import Model.Types.BooleanType;
import Model.Types.InterfaceType;
import Model.Values.BooleanValue;
import Model.Values.InterfaceValue;

public class WhileStatement implements InterfaceStatement {

    InterfaceExpression exp;
    InterfaceStatement stmt;

    public WhileStatement(InterfaceExpression exp, InterfaceStatement stmt) {
        this.exp = exp;
        this.stmt = stmt;
    }

    @Override
    public ProgramState execute(ProgramState prgState) throws ADTException, ExpressionEvaluationException, StatementExecutionException {
        InterfaceValue expVal = this.exp.evaluate(prgState.getSymTable(), prgState.getHeap());
        if (!expVal.getType().equals(new BooleanType()))
            throw new StatementExecutionException(String.format("%s is not evaluated to BooleanValue!", this.exp));
        InterfaceStack<InterfaceStatement> exeStack = prgState.getExeStack();
        if (((BooleanValue) expVal).getValue()) {
            exeStack.push(new WhileStatement(this.exp, this.stmt));
            exeStack.push(this.stmt);
        }
        return null;
    }

    @Override
    public InterfaceDictionary<String, InterfaceType> typeCheck(InterfaceDictionary<String, InterfaceType> typeEnv) throws ADTException, ExpressionEvaluationException, StatementExecutionException {
        InterfaceType typExp = this.exp.typeCheck(typeEnv);
        if (typExp.equals(new BooleanType())) {
            this.stmt.typeCheck(typeEnv.deepCopy());
            return typeEnv;
        }
        else
            throw new StatementExecutionException("The condition of WHILE is not of BooleanType!");
    }

    @Override
    public InterfaceStatement deepCopy() {
        return new WhileStatement(this.exp.deepCopy(), this.stmt.deepCopy());
    }

    @Override
    public String toString() {
        return "(while(" + this.exp.toString() + ") " + this.stmt.toString() + ")";
    }

}
