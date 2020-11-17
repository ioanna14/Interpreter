package Repository;
import Exceptions.MyException;
import Model.PrgState;

import java.io.IOException;

public interface IRepository {
    void addPrg(PrgState newPrg);
    void logPrgStateExec(PrgState prgState) throws MyException, IOException;
    PrgState getCrtPrg();
    void clear();
    }
