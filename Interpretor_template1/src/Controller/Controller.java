package Controller;

import Exceptions.MyException;
import Model.ADT.IDict;
import Model.PrgState;
import Model.Values.RefValue;
import Model.Values.Value;
import Repository.*;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Controller {
    IRepository repository;
    ExecutorService executor;

    public Controller(IRepository repository) {
        this.repository = repository;
    }

    public void addProgram(PrgState newPrg) {
        this.repository.addPrg(newPrg);
    }

    public List<PrgState> removeCompletedPrg(List<PrgState> inPrgList) {
        return inPrgList.stream().filter(PrgState::isNotCompleted)
                .collect(Collectors.toList());
    }

    private Map<Integer, Value> conservativeGarbageCollector(List<IDict<String, Value>> symTables, Map<Integer, Value> heapMap) {
        List<Integer> heapAddresses = getAddrFromHeapTable(heapMap);
        List<Integer> allAddresses = new ArrayList<>(heapAddresses);
        symTables.forEach(symTable -> {
            allAddresses.addAll(this.getAddrFromSymTable(symTable.getContent().values()));
        });

        return heapMap.entrySet().stream().
                filter((e) -> allAddresses.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private Map<Integer, Value> safeGarbageCollector(List<Integer> symTableAddr, List<Integer> heapAddr, Map<Integer, Value> heap) {
        return heap.entrySet().stream().
                filter((e) -> symTableAddr.contains(e.getKey()) || heapAddr.contains(e.getKey()))
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

    public void oneStepForAllPrg(List<PrgState> prgList) throws InterruptedException {
        prgList.forEach(prg -> repository.logPrgStateExec(prg));

        List<Callable<PrgState>> callList =
                prgList.stream()
                        .map(
                                (PrgState p) -> (Callable<PrgState>) (p::oneStep)
                        )
                        .collect(Collectors.toList());

        List<PrgState> newPrgList = executor.invokeAll(callList).stream()
                .map(future -> {
                    try {
                        return future.get();
                    } catch (InterruptedException | ExecutionException e) {
                        System.out.println(e.getMessage());
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        prgList.addAll(newPrgList);
        prgList.forEach(prg -> repository.logPrgStateExec(prg));
        repository.setPrgList(prgList);
    }

    public void allSteps() throws InterruptedException {
        executor = Executors.newFixedThreadPool(2);
        List<PrgState> prgList = removeCompletedPrg(repository.getPrgList());
        try {
            while (prgList.size() > 0) {
                prgList.get(0).getHeapTable().setContent(
                        conservativeGarbageCollector(
                                prgList.stream().map(PrgState::getSymTable).collect(Collectors.toList()),
                                prgList.get(0).getHeapTable().getContent()
                        ));
                oneStepForAllPrg(prgList);
                prgList = removeCompletedPrg(repository.getPrgList());
            }
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
        executor.shutdownNow();
        repository.setPrgList(prgList);
    }
}
