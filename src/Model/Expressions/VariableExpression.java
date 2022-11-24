package Model.Expressions;

import Model.ProgramState.InterfaceDictionary;
import Model.Values.InterfaceValue;
import Exceptions.ADTException;

public class VariableExpression implements InterfaceExpression {

    String var;

    public VariableExpression(String var) {
        this.var = var;
    }

    @Override
    public InterfaceValue evaluate(InterfaceDictionary<String, InterfaceValue> symTable) throws ADTException {
        return symTable.get(this.var);
    }

    @Override
    public InterfaceExpression deepCopy() {
        return new VariableExpression(this.var);
    }

    @Override
    public String toString() {
        return this.var;
    }

}
