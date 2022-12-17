package Model.Statements;

import Model.ProgramState.InterfaceDictionary;
import Model.ProgramState.ProgramState;
import Exceptions.ADTException;
import Exceptions.ExpressionEvaluationException;
import Exceptions.StatementExecutionException;
import Model.Types.InterfaceType;

public interface InterfaceStatement {

    ProgramState execute(ProgramState prgState) throws ADTException, ExpressionEvaluationException, StatementExecutionException;

    InterfaceDictionary<String, InterfaceType> typeCheck(InterfaceDictionary<String, InterfaceType> typeEnv) throws ADTException, ExpressionEvaluationException, StatementExecutionException;

    InterfaceStatement deepCopy();

}
