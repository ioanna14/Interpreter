package Model.Statements;

import Exceptions.MyException;
import Model.ADT.*;
import Model.Expressions.*;
import Model.PrgState;
import Model.Types.*;
import Model.Values.*;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseRFileStatement implements IStatement {
    Expression exp;

    public CloseRFileStatement(Expression exp) {
        this.exp = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        IStack<IStatement> exeStack = state.getStack();
        exeStack.pop();
        IDict<String, Value> symTable = state.getSymTable();
        IDict<StringValue, BufferedReader> fileTable = state.getFileTable();
        // check again, not sure it's ok
        if (exp.eval(symTable).getType().equals(new StringType())) {
            StringValue stringValue = (StringValue) exp.eval(symTable);
            if (fileTable.isDefined(stringValue)) {
                BufferedReader br = fileTable.lookup(stringValue);
                try {
                    br.close();
                    fileTable.remove(stringValue, br);
                } catch (IOException e) {
                    throw new MyException("File exception: " + e);
                }
            } else
                throw new MyException("The value is not defined in the file table!");
        } else
            throw new MyException("The expression has not string type.");
        return state;
    }
}
