package Model.Statements;
import Model.PrgState;
import Exceptions.*;

public interface IStatement {
    PrgState execute(PrgState state) throws MyException;
    String toString();
}
