package Model.Statements;

import Model.ProgramState.ProgramState;
import Model.ProgramState.InterfaceDictionary;
import Model.Types.InterfaceType;
import Model.Values.InterfaceValue;
import Exceptions.StmtExecException;

public class VariableDeclarationStatement implements InterfaceStatement {

    String var;
    InterfaceType typ;

    public VariableDeclarationStatement(String var, InterfaceType typ) {
        this.var = var;
        this.typ = typ;
    }

    @Override
    public ProgramState execute(ProgramState prgState) throws StmtExecException {
        InterfaceDictionary<String, InterfaceValue> symTable = prgState.getSymTable();
        if (symTable.isDefined(this.var))
            throw new StmtExecException("Variable " + this.var + " is already declared!");
        symTable.put(this.var, this.typ.defaultValue());
        prgState.setSymTable(symTable);
        return prgState;
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
