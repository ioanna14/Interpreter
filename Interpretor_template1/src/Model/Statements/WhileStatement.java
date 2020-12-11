package Model.Statements;

import Exceptions.MyException;
import Model.ADT.IDict;
import Model.Expressions.Expression;
import Model.PrgState;
import Model.Types.BoolType;
import Model.Types.Type;
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
        return null;
    }

    @Override
    public IDict<String, Type> typeCheck(IDict<String, Type> typeEnv) throws MyException {
        if (!condition.typeCheck(typeEnv).equals(new BoolType())) {
            throw new MyException("While Statement: condition not of type boolean!");
        }
        return content.typeCheck(typeEnv);
    }

    @Override
    public String toString() {
        return "while ( " + condition + " ) do (" + content + " )";
    }
}
