package Model.Expressions;
import Exceptions.MyException;
import Model.ADT.IDict;
import Model.ADT.IHeap;
import Model.Values.Value;

public interface Expression {
    Value eval(IDict<String, Value> symTable, IHeap<Integer, Value> heapTable) throws MyException;
    String toString();
}
