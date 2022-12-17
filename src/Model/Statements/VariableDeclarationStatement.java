package Model.Statements;

import Exceptions.ADTException;
import Exceptions.ExpressionEvaluationException;
import Model.ProgramState.ProgramState;
import Model.ProgramState.InterfaceDictionary;
import Model.Types.InterfaceType;
import Model.Values.InterfaceValue;
import Exceptions.StatementExecutionException;

public class VariableDeclarationStatement implements InterfaceStatement {

    String var;
    InterfaceType typ;

    public VariableDeclarationStatement(String var, InterfaceType typ) {
        this.var = var;
        this.typ = typ;
    }

    @Override
    public ProgramState execute(ProgramState prgState) throws StatementExecutionException {
        InterfaceDictionary<String, InterfaceValue> symTable = prgState.getSymTable();
        if (symTable.isDefined(this.var))
            throw new StatementExecutionException("Variable " + this.var + " is already declared!");
        symTable.put(this.var, this.typ.defaultValue());
        prgState.setSymTable(symTable);
        return null;
    }

    @Override
    public InterfaceDictionary<String, InterfaceType> typeCheck(InterfaceDictionary<String, InterfaceType> typeEnv) throws ADTException, ExpressionEvaluationException, StatementExecutionException {
        typeEnv.put(this.var, this.typ);
        return typeEnv;
    }

    @Override
    public InterfaceStatement deepCopy() {
        return new VariableDeclarationStatement(this.var, this.typ);
    }

    @Override
    public String toString() {
        return this.typ.toString() + " " + this.var;
    }

}
