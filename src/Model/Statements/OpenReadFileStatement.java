package Model.Statements;

import Model.ProgramState.InterfaceDictionary;
import Model.ProgramState.ProgramState;
import Model.Types.StringType;
import Model.Values.InterfaceValue;
import Model.Values.StringValue;
import Model.Expressions.InterfaceExpression;
import Exceptions.ADTException;
import Exceptions.ExprEvalException;
import Exceptions.StmtExecException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class OpenReadFileStatement implements InterfaceStatement {

    InterfaceExpression exp;

    public OpenReadFileStatement(InterfaceExpression exp) {
        this.exp = exp;
    }

    @Override
    public ProgramState execute(ProgramState prgState) throws ADTException, ExprEvalException, StmtExecException {
        InterfaceValue file = this.exp.evaluate(prgState.getSymTable());
        if (!file.getType().equals(new StringType()))
            throw new StmtExecException("Type of evaluated expression should be a string!");

        StringValue fileName = (StringValue) file;
        InterfaceDictionary<String, BufferedReader> fileTable = prgState.getFileTable();
        if (fileTable.isDefined(fileName.getValue()))
            throw new StmtExecException("String value is already a key in the FileTable!");

        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(fileName.getValue()));
        }
        catch (FileNotFoundException ex) {
            throw new StmtExecException("Error opening file!");
        }

        fileTable.put(fileName.getValue(), br);
        prgState.setFileTable(fileTable);

        return prgState;
    }

    @Override
    public InterfaceStatement deepCopy() {
        return new OpenReadFileStatement(this.exp.deepCopy());
    }

    @Override
    public String toString() {
        return "openRFile(" + this.exp.toString() + ")";
    }

}
