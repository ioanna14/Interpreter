package Model.Statements;

import Exceptions.MyException;
import Model.ADT.IList;
import Model.Expressions.Expression;
import Model.PrgState;
import Model.Values.Value;

public class PrintStatement implements IStatement{
    Expression expression;

    public PrintStatement(Expression expression) {
        this.expression = expression;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        IList<Value> out = state.getOut();
        out.add(expression.eval(state.getSymTable()));
        return state;
    }

    @Override
    public String toString() {
        return "print(" + expression.toString() + ")";
    }
}
