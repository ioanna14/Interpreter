package Model.Expressions;
import Exceptions.MyException;
import Model.ADT.IDict;
import Model.ADT.IHeap;
import Model.Types.Type;
import Model.Values.Value;

public interface Expression {
    Value eval(IDict<String, Value> symTable, IHeap<Integer, Value> heapTable) throws MyException;
    Type typeCheck(IDict<String, Type> typeEnv) throws MyException;
    String toString();
}
