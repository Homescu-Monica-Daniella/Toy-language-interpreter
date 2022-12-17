package Model.Statements;

import Model.ProgramState.InterfaceDictionary;
import Model.ProgramState.ProgramState;
import Model.ProgramState.InterfaceStack;
import Model.Types.BooleanType;
import Model.Types.InterfaceType;
import Model.Values.InterfaceValue;
import Model.Values.BooleanValue;
import Model.Expressions.InterfaceExpression;
import Exceptions.ADTException;
import Exceptions.ExpressionEvaluationException;
import Exceptions.StatementExecutionException;


public class ConditionalStatement implements InterfaceStatement {
    InterfaceExpression exp;
    InterfaceStatement thenStmt;
    InterfaceStatement elseStmt;

    public ConditionalStatement(InterfaceExpression exp, InterfaceStatement thenStmt, InterfaceStatement elseStmt) {
        this.exp = exp;
        this.thenStmt = thenStmt;
        this.elseStmt = elseStmt;
    }

    public ProgramState execute(ProgramState prgState) throws ADTException, ExpressionEvaluationException, StatementExecutionException {
        InterfaceValue val = this.exp.evaluate(prgState.getSymTable(), prgState.getHeap());
        if (val.getType().equals(new BooleanType())) {
            BooleanValue b = (BooleanValue) val;
            InterfaceStatement stmt;
            if (b.getValue())
                stmt = this.thenStmt;
            else
                stmt = this.elseStmt;
            InterfaceStack<InterfaceStatement> exeStack = prgState.getExeStack();
            exeStack.push(stmt);
            prgState.setExeStack(exeStack);
        }
        else throw new StatementExecutionException("Expression should have boolean result!");
        return null;
    }

    @Override
    public InterfaceDictionary<String, InterfaceType> typeCheck(InterfaceDictionary<String, InterfaceType> typeEnv) throws ADTException, ExpressionEvaluationException, StatementExecutionException {
        InterfaceType typExp = this.exp.typeCheck(typeEnv);
        if (typExp.equals(new BooleanType())) {
            this.thenStmt.typeCheck(typeEnv.deepCopy());
            this.elseStmt.typeCheck(typeEnv.deepCopy());
            return typeEnv;
        }
        else
            throw new StatementExecutionException("The condition of IF is not of BooleanType!");
    }

    @Override
    public InterfaceStatement deepCopy() {
        return new ConditionalStatement(this.exp.deepCopy(), this.thenStmt.deepCopy(), this.elseStmt.deepCopy());
    }

    @Override
    public String toString() {
        return "If " + this.exp.toString() + " Then " + this.thenStmt.toString() + " Else " + this.elseStmt.toString();
    }

}
