package Repository;

import Model.ProgramState.ProgramState;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Repository implements InterfaceRepository {

    List<ProgramState> prgStates;
    String logFilePath;

    public Repository(ProgramState prgState, String logFilePath) {
        this.prgStates = new ArrayList<>();
        this.prgStates.add(prgState);
        this.logFilePath = logFilePath;
    }

    @Override
    public List<ProgramState> getProgramList() {
        return this.prgStates;
    }

    @Override
    public void setProgramList(List<ProgramState> prgLst) {
        this.prgStates = prgLst;
    }

    @Override
    public void logProgramStateExecution(ProgramState prgState) throws IOException {
        PrintWriter logFile;
        logFile = new PrintWriter(new BufferedWriter(new FileWriter(this.logFilePath, true)));
        logFile.println(this.prgStates.toString());
        logFile.close();
    }

}
