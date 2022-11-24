package Controller;

import Model.ProgramState.ProgramState;
import Model.ProgramState.InterfaceStack;
import Model.Statements.InterfaceStatement;
import Repository.InterfaceRepository;
import Exceptions.ADTException;
import Exceptions.ExprEvalException;
import Exceptions.StmtExecException;
import java.io.IOException;

public class Controller {

    InterfaceRepository repo;
    boolean flg;

    public Controller(InterfaceRepository repo) {
        this.repo = repo;
    }

    public void setFlg(Boolean flg) {
        this.flg = flg;
    }

    public void oneStep(ProgramState prgState) throws ADTException, ExprEvalException, StmtExecException {
        InterfaceStack<InterfaceStatement> exeStack = prgState.getExeStack();
        if (exeStack.isEmpty())
            throw new StmtExecException("Execution stack is empty!");
        InterfaceStatement currentStmt = exeStack.pop();
        prgState.setExeStack(exeStack);
        currentStmt.execute(prgState);
    }

    public void allStep() throws ADTException, ExprEvalException, StmtExecException, IOException {
        ProgramState prgState = this.repo.getCurrentProgramState();
        //displayStep();
        this.repo.logProgramStateExecution();
        while(!prgState.getExeStack().isEmpty()) {
            oneStep(prgState);
            //displayStep();
            this.repo.logProgramStateExecution();
        }
    }

    public void displayStep() throws ADTException {
        if (this.flg)
            System.out.println(this.repo.getCurrentProgramState().toString());
    }

}
