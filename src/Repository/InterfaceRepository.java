package Repository;

import Model.ProgramState.ProgramState;
import Exceptions.ADTException;
import java.io.IOException;
import java.util.List;

public interface InterfaceRepository {

    List<ProgramState> getProgramList();

    void setProgramList(List<ProgramState> prgLst);

    void logProgramStateExecution(ProgramState prgState) throws ADTException, IOException;

}
