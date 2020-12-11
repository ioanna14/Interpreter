package Model.Statements;

import Exceptions.MyException;
import Model.ADT.IDict;
import Model.PrgState;
import Model.Types.Type;

public class NoStatement implements IStatement{
    @Override
    public PrgState execute(PrgState state) throws MyException {
        return null;
    }

    @Override
    public IDict<String, Type> typeCheck(IDict<String, Type> typeEnv) throws MyException {
        return null;
    }

    @Override
    public String toString() {
        return "No Statement";
    }
}
