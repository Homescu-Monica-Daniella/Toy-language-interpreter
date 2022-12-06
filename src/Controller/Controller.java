package Controller;

import Model.ProgramState.ProgramState;
import Model.ProgramState.InterfaceStack;
import Model.Statements.InterfaceStatement;
import Model.Values.InterfaceValue;
import Model.Values.ReferenceValue;
import Repository.InterfaceRepository;
import Exceptions.ADTException;
import Exceptions.ExprEvalException;
import Exceptions.StmtExecException;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public List<Integer> getAddrFromSymTable(Collection<InterfaceValue> symTableValues) {
        return symTableValues.stream()
                .filter(v->v instanceof ReferenceValue)
                .map(v->{ReferenceValue v1 = (ReferenceValue) v; return v1.getAddress();})
                .collect(Collectors.toList());
    }

    public List<Integer> getAddrFromHeap(Collection<InterfaceValue> heapValues) {
        return heapValues.stream()
                .filter(v->v instanceof ReferenceValue)
                .map(v->{ReferenceValue v1 = (ReferenceValue) v; return v1.getAddress();})
                .collect(Collectors.toList());
    }

    public Map<Integer, InterfaceValue> safeGarbageCollector(List<Integer> symTableAddr, List<Integer> heapAddr, Map<Integer, InterfaceValue> heap) {
        return heap.entrySet().stream()
                .filter(e->(symTableAddr.contains(e.getKey()) || heapAddr.contains(e.getKey())))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public void allStep() throws ADTException, ExprEvalException, StmtExecException, IOException {
        ProgramState prgState = this.repo.getCurrentProgramState();
        //displayStep();
        this.repo.logProgramStateExecution();
        while(!prgState.getExeStack().isEmpty()) {
            oneStep(prgState);
            //displayStep();
            this.repo.logProgramStateExecution();
            prgState.getHeap().setContent((HashMap<Integer, InterfaceValue>) safeGarbageCollector(getAddrFromSymTable(prgState.getSymTable().getContent().values()),
                    getAddrFromHeap(prgState.getHeap().getContent().values()),
                    prgState.getHeap().getContent()));
        }
    }

    public void displayStep() throws ADTException {
        if (this.flg)
            System.out.println(this.repo.getCurrentProgramState().toString());
    }

}
