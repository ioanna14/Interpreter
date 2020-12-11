package Model.Expressions;

import Exceptions.DivisionByZEROException;
import Exceptions.MyException;
import Model.ADT.IDict;
import Model.ADT.IHeap;
import Model.Types.BoolType;
import Model.Types.IntType;
import Model.Types.Type;
import Model.Values.BoolValue;
import Model.Values.Value;

public class LogicExpression implements Expression{
    Expression e1;
    Expression e2;
    int op;

    public LogicExpression(Expression e1, Expression e2, int operator) {
        this.e1 = e1;
        this.e2 = e2;
        this.op = operator;
    }

    @Override
    public Value eval(IDict<String, Value> symTable, IHeap<Integer, Value> heapTable) throws DivisionByZEROException {
        Value v1, v2;
        v1 = e1.eval(symTable, heapTable);
        if (v1.getType().equals(new BoolType())) {
            v2 = e2.eval(symTable, heapTable);
            if (v2.getType().equals(new BoolType())) {
                BoolValue i1 = (BoolValue) v1;
                BoolValue i2 = (BoolValue) v2;
                boolean n1, n2;
                n1 = i1.getValue();
                n2 = i2.getValue();
                if (op == 1)
                    return new BoolValue(n1 && n2);
                else if (op == 2)
                    return new BoolValue(n1 || n2);
                else
                    throw new MyException("The operator is wrong!");
            } else
                throw new MyException("Second operand is not a boolean!");
        } else
            throw new MyException("First operand is not a boolean!");
    }

    @Override
    public Type typeCheck(IDict<String, Type> typeEnv) throws MyException {
        Type typ1, typ2;
        typ1 = e1.typeCheck(typeEnv);
        typ2 = e2.typeCheck(typeEnv);
        if (typ1.equals(new BoolType())) {
            if (typ2.equals(new BoolType())) {
                return new BoolType();
            } else throw new MyException("Second operand is not a boolean!");
        } else
            throw new MyException("First operand is not a boolean!");
    }

    @Override
    public String toString() {
        if (op == 1)
            return e1.toString() + " && " + e2.toString();
        if (op == 2)
            return e1.toString() + " || " + e2.toString();
        return "";
    }
}
