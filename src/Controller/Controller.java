package Controller;

import Model.ProgramState.ProgramState;
import Model.Values.InterfaceValue;
import Model.Values.ReferenceValue;
import Repository.InterfaceRepository;
import Exceptions.ADTException;
import Exceptions.ExpressionEvaluationException;
import Exceptions.StatementExecutionException;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Controller {

    InterfaceRepository repo;
    boolean flg;
    ExecutorService executor;

    public Controller(InterfaceRepository repo) {
        this.repo = repo;
    }

    public void setFlg(Boolean flg) {
        this.flg = flg;
    }

    public List<Integer> getAddrFromSymTable(Collection<InterfaceValue> symTableValues) {
        return symTableValues.stream()
                .filter(v->v instanceof ReferenceValue)
                .map(v->{
                    ReferenceValue v1 = (ReferenceValue) v; return v1.getAddress();})
                .collect(Collectors.toList());
    }

    public List<Integer> getAddrFromHeap(Collection<InterfaceValue> heapValues) {
        return heapValues.stream()
                .filter(v->v instanceof ReferenceValue)
                .map(v->{
                    ReferenceValue v1 = (ReferenceValue) v; return v1.getAddress();})
                .collect(Collectors.toList());
    }

    public Map<Integer, InterfaceValue> safeGarbageCollector(List<Integer> symTableAddr, List<Integer> heapAddr, Map<Integer, InterfaceValue> heap) {
        return heap.entrySet().stream()
                .filter(e->(symTableAddr.contains(e.getKey()) || heapAddr.contains(e.getKey())))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public void conservativeGarbageCollector(List<ProgramState> prgList) {
        List<Integer> symTableAddr = Objects.requireNonNull(prgList.stream()
                .map(p->getAddrFromSymTable(p.getSymTable().getContent().values()))
                .map(Collection::stream)
                .reduce(Stream::concat).orElse(null)
                .collect(Collectors.toList()));
        prgList.forEach(p->{p.getHeap().setContent((HashMap<Integer, InterfaceValue>) safeGarbageCollector(
                symTableAddr, getAddrFromHeap(p.getHeap().getContent().values()), p.getHeap().getContent()));
        });
    }

    public List<ProgramState> removeCompletedProgram(List<ProgramState> inPrgList) {
        return inPrgList.stream()
                .filter(p->!p.isNotCompleted())
                .collect(Collectors.toList());
    }

    public void oneStepForAllPrograms(List<ProgramState> prgList) throws InterruptedException {
        prgList.forEach(prg-> {
            try {
                this.repo.logProgramStateExecution(prg);
                display(prg);
          } catch (ADTException | IOException e) {
                System.out.println(e.getMessage());
            }
        });
        List<Callable<ProgramState>> callList = prgList.stream()
                .map((ProgramState p)->(Callable<ProgramState>)(p::oneStep))
                .collect(Collectors.toList());
        List<ProgramState> newPrgList = executor.invokeAll(callList).stream()
                .map(future->{
                    try {
                        return future.get();
                    } catch (InterruptedException | ExecutionException e) {
                        System.out.println(e.getMessage());
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        prgList.addAll(newPrgList);
        prgList.forEach(prg-> {
            try {
                this.repo.logProgramStateExecution(prg);
            } catch (ADTException | IOException e) {
                System.out.println(e.getMessage());
            }
        });
        this.repo.setProgramList(prgList);
    }

    public void allStep() throws ADTException, ExpressionEvaluationException, StatementExecutionException, IOException, InterruptedException {
        this.executor = Executors.newFixedThreadPool(2);
        List<ProgramState> prgList = removeCompletedProgram(this.repo.getProgramList());
        while (prgList.size() > 0) {
            conservativeGarbageCollector(prgList);
            oneStepForAllPrograms(prgList);
            prgList = removeCompletedProgram(this.repo.getProgramList());
        }
        this.executor.shutdownNow();
        this.repo.setProgramList(prgList);
    }

    private void display(ProgramState programState) {
        if (this.flg) {
            System.out.println(programState.toString());
        }
    }

}
