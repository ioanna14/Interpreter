package Model.Statements;

import Exceptions.MyException;
import Model.ADT.IDict;
import Model.ADT.IHeap;
import Model.Expressions.Expression;
import Model.PrgState;
import Model.Types.RefType;
import Model.Values.IntValue;
import Model.Values.RefValue;
import Model.Values.Value;

public class HeapWriteStatement implements IStatement{
    String varName;
    Expression expression;

    public HeapWriteStatement(String varName, Expression expression) {
        this.varName = varName;
        this.expression = expression;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        IDict<String, Value> symTable = state.getSymTable();
        IHeap<Integer, Value> heapTable = state.getHeapTable();
        if (symTable.isDefined(varName)){
            Value variable = symTable.lookup(varName);
            if (variable.getType() instanceof RefType) {
                RefValue ref = (RefValue) variable;
                if (heapTable.isDefined(ref.getAddress())) {
                    Value expValue = expression.eval(symTable, heapTable);
                    if (expValue.getType().equals(ref.getLocationType())) {
                        heapTable.update(ref.getAddress(), expValue);
                    } else
                        throw new MyException("The expression's type is different than the location type!");
                } else
                    throw new MyException("Address is not on the heap table!");
            } else
                throw new MyException("The variable is not a reference type!");
        } else
            throw new MyException("The variable is not defined!");
        return state;
    }

    @Override
    public String toString() {
        return "wH(" + varName + ", " + expression + ')';
    }
}
