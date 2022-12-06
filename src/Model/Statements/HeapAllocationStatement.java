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

public class HeapAllocationStatement implements InterfaceStatement {

    String var;
    InterfaceExpression exp;

    public HeapAllocationStatement(String var, InterfaceExpression exp) {
        this.var = var;
        this.exp = exp;
    }

    @Override
    public ProgramState execute(ProgramState prgState) throws ADTException, ExprEvalException, StmtExecException {
        InterfaceDictionary<String, InterfaceValue> symTable = prgState.getSymTable();
        if (!symTable.isDefined(this.var))
            throw new StmtExecException(String.format("%s is not a variable in SymTable!", this.var));

        InterfaceValue varVal = symTable.get(this.var);
        if (!(varVal.getType() instanceof ReferenceType))
            throw new StmtExecException(String.format("Type of %s is not a ReferenceType!", this.var));

        InterfaceHeap heap = prgState.getHeap();
        InterfaceValue expVal = this.exp.evaluate(symTable, heap);
        InterfaceType locationTypeVar = ((ReferenceValue)varVal).getLocationType();
        if (!expVal.getType().equals(locationTypeVar))
            throw new StmtExecException(String.format("Type of %s is not equal to the locationType from the value associated to %s in SymTable!", this.exp, this.var));

        //////////////////////////////////////////////////////////////
        int newPos = heap.add(expVal);
        symTable.put(this.var, new ReferenceValue(newPos, locationTypeVar));
        prgState.setSymTable(symTable);
        prgState.setHeap(heap);
        return prgState;
    }

    @Override
    public InterfaceStatement deepCopy() {
        return new HeapAllocationStatement(this.var, this.exp.deepCopy());
    }

    @Override
    public String toString() {
        return "new(" + this.var + ", " + this.exp.toString() + ")";
    }

}
