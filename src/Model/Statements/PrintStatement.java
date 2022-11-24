package Model.Statements;

import Model.ProgramState.ProgramState;
import Model.ProgramState.InterfaceList;
import Model.Values.InterfaceValue;
import Model.Expressions.InterfaceExpression;
import Exceptions.ADTException;
import Exceptions.ExprEvalException;

public class PrintStatement implements InterfaceStatement {

    InterfaceExpression exp;

    public PrintStatement(InterfaceExpression exp) {
        this.exp = exp;
    }

    @Override
    public ProgramState execute(ProgramState prgState) throws ADTException, ExprEvalException {
        InterfaceList<InterfaceValue> out = prgState.getOut();
        out.add(this.exp.evaluate(prgState.getSymTable()));
        prgState.setOut(out);
        return prgState;
    }

    public InterfaceStatement deepCopy() {
        return new PrintStatement(this.exp.deepCopy());
    }

    @Override
    public String toString() {
        return "Print(" + this.exp.toString() + ")";
    }

}
