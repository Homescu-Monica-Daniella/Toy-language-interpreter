package Model.Statements;

import Exceptions.ADTException;
import Exceptions.ExprEvalException;
import Exceptions.StmtExecException;
import Model.Expressions.InterfaceExpression;
import Model.ProgramState.InterfaceDictionary;
import Model.ProgramState.ProgramState;
import Model.Types.IntegerType;
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
    public ProgramState execute(ProgramState prgState) throws ADTException, ExprEvalException, StmtExecException {
        InterfaceDictionary<String, InterfaceValue> symTable = prgState.getSymTable();
        if (!symTable.isDefined(this.var))
            throw new StmtExecException("Variable name is not defined in SymTable!");
        if (!symTable.get(this.var).getType().equals(new IntegerType()))
            throw new StmtExecException("Variable should be an integer!");

        InterfaceValue val = this.exp.evaluate(symTable, prgState.getHeap());
        if(!val.getType().equals(new StringType()))
            throw new StmtExecException("Type of evaluated expression should be a string!");

        StringValue strVal = (StringValue) val;
        InterfaceDictionary<String, BufferedReader> fileTable = prgState.getFileTable();
        if (!fileTable.isDefined(strVal.getValue()))
            throw new StmtExecException("String does not have an entry associated in the FileTable!");

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
            throw new StmtExecException("Error reading from file!");
        }

        symTable.put(this.var, v);
        prgState.setSymTable(symTable);

        return prgState;
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
