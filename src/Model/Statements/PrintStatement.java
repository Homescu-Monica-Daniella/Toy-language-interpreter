package Model.Statements;

import Exceptions.StatementExecutionException;
import Model.ProgramState.InterfaceDictionary;
import Model.ProgramState.ProgramState;
import Model.ProgramState.InterfaceList;
import Model.Types.InterfaceType;
import Model.Values.InterfaceValue;
import Model.Expressions.InterfaceExpression;
import Exceptions.ADTException;
import Exceptions.ExpressionEvaluationException;

public class PrintStatement implements InterfaceStatement {

    InterfaceExpression exp;

    public PrintStatement(InterfaceExpression exp) {
        this.exp = exp;
    }

    @Override
    public ProgramState execute(ProgramState prgState) throws ADTException, ExpressionEvaluationException {
        InterfaceList<InterfaceValue> out = prgState.getOut();
        out.add(this.exp.evaluate(prgState.getSymTable(), prgState.getHeap()));
        prgState.setOut(out);
        return null;
    }

    @Override
    public InterfaceDictionary<String, InterfaceType> typeCheck(InterfaceDictionary<String, InterfaceType> typeEnv) throws ADTException, ExpressionEvaluationException, StatementExecutionException {
        this.exp.typeCheck(typeEnv);
        return typeEnv;
    }

    public InterfaceStatement deepCopy() {
        return new PrintStatement(this.exp.deepCopy());
    }

    @Override
    public String toString() {
        return "Print(" + this.exp.toString() + ")";
    }

}
