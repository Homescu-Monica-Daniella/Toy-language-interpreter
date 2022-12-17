package Model.Statements;

import Exceptions.ADTException;
import Exceptions.ExpressionEvaluationException;
import Exceptions.StatementExecutionException;
import Model.Expressions.InterfaceExpression;
import Model.ProgramState.InterfaceDictionary;
import Model.ProgramState.ProgramState;
import Model.Types.InterfaceType;
import Model.Types.StringType;
import Model.Values.InterfaceValue;
import Model.Values.StringValue;
import java.io.BufferedReader;
import java.io.IOException;

public class CloseReadFileStatement implements InterfaceStatement {

    InterfaceExpression exp;

    public CloseReadFileStatement(InterfaceExpression exp) {
        this.exp = exp;
    }

    @Override
    public ProgramState execute(ProgramState prgState) throws ADTException, ExpressionEvaluationException, StatementExecutionException {
        InterfaceValue file = this.exp.evaluate(prgState.getSymTable(), prgState.getHeap());
        if (!file.getType().equals(new StringType()))
            throw new StatementExecutionException("Type of evaluated expression should be a string!");

        StringValue fileName = (StringValue) file;
        InterfaceDictionary<String, BufferedReader> fileTable = prgState.getFileTable();
        if (!fileTable.isDefined(fileName.getValue()))
            throw new StatementExecutionException("String does not have an entry associated in the FileTable!");

        BufferedReader br = fileTable.get(fileName.getValue());
        try {
            br.close();
        }
        catch (IOException ex) {
            throw new StatementExecutionException("Error closing file!");
        }

        fileTable.remove(fileName.getValue());
        prgState.setFileTable(fileTable);

        return null;
    }

    @Override
    public InterfaceDictionary<String, InterfaceType> typeCheck(InterfaceDictionary<String, InterfaceType> typeEnv) throws ADTException, ExpressionEvaluationException, StatementExecutionException {
        if (this.exp.typeCheck(typeEnv).equals(new StringType()))
            return typeEnv;
        else
            throw new StatementExecutionException("CloseReadFile requires a string expression!");
    }

    @Override
    public InterfaceStatement deepCopy() {
        return new CloseReadFileStatement(this.exp.deepCopy());
    }

    @Override
    public String toString() {
        return "closeRFile(" + this.exp.toString() + ")";
    }

}
