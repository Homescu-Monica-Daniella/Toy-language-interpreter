package Model.Expressions;

import Exceptions.ADTException;
import Exceptions.ExpressionEvaluationException;
import Model.ProgramState.InterfaceDictionary;
import Model.ProgramState.InterfaceHeap;
import Model.Types.InterfaceType;
import Model.Values.InterfaceValue;

public class ValueExpression implements InterfaceExpression {

    InterfaceValue val;

    public ValueExpression(InterfaceValue val) {
        this.val = val;
    }

    @Override
    public InterfaceValue evaluate(InterfaceDictionary<String, InterfaceValue> symTable, InterfaceHeap heap) {
        return this.val;
    }

    @Override
    public InterfaceType typeCheck(InterfaceDictionary<String, InterfaceType> typeEnv) throws ADTException, ExpressionEvaluationException {
        return this.val.getType();
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
