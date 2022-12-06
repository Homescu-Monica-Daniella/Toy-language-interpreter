package Model.Expressions;

import Exceptions.ADTException;
import Exceptions.ExprEvalException;
import Model.ProgramState.InterfaceDictionary;
import Model.ProgramState.InterfaceHeap;
import Model.Values.InterfaceValue;
import Model.Values.ReferenceValue;

public class HeapReadingExpression implements InterfaceExpression {

    InterfaceExpression exp;

    public HeapReadingExpression(InterfaceExpression exp) {
        this.exp = exp;
    }

    @Override
    public InterfaceValue evaluate(InterfaceDictionary<String, InterfaceValue> symTable, InterfaceHeap heap) throws ADTException, ExprEvalException {
        InterfaceValue expVal = this.exp.evaluate(symTable, heap);
        if (!(expVal instanceof ReferenceValue))
            throw new ExprEvalException(String.format("%s is not evaluated to a ReferenceValue!", this.exp));

        int addressExp = ((ReferenceValue) expVal).getAddress();
        if (!heap.containsKey(addressExp))
            throw new ExprEvalException(String.format("%s is not a key in Heap table!", addressExp));
        return heap.get(addressExp);
    }

    @Override
    public InterfaceExpression deepCopy() {
        return new HeapReadingExpression(this.exp.deepCopy());
    }

    @Override
    public String toString() {
        return "rH(" + this.exp.toString() + ")";
    }

}
