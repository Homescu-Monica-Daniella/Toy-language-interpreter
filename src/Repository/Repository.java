package Repository;

import Model.ProgramState.ProgramState;
import Model.ProgramState.InterfaceList;
import Model.ProgramState.List;
import Exceptions.ADTException;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;

public class Repository implements InterfaceRepository {

    InterfaceList<ProgramState> prgStates;
    String logFilePath;

    public Repository(ProgramState prgState, String logFilePath) {
        this.prgStates = new List<>();
        this.prgStates.add(prgState);
        this.logFilePath = logFilePath;
    }

    @Override
    public ProgramState getCurrentProgramState() throws ADTException {
        return this.prgStates.getLast();
    }

    @Override
    public void logProgramStateExecution() throws ADTException, IOException {
        PrintWriter logFile;
        logFile = new PrintWriter(new BufferedWriter(new FileWriter(this.logFilePath, true)));
        logFile.println(this.prgStates.getFirst().toString());
        logFile.close();
    }

}
