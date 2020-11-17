package Model.Statements;

import Exceptions.MyException;
import Model.ADT.IStack;
import Model.PrgState;

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
        return state;
    }

    @Override
    public String toString() {
        return "{" + first + "} {" + second + "}";
    }
}
