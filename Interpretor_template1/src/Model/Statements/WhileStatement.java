package Model.Statements;

import Exceptions.MyException;
import Model.Expressions.Expression;
import Model.PrgState;
import Model.Types.BoolType;
import Model.Values.BoolValue;
import Model.Values.Value;

public class WhileStatement implements IStatement{
    Expression condition;
    IStatement content;

    public WhileStatement(Expression condition, IStatement content) {
        this.condition = condition;
        this.content = content;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        Value conditionResult = condition.eval(state.getSymTable(), state.getHeapTable());
        if (conditionResult.getType().equals(new BoolType())) {
            if (((BoolValue)conditionResult).getValue()) {
                state.getStack().push(this);
                state.getStack().push(content);
            }
        } else
            throw new MyException("While condition has to be a " + new BoolType() + " !");
        return state;
    }

    @Override
    public String toString() {
        return "while ( " + condition + " ) do (" + content + " )";
    }
}
