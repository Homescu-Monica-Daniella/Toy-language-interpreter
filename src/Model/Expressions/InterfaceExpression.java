package Model.Expressions;

import Model.ProgramState.InterfaceDictionary;
import Model.Values.InterfaceValue;
import Exceptions.ADTException;
import Exceptions.ExprEvalException;

public interface InterfaceExpression {

    InterfaceValue evaluate(InterfaceDictionary<String, InterfaceValue> symTable) throws ADTException, ExprEvalException;

    InterfaceExpression deepCopy();

}
