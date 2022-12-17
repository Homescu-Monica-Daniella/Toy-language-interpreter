package Model.Statements;

import Exceptions.ADTException;
import Exceptions.ExpressionEvaluationException;
import Exceptions.StatementExecutionException;
import Model.Expressions.InterfaceExpression;
import Model.ProgramState.InterfaceDictionary;
import Model.ProgramState.ProgramState;
import Model.Types.IntegerType;
import Model.Types.InterfaceType;
import Model.Types.StringType;
import Model.Values.IntegerValue;
import Model.Values.InterfaceValue;
import Model.Values.StringValue;
import java.io.BufferedReader;
import java.io.IOException;

public class ReadFileStatement implements InterfaceStatement {

    InterfaceExpression exp;
    String var;

    public ReadFileStatement(InterfaceExpression exp, String var) {
        this.exp = exp;
        this.var = var;
    }

    @Override
    public ProgramState execute(ProgramState prgState) throws ADTException, ExpressionEvaluationException, StatementExecutionException {
        InterfaceDictionary<String, InterfaceValue> symTable = prgState.getSymTable();
        if (!symTable.isDefined(this.var))
            throw new StatementExecutionException("Variable name is not defined in SymTable!");
        if (!symTable.get(this.var).getType().equals(new IntegerType()))
            throw new StatementExecutionException("Variable should be an integer!");

        InterfaceValue val = this.exp.evaluate(symTable, prgState.getHeap());
        if(!val.getType().equals(new StringType()))
            throw new StatementExecutionException("Type of evaluated expression should be a string!");

        StringValue strVal = (StringValue) val;
        InterfaceDictionary<String, BufferedReader> fileTable = prgState.getFileTable();
        if (!fileTable.isDefined(strVal.getValue()))
            throw new StatementExecutionException("String does not have an entry associated in the FileTable!");

        BufferedReader br = fileTable.get(strVal.getValue());
        IntegerValue v;
        try {
            String line = br.readLine();
            if (line == null)
                v = new IntegerValue(0);
            else
                v = new IntegerValue(Integer.parseInt(line));
        }
        catch (IOException ex) {
            throw new StatementExecutionException("Error reading from file!");
        }

        symTable.put(this.var, v);
        prgState.setSymTable(symTable);

        return null;
    }

    @Override
    public InterfaceDictionary<String, InterfaceType> typeCheck(InterfaceDictionary<String, InterfaceType> typeEnv) throws ADTException, ExpressionEvaluationException, StatementExecutionException {
        InterfaceType typeExp = this.exp.typeCheck(typeEnv);
        InterfaceType typeVar = typeEnv.get(this.var);
        if (typeExp.equals(new StringType())) {
            if (typeVar.equals(new IntegerType()))
                return typeEnv;
            else
                throw new StatementExecutionException("Variable is not an integer!");
        }
        else
            throw new StatementExecutionException("Expression is not a string!");
    }

    @Override
    public InterfaceStatement deepCopy() {
        return new ReadFileStatement(this.exp.deepCopy(), this.var);
    }

    @Override
    public String toString() {
        return "readFile(" + this.exp.toString() + "," + this.var + ")";
    }

}
