package Model.Statements;

import Exceptions.ADTException;
import Exceptions.ExprEvalException;
import Exceptions.StmtExecException;
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
    public ProgramState execute(ProgramState prgState) throws ADTException, ExprEvalException, StmtExecException {
        InterfaceDictionary<String, InterfaceValue> symTable = prgState.getSymTable();
        if (!symTable.isDefined(this.var))
            throw new StmtExecException(String.format("%s is not a variable defined in SymTable!", this.var));

        InterfaceValue varVal = symTable.get(this.var);
        if (!(varVal.getType() instanceof ReferenceType))
            throw new StmtExecException(String.format("Type of %s is not a ReferenceType!", this.var));

        InterfaceHeap heap = prgState.getHeap();
        int addressVar = ((ReferenceValue) varVal).getAddress();
        if (!heap.containsKey(addressVar))
            throw new StmtExecException(String.format("%s is not a key in Heap!", addressVar));

        InterfaceValue expVal = this.exp.evaluate(symTable, heap);
        InterfaceType locationTypeVar = ((ReferenceValue) varVal).getLocationType();
        if (!expVal.getType().equals(locationTypeVar))
            throw new StmtExecException(String.format("Type of %s is not equal to the locationType of %s!", this.exp, this.var));

        heap.update(addressVar, expVal);

        prgState.setHeap(heap);
        return prgState;
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
