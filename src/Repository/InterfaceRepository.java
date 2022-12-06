package Repository;

import Model.ProgramState.ProgramState;
import Exceptions.ADTException;
import java.io.IOException;

public interface InterfaceRepository {

    ProgramState getCurrentProgramState() throws ADTException;

    void logProgramStateExecution() throws ADTException, IOException;

}
