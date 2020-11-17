package Model.Statements;

import Exceptions.MyException;
import Model.ADT.*;
import Model.Expressions.Expression;
import Model.PrgState;
import Model.Types.IntType;
import Model.Values.*;

import java.io.*;

public class ReadFileStatement implements IStatement {
    Expression exp;
    Value varName;

    public ReadFileStatement(Expression exp, Value varName) {
        this.exp = exp;
        this.varName = varName;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        IStack<IStatement> exeStack = state.getStack();
        exeStack.pop();
        IDict<String, Value> symTable = state.getSymTable();
        IDict<StringValue, BufferedReader> fileTable = state.getFileTable();
        // check again, not sure it's ok
        if (symTable.isDefined(varName.toString())) {
            if (varName.getType().equals(new IntType())) {
                try {
                    StringValue stringValue = (StringValue) exp.eval(symTable);
                    BufferedReader br = fileTable.lookup(stringValue);
                    String output = br.readLine();
                    if (output != null) {
                        int intVal = Integer.parseInt(output);
                        Value val = new IntValue(intVal);
                        // check again, not sure it's ok
                        symTable.add(varName.toString(), val);
                    } else {
                        Value val = new IntValue(0);
                        symTable.add(varName.toString(), val);
                    }
                } catch (IOException e) {
                    throw new MyException("Not a string value!");
                }
            } else
                throw new MyException("The expression has not int type.");
        } else
            throw new MyException("The variable is not defined in the table.");
        return state;
    }
}
