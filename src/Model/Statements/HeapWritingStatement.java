package Model.Statements;

import Exceptions.ADTException;
import Exceptions.ExpressionEvaluationException;
import Exceptions.StatementExecutionException;
import Model.Expressions.InterfaceExpression;
import Model.ProgramState.InterfaceDictionary;
import Model.ProgramState.InterfaceHeap;
import Model.ProgramState.ProgramState;
import Model.Types.InterfaceType;
import Model.Types.ReferenceType;
import Model.Values.InterfaceValue;
import Model.Values.ReferenceValue;

public class HeapWritingStatement implements InterfaceStatement {

    String var;
    InterfaceExpression exp;

    public HeapWritingStatement(String var, InterfaceExpression exp) {
        this.var = var;
        this.exp = exp;
    }

    @Override
    public ProgramState execute(ProgramState prgState) throws ADTException, ExpressionEvaluationException, StatementExecutionException {
        InterfaceDictionary<String, InterfaceValue> symTable = prgState.getSymTable();
        if (!symTable.isDefined(this.var))
            throw new StatementExecutionException(String.format("%s is not a variable defined in SymTable!", this.var));

        InterfaceValue varVal = symTable.get(this.var);
        if (!(varVal.getType() instanceof ReferenceType))
            throw new StatementExecutionException(String.format("Type of %s is not a ReferenceType!", this.var));

        InterfaceHeap heap = prgState.getHeap();
        int addressVar = ((ReferenceValue) varVal).getAddress();
        if (!heap.containsKey(addressVar))
            throw new StatementExecutionException(String.format("%s is not a key in Heap!", addressVar));

        InterfaceValue expVal = this.exp.evaluate(symTable, heap);
        InterfaceType locationTypeVar = ((ReferenceValue) varVal).getLocationType();
        if (!expVal.getType().equals(locationTypeVar))
            throw new StatementExecutionException(String.format("Type of %s is not equal to the locationType of %s!", this.exp, this.var));

        heap.update(addressVar, expVal);

        prgState.setHeap(heap);
        return null;
    }

    @Override
    public InterfaceDictionary<String, InterfaceType> typeCheck(InterfaceDictionary<String, InterfaceType> typeEnv) throws ADTException, ExpressionEvaluationException, StatementExecutionException {
        InterfaceType typeVar = typeEnv.get(this.var);
        InterfaceType typeExp = this.exp.typeCheck(typeEnv);
        if (typeVar.equals(new ReferenceType(typeExp)))
            return typeEnv;
        else
            throw new StatementExecutionException("HEAP WRITING: right hand side and left hand side have different types!");

    }

    @Override
    public InterfaceStatement deepCopy() {
        return new HeapWritingStatement(this.var, this.exp.deepCopy());
    }

    @Override
    public String toString() {
        return "wH(" + this.var + ", " + this.exp.toString() + ")";
    }

}
