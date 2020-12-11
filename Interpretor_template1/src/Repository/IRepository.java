package Repository;
import Exceptions.MyException;
import Model.ADT.MyList;
import Model.PrgState;

import java.util.List;

public interface IRepository {
    void addPrg(PrgState newPrg);
    void logPrgStateExec(PrgState prgState) throws MyException;
    List<PrgState> getPrgList();
    void setPrgList(List<PrgState> newPrograms);
    void clear();
    }
