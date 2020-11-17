package Model.Statements;

import Exceptions.MyException;
import Model.ADT.*;
import Model.Expressions.Expression;
import Model.PrgState;
import Model.Types.*;
import Model.Values.*;

import java.io.*;

public class OpenRFileStatement implements IStatement {
    Expression exp;

    public OpenRFileStatement(Expression exp) {
        this.exp = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        IStack<IStatement> exeStack = state.getStack();
        exeStack.pop();
        IDict<String, Value> symTable = state.getSymTable();
        IDict<StringValue, BufferedReader> fileTable = state.getFileTable();
        Value val = exp.eval(symTable);
        if (val.getType().equals(new StringType())) {
            StringValue strVal = (StringValue) val;
            if (!fileTable.isDefined(strVal)) {
                try {
                    File file = new File(strVal.getValue());
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    fileTable.add(strVal, br);
                } catch (IOException e) {
                    throw new MyException("File exception: " + e);
                }
            } else
                throw new MyException("Duplicate key!");
        } else
            throw new MyException("The expression has not string type.");

        return state;
    }
}
