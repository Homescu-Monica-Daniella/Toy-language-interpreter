package Model.Statements;

import Model.ProgramState.ProgramState;
import Model.ProgramState.InterfaceDictionary;
import Model.Types.InterfaceType;
import Model.Values.InterfaceValue;
import Model.Expressions.InterfaceExpression;
import Exceptions.ADTException;
import Exceptions.ExprEvalException;
import Exceptions.StmtExecException;

public class AssignmentStatement implements InterfaceStatement {
    String var;
    InterfaceExpression exp;

    public AssignmentStatement(String var, InterfaceExpression exp) {
        this.var = var;
        this.exp = exp;
    }

    @Override
    public ProgramState execute(ProgramState prgState) throws ADTException, ExprEvalException, StmtExecException {
        InterfaceDictionary<String, InterfaceValue> symTable = prgState.getSymTable();
        if (symTable.isDefined(this.var)) {
            InterfaceValue val = this.exp.evaluate(symTable);
            InterfaceType typ = (symTable.get(this.var)).getType();
            if ((val.getType()).equals(typ))
                symTable.update(this.var, val);
            else throw new StmtExecException("Type of variable" + this.var + " and type of expression do not match!");
        } else throw new StmtExecException("Variable" + this.var + " was not declared before!");
        prgState.setSymTable(symTable);
        return prgState;
    }

    @Override
    public InterfaceStatement deepCopy() {
        return new AssignmentStatement(this.var, this.exp.deepCopy());
    }

    @Override
    public String toString() {
        return this.var + "=" + this.exp.toString();
    }

}
