package Model.Expressions;

import Model.ProgramState.InterfaceDictionary;
import Model.ProgramState.InterfaceHeap;
import Model.Types.InterfaceType;
import Model.Values.InterfaceValue;
import Exceptions.ADTException;
import Exceptions.ExpressionEvaluationException;

public interface InterfaceExpression {

    InterfaceValue evaluate(InterfaceDictionary<String, InterfaceValue> symTable, InterfaceHeap heap) throws ADTException, ExpressionEvaluationException;

    InterfaceType typeCheck(InterfaceDictionary<String, InterfaceType> typeEnv) throws ADTException, ExpressionEvaluationException;

    InterfaceExpression deepCopy();

}
