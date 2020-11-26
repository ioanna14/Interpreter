package Model.Expressions;

import Exceptions.MyException;
import Model.ADT.IDict;
import Model.ADT.IHeap;
import Model.Values.RefValue;
import Model.Values.Value;


public class HeapReadExpression implements Expression{
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
            if(heapTable.isDefined(address)) {
                return heapTable.lookup(address);
            } else
                throw new MyException("The address was not found in the heap table!");
        } else
            throw new MyException("The expression is not a reference!");
    }

    @Override
    public String toString() {
        return "rH(" + expression + ')';
    }
}
