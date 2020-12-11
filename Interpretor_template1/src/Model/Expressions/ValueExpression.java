package Model.Expressions;
import Exceptions.MyException;
import Model.ADT.IDict;
import Model.ADT.IHeap;
import Model.Types.Type;
import Model.Values.Value;

public class ValueExpression implements Expression {
    Value value;

    public ValueExpression(Value value) {
        this.value = value;
    }

    @Override
    public Value eval(IDict<String, Value> symTable, IHeap<Integer, Value> heapTable) throws MyException {
        return value;
    }

    @Override
    public Type typeCheck(IDict<String, Type> typeEnv) throws MyException {
        return value.getType();
    }

    @Override
    public String toString() {
        return "" + value;
    }
}
