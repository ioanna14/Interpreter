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
    String varName;

    public ReadFileStatement(Expression exp, String varName) {
        this.exp = exp;
        this.varName = varName;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        IDict<String, Value> symTable = state.getSymTable();
        IHeap<Integer, Value> heapTable = state.getHeapTable();
        IDict<StringValue, BufferedReader> fileTable = state.getFileTable();
        if (symTable.isDefined(varName)) {
            if (symTable.lookup(varName).getType() instanceof IntType) {
                try {
                    StringValue stringValue = (StringValue) exp.eval(symTable, heapTable);
                    BufferedReader br = fileTable.lookup(stringValue);
                    String output = br.readLine();
                    if (output != null) {
                        int intVal = Integer.parseInt(output);
                        Value val = new IntValue(intVal);
                        symTable.update(varName, val);
                    } else {
                        Value val = new IntValue(0);
                        symTable.update(varName, val);
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

    @Override
    public String toString() {
        return "readFile(" + exp + ", " + varName + ')';
    }
}
