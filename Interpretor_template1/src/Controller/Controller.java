package Controller;

import Exceptions.MyException;
import Model.ADT.IStack;
import Model.PrgState;
import Model.Statements.IStatement;
import Model.Values.RefValue;
import Model.Values.Value;
import Repository.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    private Map<Integer, Value> unsafeGarbageCollector(List<Integer> symTableAddr, List<Integer> heapAddr, Map<Integer, Value> heap) {
        return heap.entrySet().stream().
                filter(e -> symTableAddr.contains(e.getKey()) || heapAddr.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private List<Integer> getAddrFromSymTable(Collection<Value> symTableValues) {
        return symTableValues.stream().
                filter(v -> v instanceof RefValue)
                .map(v -> ((RefValue) v).getAddress())
                .collect(Collectors.toList());
    }

    private List<Integer> getAddrFromHeapTable(Map<Integer, Value> heapTableValues) {
        return heapTableValues.values().stream().
                filter(v -> v instanceof RefValue)
                .map(v -> ((RefValue) v).getAddress())
                .collect(Collectors.toList());
    }

    public void allSteps() throws MyException, IOException {
        PrgState prg = repository.getCrtPrg();
        repository.logPrgStateExec(prg);
        while (!prg.getStack().isEmpty()) {
            oneStep(prg);
            prg.getHeapTable().setContent(
                    unsafeGarbageCollector(
                            getAddrFromSymTable(prg.getSymTable().getContent().values()),
                            getAddrFromHeapTable(prg.getHeapTable().getContent()),
                            prg.getHeapTable().getContent()));
            repository.logPrgStateExec(prg);
        }
    }
}
