package Model.Expressions;
import Exceptions.MyException;
import Model.ADT.IDict;
import Model.Values.Value;

public interface Expression {
    Value eval(IDict<String, Value> symTable) throws MyException;
    String toString();
}
