package Model.Expressions;

import Exceptions.MyException;
import Model.ADT.IDict;
import Model.ADT.IHeap;
import Model.Types.RefType;
import Model.Types.Type;
import Model.Values.RefValue;
import Model.Values.Value;


public class HeapReadExpression implements Expression {
    Expression expression;

    public HeapReadExpression(Expression expression) {
        this.expression = expression;
    }

    @Override
    public Value eval(IDict<String, Value> symTable, IHeap<Integer, Value> heapTable) throws MyException {
        Value val = expression.eval(symTable, heapTable);
        if (val instanceof RefValue) {
            RefValue ref = (RefValue) val;
            int address = ref.getAddress();
            if (heapTable.isDefined(address)) {
                return heapTable.lookup(address);
            } else
                throw new MyException("The address was not found in the heap table!");
        } else
            throw new MyException("The expression is not a reference!");
    }

    @Override
    public Type typeCheck(IDict<String, Type> typeEnv) throws MyException {
        Type typ = expression.typeCheck(typeEnv);
        if (typ instanceof RefType) {
            RefType refType = (RefType) typ;
            return refType.getInner();
        } else
            throw new MyException("The rH argument is not a Ref Type!");
    }

    @Override
    public String toString() {
        return "rH(" + expression + ')';
    }
}
