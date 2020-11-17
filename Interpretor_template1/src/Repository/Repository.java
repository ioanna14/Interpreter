package Repository;
import Exceptions.MyException;
import Model.PrgState;
import Model.ADT.MyList;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Repository implements IRepository {

    MyList<PrgState> myPrgStates;
    String logFilePath;

    public Repository(PrgState prg1, String logFilePath) {
        this.logFilePath = logFilePath;
        this.myPrgStates = new MyList<PrgState>();
    }

    @Override
    public PrgState getCrtPrg() {
        return myPrgStates.get(0);
    }

    @Override
    public void addPrg(PrgState newPrg) {
        myPrgStates.add(newPrg);
    }

    @Override
    public void logPrgStateExec(PrgState prgState) throws MyException, IOException {
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
