package Model.Statements;
import Model.ADT.IDict;
import Model.PrgState;
import Exceptions.*;
import Model.Types.Type;

public interface IStatement {
    PrgState execute(PrgState state) throws MyException;
    IDict<String,Type> typeCheck(IDict<String, Type> typeEnv) throws MyException;
    String toString();
}
