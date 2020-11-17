package Controller;

import Exceptions.MyException;
import Model.ADT.IStack;
import Model.PrgState;
import Model.Statements.IStatement;
import Repository.*;

import java.io.IOException;

public class Controller {
    IRepository repository;

    public Controller(IRepository repository) {
        this.repository = repository;
    }

    public void addProgram(PrgState newPrg) {
        this.repository.addPrg(newPrg);
    }

    public void oneStep(PrgState state) {
        IStack<IStatement> stack = state.getStack();
        if (stack.isEmpty())
            throw new MyException("PrgState stack is empty");
        IStatement crtStatement = stack.pop();
        crtStatement.execute(state);
    }

    public void allSteps() throws MyException, IOException {
        PrgState prg = repository.getCrtPrg();
        repository.logPrgStateExec(prg);
        while (!prg.getStack().isEmpty()) {
            oneStep(prg);
            repository.logPrgStateExec(prg);
        }
    }
}
