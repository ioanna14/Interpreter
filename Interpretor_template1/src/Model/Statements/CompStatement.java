package Model.Statements;

import Exceptions.MyException;
import Model.ADT.IDict;
import Model.ADT.IStack;
import Model.PrgState;
import Model.Types.Type;

public class CompStatement implements IStatement{
    IStatement first;
    IStatement second;

    public CompStatement(IStatement first, IStatement second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        IStack<IStatement> stack = state.getStack();
        stack.push(second);
        stack.push(first);
        return null;
    }

    @Override
    public IDict<String, Type> typeCheck(IDict<String, Type> typeEnv) throws MyException {
        return second.typeCheck(first.typeCheck(typeEnv));
    }

    @Override
    public String toString() {
        return first + "; " + second;
    }
}
