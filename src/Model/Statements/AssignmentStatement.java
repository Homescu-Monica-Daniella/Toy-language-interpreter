package Model.Statements;

import Model.ProgramState.InterfaceHeap;
import Model.ProgramState.ProgramState;
import Model.ProgramState.InterfaceDictionary;
import Model.Types.InterfaceType;
import Model.Values.InterfaceValue;
import Model.Expressions.InterfaceExpression;
import Exceptions.ADTException;
import Exceptions.ExpressionEvaluationException;
import Exceptions.StatementExecutionException;

public class AssignmentStatement implements InterfaceStatement {
    String var;
    InterfaceExpression exp;

    public AssignmentStatement(String var, InterfaceExpression exp) {
        this.var = var;
        this.exp = exp;
    }

    @Override
    public ProgramState execute(ProgramState prgState) throws ADTException, ExpressionEvaluationException, StatementExecutionException {
        InterfaceDictionary<String, InterfaceValue> symTable = prgState.getSymTable();
        if (symTable.isDefined(this.var)) {
            InterfaceHeap heap = prgState.getHeap();
            InterfaceValue val = this.exp.evaluate(symTable, heap);
            InterfaceType typ = (symTable.get(this.var)).getType();
            if ((val.getType()).equals(typ))
                symTable.update(this.var, val);
            else throw new StatementExecutionException("Type of variable" + this.var + " and type of expression do not match!");
        } else throw new StatementExecutionException("Variable" + this.var + " was not declared before!");
        prgState.setSymTable(symTable);
        return null;
    }

    @Override
    public InterfaceDictionary<String, InterfaceType> typeCheck(InterfaceDictionary<String, InterfaceType> typeEnv) throws ADTException, ExpressionEvaluationException, StatementExecutionException {
        InterfaceType typeVar = typeEnv.get(this.var);
        InterfaceType typeExp = this.exp.typeCheck(typeEnv);
        if (typeVar.equals(typeExp))
            return typeEnv;
        else
            throw new StatementExecutionException("Assignment: right hand side and left hand side have different types!");
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
