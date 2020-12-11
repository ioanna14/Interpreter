package Repository;
import Exceptions.MyException;
import Model.PrgState;
import Model.ADT.MyList;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Repository implements IRepository {

    List<PrgState> myPrgStates;
    String logFilePath;

    public Repository(PrgState prg1, String logFilePath) {
        this.logFilePath = logFilePath;
        this.myPrgStates = new ArrayList<>();
        this.myPrgStates.add(prg1);
    }

    @Override
    public List<PrgState> getPrgList() {
        return this.myPrgStates;
    }

    @Override
    public void setPrgList(List<PrgState> newPrograms) {
        this.myPrgStates = newPrograms;
    }

    @Override
    public void addPrg(PrgState newPrg) {
        myPrgStates.add(newPrg);
    }

    @Override
    public void logPrgStateExec(PrgState prgState) throws MyException {
        try {
            try(PrintWriter logFile = new PrintWriter(new BufferedWriter(
                    new FileWriter(logFilePath, true)))){
                logFile.println(prgState);
                logFile.close();
            } catch (MyException e) {
                throw new MyException("No program loaded.");
            }
        } catch (IOException e) {
            throw new MyException("File exception: " + e);
        }
    }

    @Override
    public void clear() {
        myPrgStates.clear();
    }
}
