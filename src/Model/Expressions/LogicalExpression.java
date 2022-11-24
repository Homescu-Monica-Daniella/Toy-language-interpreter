package Model.Expressions;

import Model.ProgramState.InterfaceDictionary;
import Model.Types.BooleanType;
import Model.Values.InterfaceValue;
import Model.Values.BooleanValue;
import Exceptions.ADTException;
import Exceptions.ExprEvalException;

public class LogicalExpression implements InterfaceExpression {

    String op;
    InterfaceExpression exp1;
    InterfaceExpression exp2;

    public LogicalExpression(String op, InterfaceExpression exp1, InterfaceExpression exp2) {
        this.op = op;
        this.exp1 = exp1;
        this.exp2 = exp2;
    }

    @Override
    public InterfaceValue evaluate(InterfaceDictionary<String, InterfaceValue> symTable) throws ADTException, ExprEvalException {
        InterfaceValue val1;
        InterfaceValue val2;
        val1 = this.exp1.evaluate(symTable);
        if (val1.getType().equals(new BooleanType())) {
            val2 = this.exp2.evaluate(symTable);
            if (val2.getType().equals(new BooleanType())) {
                BooleanValue b1 = (BooleanValue) val1;
                BooleanValue b2 = (BooleanValue) val2;
                boolean n1;
                boolean n2;
                n1 = b1.getValue();
                n2 = b2.getValue();
                if (this.op.equals("and"))
                    return new BooleanValue(n1 && n2);
                else if (this.op.equals("or"))
                        return new BooleanValue(n1 || n2);
            }
            else throw new ExprEvalException("Second operand is not a boolean!");
        }
        else throw new ExprEvalException("First operand is not a boolean!");
        return null;
    }

    @Override
    public InterfaceExpression deepCopy() {
        return new LogicalExpression(this.op, this.exp1.deepCopy(), this.exp2.deepCopy());
    }

    @Override
    public String toString() {
        return this.exp1.toString() + this.op + this.exp2.toString();
    }

}
