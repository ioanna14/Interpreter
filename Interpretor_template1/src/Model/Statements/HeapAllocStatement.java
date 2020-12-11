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

public class HeapAllocStatement implements IStatement {
    String varName;
    Expression expression;

    public HeapAllocStatement(String varName, Expression expression) {
        this.varName = varName;
        this.expression = expression;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        IDict<String, Value> symTable = state.getSymTable();
        IHeap<Integer, Value> heapTable = state.getHeapTable();
        if (symTable.isDefined(varName)) {
            Value variable = symTable.lookup(varName);
            if (variable instanceof RefValue) {
                Value expValue = expression.eval(symTable, heapTable);
                int address = heapTable.add(expValue.getCopy());
                RefValue ref = (RefValue) variable;
                ref.setAddress(address);
                ref.setLocationType(ref.getType());
            } else
                throw new MyException("The variable is not a reference type!");
        } else
            throw new MyException("The variable is not defined!");
        return null;
    }

    @Override
    public IDict<String, Type> typeCheck(IDict<String, Type> typeEnv) throws MyException {
        Type typeVar = typeEnv.lookup(varName);
        Type typeExp = expression.typeCheck(typeEnv);
        if (typeVar.equals(new RefType(typeExp)))
            return typeEnv;
        else
            throw new MyException("NEW Statement: right hand side and left hand side have different types! ");
    }

    @Override
    public String toString() {
        return "new(" + varName + ", " + expression + ')';
    }
}
