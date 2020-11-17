package Model.Statements;

import Exceptions.MyException;
import Model.PrgState;

public class NoStatement implements IStatement{
    @Override
    public PrgState execute(PrgState state) throws MyException {
        return state;
    }

    @Override
    public String toString() {
        return "No Statement";
    }
}
