package Model.Expressions;

import Model.ADT.IDict;
import Exceptions.*;
import Model.ADT.IHeap;
import Model.Types.IntType;
import Model.Types.Type;
import Model.Values.IntValue;
import Model.Values.Value;

public class ArithExpression implements Expression {
    int op;
    Expression e1, e2;

    public ArithExpression(int op, Expression e1, Expression e2) {
        this.op = op;
        this.e1 = e1;
        this.e2 = e2;
    }

    public Value eval(IDict<String, Value> symTable, IHeap<Integer, Value> heapTable) throws DivisionByZEROException {
        Value v1, v2;
        v1 = e1.eval(symTable, heapTable);
        if (v1.getType().equals(new IntType())) {
            v2 = e2.eval(symTable, heapTable);
            if (v2.getType().equals(new IntType())) {
                IntValue i1 = (IntValue) v1;
                IntValue i2 = (IntValue) v2;
                int n1, n2;
                n1 = i1.getValue();
                n2 = i2.getValue();
                if (op == 1)
                    return new IntValue(n1 + n2);
                else if (op == 2)
                    return new IntValue(n1 - n2);
                else if (op == 3)
                    return new IntValue(n1 * n2);
                else if (op == 4)
                    if (n2 == 0)
                        throw new DivisionByZEROException();
                    else
                        return new IntValue(n1 / n2);
                else
                    throw new MyException("The operator is wrong!");
            } else
                throw new MyException("Second operand is not an integer!");
        } else
            throw new MyException("First operand is not an integer!");
    }

    @Override
    public Type typeCheck(IDict<String, Type> typeEnv) throws MyException {
        Type typ1, typ2;
        typ1 = e1.typeCheck(typeEnv);
        typ2 = e2.typeCheck(typeEnv);
        if (typ1.equals(new IntType())) {
            if (typ2.equals(new IntType())) {
                return new IntType();
            } else throw new MyException("Second operand is not an integer!");
        } else
            throw new MyException("First operand is not an integer!");
    }


    @Override
    public String toString() {
        if (op == 1)
            return e1.toString() + " + " + e2.toString();
        if (op == 2)
            return e1.toString() + " - " + e2.toString();
        if (op == 3)
            return e1.toString() + " * " + e2.toString();
        if (op == 4)
            return e1.toString() + " / " + e2.toString();
        return "";
    }
}
