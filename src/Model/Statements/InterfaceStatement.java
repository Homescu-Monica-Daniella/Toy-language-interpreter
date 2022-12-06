package Model.Statements;

import Model.ProgramState.ProgramState;
import Exceptions.ADTException;
import Exceptions.ExprEvalException;
import Exceptions.StmtExecException;

public interface InterfaceStatement {

    ProgramState execute(ProgramState prgState) throws ADTException, ExprEvalException, StmtExecException;

    InterfaceStatement deepCopy();

}
