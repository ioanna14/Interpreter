package Model.Statements;

import Exceptions.MyException;
import Model.ADT.IDict;
import Model.ADT.IHeap;
import Model.ADT.IList;
import Model.Expressions.Expression;
import Model.PrgState;
import Model.Types.Type;
import Model.Values.Value;

public class PrintStatement implements IStatement {
    Expression expression;

    public PrintStatement(Expression expression) {
        this.expression = expression;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        IList<Value> out = state.getOut();
        IHeap<Integer, Value> heapTable = state.getHeapTable();
        out.add(expression.eval(state.getSymTable(), heapTable));
        return null;
    }

    @Override
    public IDict<String, Type> typeCheck(IDict<String, Type> typeEnv) throws MyException {
        expression.typeCheck(typeEnv);
        return typeEnv;
    }

    @Override
    public String toString() {
        return "print(" + expression.toString() + ")";
    }
}
