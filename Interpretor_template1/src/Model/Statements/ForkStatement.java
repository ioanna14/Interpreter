package Model.Statements;

import Exceptions.MyException;;
import Model.ADT.Dict;
import Model.ADT.IDict;
import Model.ADT.IStack;
import Model.ADT.MyStack;
import Model.PrgState;
import Model.Types.Type;
import Model.Values.Value;

public class ForkStatement implements IStatement {
    IStatement statement;

    public ForkStatement(IStatement statement) {
        this.statement = statement;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        IStack<IStatement> newExeStack = new MyStack<>();
        newExeStack.push(this.statement);

        return new PrgState(
                newExeStack,
                new Dict<String, Value>(state.getSymTable()),
                state.getFileTable(),
                state.getHeapTable(),
                state.getOut(),
                statement
        );
    }

    @Override
    public IDict<String, Type> typeCheck(IDict<String, Type> typeEnv) throws MyException {
        return statement.typeCheck(typeEnv);
    }

    @Override
    public String toString() {
        return "fork(" + statement + ')';
    }
}
