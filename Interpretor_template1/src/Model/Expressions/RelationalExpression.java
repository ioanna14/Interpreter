package Model.Expressions;

import Exceptions.DivisionByZEROException;
import Exceptions.MyException;
import Model.ADT.IDict;
import Model.Types.BoolType;
import Model.Types.IntType;
import Model.Values.BoolValue;
import Model.Values.IntValue;
import Model.Values.Value;

public class RelationalExpression implements Expression{
    Expression e1;
    Expression e2;
    String op;

    public RelationalExpression(Expression e1, Expression e2, String op) {
        this.e1 = e1;
        this.e2 = e2;
        this.op = op;
    }

    @Override
    public Value eval(IDict<String, Value> symTable) throws MyException {
        Value v1, v2;
        v1 = e1.eval(symTable);
        if (v1.getType().equals(new IntType())) {
            v2 = e2.eval(symTable);
            if (v2.getType().equals(new IntType())) {
                IntValue i1 = (IntValue) v1;
                IntValue i2 = (IntValue) v2;
                int n1, n2;
                n1 = i1.getValue();
                n2 = i2.getValue();
                if (op == "<")
                    return new BoolValue(n1 < n2);
                else if (op == "<=")
                    return new BoolValue(n1 <= n2);
                else if (op == "==")
                    return new BoolValue(n1 == n2);
                else if (op == ">=")
                    return new BoolValue(n1 >= n2);
                else if (op == ">")
                    return new BoolValue(n1 > n2);
                else
                    throw new MyException("The operator is wrong!");
            } else
                throw new MyException("Second operand is not an integer!");
        } else
            throw new MyException("First operand is not an integer!");
    }

    @Override
    public String toString() {
        return  e1.toString() + op + e2.toString();
    }
}
