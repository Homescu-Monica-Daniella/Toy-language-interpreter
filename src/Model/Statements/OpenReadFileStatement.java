package Model.Statements;

import Model.ProgramState.InterfaceDictionary;
import Model.ProgramState.ProgramState;
import Model.Types.InterfaceType;
import Model.Types.StringType;
import Model.Values.InterfaceValue;
import Model.Values.StringValue;
import Model.Expressions.InterfaceExpression;
import Exceptions.ADTException;
import Exceptions.ExpressionEvaluationException;
import Exceptions.StatementExecutionException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class OpenReadFileStatement implements InterfaceStatement {

    InterfaceExpression exp;

    public OpenReadFileStatement(InterfaceExpression exp) {
        this.exp = exp;
    }

    @Override
    public ProgramState execute(ProgramState prgState) throws ADTException, ExpressionEvaluationException, StatementExecutionException {
        InterfaceValue file = this.exp.evaluate(prgState.getSymTable(), prgState.getHeap());
        if (!file.getType().equals(new StringType()))
            throw new StatementExecutionException("Type of evaluated expression should be a string!");

        StringValue fileName = (StringValue) file;
        InterfaceDictionary<String, BufferedReader> fileTable = prgState.getFileTable();
        if (fileTable.isDefined(fileName.getValue()))
            throw new StatementExecutionException("String value is already a key in the FileTable!");

        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(fileName.getValue()));
        }
        catch (FileNotFoundException ex) {
            throw new StatementExecutionException("Error opening file!");
        }

        fileTable.put(fileName.getValue(), br);
        prgState.setFileTable(fileTable);

        return null;
    }

    @Override
    public InterfaceDictionary<String, InterfaceType> typeCheck(InterfaceDictionary<String, InterfaceType> typeEnv) throws ADTException, ExpressionEvaluationException, StatementExecutionException {
        InterfaceType typeExp = this.exp.typeCheck(typeEnv);
        if (typeExp.equals(new StringType()))
            return typeEnv;
        else
            throw new StatementExecutionException("Expression is not a string!");
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
