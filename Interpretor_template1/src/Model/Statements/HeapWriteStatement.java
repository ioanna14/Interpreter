package Model.Statements;

import Exceptions.MyException;
import Model.ADT.IDict;
import Model.ADT.IHeap;
import Model.Expressions.Expression;
import Model.PrgState;
import Model.Types.RefType;
import Model.Types.Type;
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
            Value variable_value = symTable.lookup(varName);
            if (variable_value instanceof RefValue) {
                RefValue ref = (RefValue) variable_value;
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
    public IDict<String, Type> typeCheck(IDict<String, Type> typeEnv) throws MyException {
        Type typeVar = typeEnv.lookup(varName);
        Type typeExp = expression.typeCheck(typeEnv);
        if (typeVar.equals(new RefType(typeExp)))
            return typeEnv;
        else
            throw new MyException("WH Statement: right hand side and left hand side have different types! ");
    }

    @Override
    public String toString() {
        return "wH(" + varName + ", " + expression + ')';
    }
}
