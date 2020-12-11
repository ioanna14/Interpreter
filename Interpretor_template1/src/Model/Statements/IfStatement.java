package Model.Statements;

import Exceptions.MyException;
import Model.ADT.Dict;
import Model.ADT.IDict;
import Model.ADT.IHeap;
import Model.ADT.IStack;
import Model.Expressions.Expression;
import Model.PrgState;
import Model.Types.BoolType;
import Model.Types.Type;
import Model.Values.BoolValue;
import Model.Values.Value;

public class IfStatement implements IStatement {
    Expression condition;
    IStatement thenBranch;
    IStatement elseBranch;

    public IfStatement(Expression condition, IStatement thenBranch, IStatement elseBranch) {
        this.condition = condition;
        this.thenBranch = thenBranch;
        this.elseBranch = elseBranch;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        IStack<IStatement> stack = state.getStack();
        IDict<String, Value> symTable = state.getSymTable();
        IHeap<Integer, Value> heapTable = state.getHeapTable();
        Value condValue = condition.eval(symTable, heapTable);
        Type condValueType = condValue.getType();
        if (condValueType.equals(new BoolType())) {
            if (((BoolValue) condValue).getValue()) {
                stack.push(thenBranch);
            } else
                stack.push(elseBranch);
        } else
            throw new MyException("Conditional expression is not a boolean!");
        return null;
    }

    @Override
    public IDict<String, Type> typeCheck(IDict<String, Type> typeEnv) throws MyException {
        Type typeExp = condition.typeCheck(typeEnv);
        if (typeExp.equals(new BoolType())) {
            thenBranch.typeCheck(new Dict<>(typeEnv));
            elseBranch.typeCheck(new Dict<>(typeEnv));
            return typeEnv;
        } else
            throw new MyException("The condition of IF has not the type bool");
    }

    @Override
    public String toString() {
        return "if (" + condition +
                ") then (" + thenBranch +
                ") else (" + elseBranch + ")";
    }
}
