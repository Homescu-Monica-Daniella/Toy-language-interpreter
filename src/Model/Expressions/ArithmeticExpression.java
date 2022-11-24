package Model.Expressions;

import Model.ProgramState.InterfaceDictionary;
import Model.Values.InterfaceValue;
import Model.Values.IntegerValue;
import Model.Types.IntegerType;
import Exceptions.ADTException;
import Exceptions.ExprEvalException;

public class ArithmeticExpression implements InterfaceExpression {

    char op;
    InterfaceExpression exp1;
    InterfaceExpression exp2;

    public ArithmeticExpression(char op, InterfaceExpression exp1, InterfaceExpression exp2) {
        this.op = op;
        this.exp1 = exp1;
        this.exp2 = exp2;
    }

    @Override
    public InterfaceValue evaluate(InterfaceDictionary<String, InterfaceValue> symTable) throws ADTException, ExprEvalException {
        InterfaceValue val1;
        InterfaceValue val2;
        val1 = this.exp1.evaluate(symTable);
        if (val1.getType().equals(new IntegerType())) {
            val2 = this.exp2.evaluate(symTable);
            if (val2.getType().equals(new IntegerType())) {
                IntegerValue i1 = (IntegerValue) val1;
                IntegerValue i2 = (IntegerValue) val2;
                int n1;
                int n2;
                n1 = i1.getValue();
                n2 = i2.getValue();
                if (this.op == '+')
                    return new IntegerValue(n1 + n2);
                else if (this.op == '-')
                        return new IntegerValue(n1 - n2);
                else if (this.op == '*')
                        return new IntegerValue(n1 * n2);
                else if (this.op == '/')
                        if (n2 == 0)
                            throw new ExprEvalException("Division by zero is not allowed!");
                        else
                            return new IntegerValue(n1 / n2);
            }
            else throw new ExprEvalException("Second operand is not an integer!");
        }
        else throw new ExprEvalException("First operand is not an integer!");
        return null;
    }

    @Override
    public InterfaceExpression deepCopy() {
        return new ArithmeticExpression(this.op, this.exp1.deepCopy(), this.exp2.deepCopy());
    }

    @Override
    public String toString() {
        return this.exp1.toString() + this.op + this.exp2.toString();
    }

}


