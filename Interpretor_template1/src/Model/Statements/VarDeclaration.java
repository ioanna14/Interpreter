package Model.Statements;

import Exceptions.MyException;
import Model.ADT.IDict;
import Model.ADT.IStack;
import Model.PrgState;
import Model.Types.Type;
import Model.Values.Value;

public class VarDeclaration implements IStatement {
    String id;
    Type type;

    public VarDeclaration(String id, Type type) {
        this.id = id;
        this.type = type;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        IDict<String, Value> symTbl = state.getSymTable();
        if (symTbl.isDefined(id))
            throw new MyException("Variable " + id + " is already declared!");
        else {
            symTbl.add(id, type.getDefaultValue());
        }
        return state;
    }

    @Override
    public String toString() {
        return  type + " " + id;
    }
}
