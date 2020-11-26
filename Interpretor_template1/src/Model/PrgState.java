package Model;

import Model.ADT.IDict;
import Model.ADT.IHeap;
import Model.ADT.IList;
import Model.ADT.IStack;
import Model.Statements.IStatement;
import Model.Values.IntValue;
import Model.Values.StringValue;
import Model.Values.Value;

import java.io.BufferedReader;

public class PrgState {

    IStack<IStatement> exeStack;
    IDict<String, Value> symTable;
    IDict<StringValue, BufferedReader> fileTable;
    IHeap<Integer, Value> heapTable;
    IList<Value> out;
    IStatement originalProgram;

    public PrgState(IStack<IStatement> exeStack, IDict<String, Value> symTable,
                    IDict<StringValue, BufferedReader> fileTable, IHeap<Integer, Value> heapTable,
                    IList<Value> out, IStatement originalProgram) {
        this.exeStack = exeStack;
        this.symTable = symTable;
        this.fileTable = fileTable;
        this.heapTable = heapTable;
        this.out = out;
        this.originalProgram = originalProgram;
    }

    public IStack<IStatement> getStack() {
        return exeStack;
    }

    public IDict<String, Value> getSymTable() {
        return symTable;
    }

    public IDict<StringValue, BufferedReader> getFileTable() {
        return fileTable;
    }

    public IList<Value> getOut() {
        return out;
    }

    public IHeap<Integer, Value> getHeapTable() {
        return heapTable;
    }

    public void setHeapTable(IHeap<Integer, Value> heapTable) {
        this.heapTable = heapTable;
    }

    public void setStack(IStack<IStatement> exeStack) {
        this.exeStack = exeStack;
    }

    public void setSymTable(IDict<String, Value> symTable) {
        this.symTable = symTable;
    }

    public void setFileTable(IDict<StringValue, BufferedReader> fileTable) {
        this.fileTable = fileTable;
    }

    public void setOut(IList<Value> out) {
        this.out = out;
    }

    @Override
    public String toString() {
        return "\n\n===== Program State =====\n" +
                "exeStack: " + exeStack +
                "\nsymTable: " + symTable +
                "\nfileTable: " + fileTable +
                "\nheapTable: " + heapTable +
                "\nout: " + out +
                "\noriginalProgram:" + originalProgram;
    }
}