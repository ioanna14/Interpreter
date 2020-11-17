package Model.Statements;

import Exceptions.MyException;
import Model.ADT.IDict;
import Model.ADT.IStack;
import Model.Expressions.Expression;
import Model.PrgState;
import Model.Types.Type;
import Model.Values.Value;

public class AssignStatement implements IStatement {
    String id;
    Expression expression;

    public AssignStatement(String id, Expression expression) {
        this.id = id;
        this.expression = expression;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        IDict<String, Value> symTbl = state.getSymTable();
        if (symTbl.isDefined(id)) {
            Value val = expression.eval(symTbl);
            Type typId = (symTbl.lookup(id)).getType();
            if (val.getType().equals(typId))
                symTbl.update(id, val);
            else
                throw new MyException("declared type of variable" + id + " " +
                        "and type of  the assigned expression do not match");
        } else
            throw new MyException("the used variable " + id + " was not declared before");
        return state;
    }

    @Override
    public String toString() {
        return  id + " = "+ expression.toString();
    }
}
