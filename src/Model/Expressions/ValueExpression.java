package Model.Expressions;

import Model.ProgramState.InterfaceDictionary;
import Model.Values.InterfaceValue;

public class ValueExpression implements InterfaceExpression {

    InterfaceValue val;

    public ValueExpression(InterfaceValue val) {
        this.val = val;
    }

    @Override
    public InterfaceValue evaluate(InterfaceDictionary<String, InterfaceValue> symTable) {
        return this.val;
    }

    @Override
    public InterfaceExpression deepCopy() {
        return new ValueExpression(this.val);
    }

    @Override
    public String toString() {
        return this.val.toString();
    }

}
