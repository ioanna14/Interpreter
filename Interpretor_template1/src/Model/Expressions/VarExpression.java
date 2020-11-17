package Model.Expressions;
import Model.ADT.IDict;
import Exceptions.*;
import Model.Values.Value;

public class VarExpression implements Expression {
    String id;

    public VarExpression(String id) {
        this.id = id;
    }

    public Value eval(IDict<String, Value> symTable) throws MyException{
        if (!symTable.isDefined(id))
            throw new MyException("Variable x is not defined!");
        return symTable.lookup(id);
    }

    @Override
    public String toString() {
        return "" + id;
    }
}
