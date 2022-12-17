package Model.Expressions;

import Exceptions.ADTException;
import Exceptions.ExpressionEvaluationException;
import Model.ProgramState.InterfaceDictionary;
import Model.ProgramState.InterfaceHeap;
import Model.Types.InterfaceType;
import Model.Types.ReferenceType;
import Model.Values.InterfaceValue;
import Model.Values.ReferenceValue;

public class HeapReadingExpression implements InterfaceExpression {

    InterfaceExpression exp;

    public HeapReadingExpression(InterfaceExpression exp) {
        this.exp = exp;
    }

    @Override
    public InterfaceValue evaluate(InterfaceDictionary<String, InterfaceValue> symTable, InterfaceHeap heap) throws ADTException, ExpressionEvaluationException {
        InterfaceValue expVal = this.exp.evaluate(symTable, heap);
        if (!(expVal instanceof ReferenceValue))
            throw new ExpressionEvaluationException(String.format("%s is not evaluated to a ReferenceValue!", this.exp));

        int addressExp = ((ReferenceValue) expVal).getAddress();
        if (!heap.containsKey(addressExp))
            throw new ExpressionEvaluationException(String.format("%s is not a key in Heap table!", addressExp));
        return heap.get(addressExp);
    }

    @Override
    public InterfaceType typeCheck(InterfaceDictionary<String, InterfaceType> typeEnv) throws ADTException, ExpressionEvaluationException {
        InterfaceType typ = this.exp.typeCheck(typeEnv);
        if (typ instanceof ReferenceType) {
            ReferenceType refT = (ReferenceType) typ;
            return refT.getInner();
        }
        else
            throw new ExpressionEvaluationException("The rh argument is not a ReferenceType!");
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
