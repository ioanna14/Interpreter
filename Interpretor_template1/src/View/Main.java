package View;

import Exceptions.MyException;
import Model.ADT.*;
import Model.Statements.*;
import Model.Values.*;
import Model.Types.*;
import Model.Expressions.*;
import Model.PrgState;
import Repository.*;
import Controller.Controller;

import java.io.BufferedReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        IStatement ex1 = new CompStatement(
                new VarDeclaration("a", new IntType()),
                new CompStatement(
                        new VarDeclaration("b", new IntType()),
                        new CompStatement(
                                new AssignStatement("a",
                                        new ArithExpression(1, new ValueExpression(new IntValue(2)),
                                                new ArithExpression(3, new ValueExpression(new IntValue(3)),
                                                        new ValueExpression(new IntValue(5))))
                                ),
                                new CompStatement(new AssignStatement("b",
                                        new ArithExpression(1, new VarExpression("a"),
                                                new ValueExpression(new IntValue(1)))),
                                        new PrintStatement(new VarExpression("b"))
                                )
                        )
                )
        );
        try {
            IDict<String, Type> typeEnv = new Dict<String, Type>();
            ex1.typeCheck(typeEnv);
        } catch (MyException e) {
            throw new MyException("TypeChecker FAIL: " + e.getMessage());
        }
        MyStack<IStatement> exeStack1 = new MyStack<IStatement>();
        IDict<String, Value> symTable1 = new Dict<String, Value>();
        IHeap<Integer, Value> heapTable1 = new MyHeap<Value>();
        IDict<StringValue, BufferedReader> fileTable1 = new Dict<StringValue, BufferedReader>();
        IList<Value> out1 = new MyList<Value>();
        exeStack1.push(ex1);
        PrgState prg1 = new PrgState(exeStack1, symTable1, fileTable1, heapTable1, out1, ex1);
        IRepository repo1 = new Repository(prg1, "log1.txt");
        Controller ctr1 = new Controller(repo1);

        IStatement ex2 = new CompStatement(
                new VarDeclaration("a", new IntType()),
                new CompStatement(
                        new VarDeclaration("b", new IntType()),
                        new CompStatement(
                                new AssignStatement("a",
                                        new ArithExpression(1, new ValueExpression(new IntValue(2)),
                                                new ArithExpression(3, new ValueExpression(new IntValue(3)),
                                                        new ValueExpression(new IntValue(5))))
                                ),
                                new CompStatement(new AssignStatement("b",
                                        new ArithExpression(1, new VarExpression("a"),
                                                new ValueExpression(new IntValue(1)))),
                                        new PrintStatement(new VarExpression("b"))
                                )
                        )
                )
        );
        try {
            IDict<String, Type> typeEnv = new Dict<String, Type>();
            ex2.typeCheck(typeEnv);
        } catch (MyException e) {
            throw new MyException("TypeChecker FAIL: " + e.getMessage());
        }
        MyStack<IStatement> exeStack2 = new MyStack<IStatement>();
        IDict<String, Value> symTable2 = new Dict<String, Value>();
        IHeap<Integer, Value> heapTable2 = new MyHeap<Value>();
        IDict<StringValue, BufferedReader> fileTable2 = new Dict<StringValue, BufferedReader>();
        IList<Value> out2 = new MyList<Value>();
        exeStack2.push(ex2);
        PrgState prg2 = new PrgState(exeStack2, symTable2, fileTable2, heapTable2, out2, ex2);
        IRepository repo2 = new Repository(prg2, "log2.txt");
        Controller ctr2 = new Controller(repo2);

        IStatement ex3 = new CompStatement(
                new VarDeclaration("v", new RefType(new IntType())),
                new CompStatement(
                        new HeapAllocStatement("v", new ValueExpression(new IntValue(20))),
                        new CompStatement(
                                new VarDeclaration("a", new RefType(new RefType(new IntType()))),
                                new CompStatement(
                                        new HeapAllocStatement("a", new VarExpression("v")),
                                        new CompStatement(
                                                new HeapAllocStatement("v", new ValueExpression(new IntValue(30))),
                                                new PrintStatement(new HeapReadExpression(new HeapReadExpression(new VarExpression("a"))))
                                        )
                                ))
                )
        );
        try {
            IDict<String, Type> typeEnv = new Dict<String, Type>();
            ex3.typeCheck(typeEnv);
        } catch (MyException e) {
            throw new MyException("TypeChecker FAIL: " + e.getMessage());
        }
        MyStack<IStatement> exeStack3 = new MyStack<IStatement>();
        IDict<String, Value> symTable3 = new Dict<String, Value>();
        IHeap<Integer, Value> heapTable3 = new MyHeap<Value>();
        IDict<StringValue, BufferedReader> fileTable3 = new Dict<StringValue, BufferedReader>();
        IList<Value> out3 = new MyList<Value>();
        exeStack3.push(ex3);
        PrgState prg3 = new PrgState(exeStack3, symTable3, fileTable3, heapTable3, out3, ex3);
        IRepository repo3 = new Repository(prg3, "log3.txt");
        Controller ctr3 = new Controller(repo3);

        IStatement ex4 = new CompStatement(
                new VarDeclaration("varf", new StringType()),
                new CompStatement(new AssignStatement("varf", new ValueExpression(new StringValue("test.in"))),
                        new CompStatement(
                                new OpenRFileStatement(new VarExpression("varf")),
                                new CompStatement(
                                        new VarDeclaration("varc", new IntType()),
                                        new CompStatement(
                                                new ReadFileStatement(new VarExpression("varf"), "varc"),
                                                new CompStatement(
                                                        new PrintStatement(new VarExpression("varc")),
                                                        new CompStatement(
                                                                new ReadFileStatement(new VarExpression("varf"), "varc"),
                                                                new CompStatement(
                                                                        new PrintStatement(new VarExpression("varc")),
                                                                        new CloseRFileStatement(new VarExpression("varf"))
                                                                )
                                                        )
                                                )
                                        )
                                )
                        )
                )
        );

        try {
            IDict<String, Type> typeEnv = new Dict<String, Type>();
            ex4.typeCheck(typeEnv);
        } catch (MyException e) {
            throw new MyException("TypeChecker FAIL: " + e.getMessage());
        }
        MyStack<IStatement> exeStack4 = new MyStack<IStatement>();
        IDict<String, Value> symTable4 = new Dict<String, Value>();
        IHeap<Integer, Value> heapTable4 = new MyHeap<Value>();
        IDict<StringValue, BufferedReader> fileTable4 = new Dict<StringValue, BufferedReader>();
        IList<Value> out4 = new MyList<Value>();
        exeStack4.push(ex4);
        PrgState prg4 = new PrgState(exeStack4, symTable4, fileTable4, heapTable4, out4, ex4);
        IRepository repo4 = new Repository(prg4, "log4.txt");
        Controller ctr4 = new Controller(repo4);

        IStatement ex5 = new CompStatement(
                new VarDeclaration("v", new IntType()),
                new CompStatement(new AssignStatement("v", new ValueExpression(new IntValue(6))),
                        new CompStatement(
                                new WhileStatement(new RelationalExpression(new VarExpression("v"),
                                        new ValueExpression(new IntValue(4)), ">"),
                                        new CompStatement(
                                                new PrintStatement(new VarExpression("v")),
                                                new AssignStatement("v", new ArithExpression(2,
                                                        new VarExpression("v"),
                                                        new ValueExpression(new IntValue(1)))
                                                )
                                        )
                                ),
                                new PrintStatement(new VarExpression("v")))
                )
        );
        try {
            IDict<String, Type> typeEnv = new Dict<String, Type>();
            ex5.typeCheck(typeEnv);
        } catch (MyException e) {
            throw new MyException("TypeChecker FAIL: " + e.getMessage());
        }
        MyStack<IStatement> exeStack5 = new MyStack<IStatement>();
        IDict<String, Value> symTable5 = new Dict<String, Value>();
        IHeap<Integer, Value> heapTable5 = new MyHeap<Value>();
        IDict<StringValue, BufferedReader> fileTable5 = new Dict<StringValue, BufferedReader>();
        IList<Value> out5 = new MyList<Value>();
        exeStack5.push(ex5);
        PrgState prg5 = new PrgState(exeStack5, symTable5, fileTable5, heapTable5, out5, ex5);
        IRepository repo5 = new Repository(prg5, "log5.txt");
        Controller ctr5 = new Controller(repo5);

        /*
        int v;
        Ref int a;
        v=10;
        new(a,22);
        fork(
            wH(a,30);
            v=32;
            print(v);
            print(rH(a))
        );
        print(v);
        print(rH(a))

        At the end:
        Id=1
        SymTable_1={v->10,a->(1,int)}
        Id=10
        SymTable_10={v->32,a->(1,int)}
        Heap={1->30}
        Out={10,30,32,30}
        */

        IStatement ex6 = new CompStatement(
                new VarDeclaration("v", new IntType()),
                new CompStatement(
                        new VarDeclaration("a", new RefType(new IntType())),
                        new CompStatement(
                                new AssignStatement("v", new ValueExpression(new IntValue(10))),
                                new CompStatement(
                                        new HeapAllocStatement("a", new ValueExpression(new IntValue(22))),
                                        new CompStatement(
                                                new ForkStatement(
                                                        new CompStatement(
                                                                new HeapWriteStatement("a", new ValueExpression(new IntValue(30))),
                                                                new CompStatement(
                                                                        new AssignStatement("v", new ValueExpression(new IntValue(32))),
                                                                        new CompStatement(
                                                                                new PrintStatement(new VarExpression("v")),
                                                                                new PrintStatement(new HeapReadExpression(new VarExpression("a")))
                                                                        )
                                                                )
                                                        )
                                                ),
                                                new CompStatement(
                                                        new PrintStatement(new VarExpression("v")),
                                                        new PrintStatement(new HeapReadExpression(new VarExpression("a")))
                                                )
                                        )
                                )
                        )
                )

        );
        try {
            IDict<String, Type> typeEnv = new Dict<String, Type>();
            ex6.typeCheck(typeEnv);
        } catch (MyException e) {
            throw new MyException("TypeChecker FAIL: " + e.getMessage());
        }
        MyStack<IStatement> exeStack6 = new MyStack<IStatement>();
        IDict<String, Value> symTable6 = new Dict<String, Value>();
        IHeap<Integer, Value> heapTable6 = new MyHeap<Value>();
        IDict<StringValue, BufferedReader> fileTable6 = new Dict<StringValue, BufferedReader>();
        IList<Value> out6 = new MyList<Value>();
        exeStack6.push(ex6);
        PrgState prg6 = new PrgState(exeStack6, symTable6, fileTable6, heapTable6, out6, ex6);
        IRepository repo6 = new Repository(prg6, "log6.txt");
        Controller ctr6 = new Controller(repo6);

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExample("1", ex1.toString(), ctr1));
        menu.addCommand(new RunExample("2", ex2.toString(), ctr2));
        menu.addCommand(new RunExample("3", ex3.toString(), ctr3));
        menu.addCommand(new RunExample("4", ex4.toString(), ctr4));
        menu.addCommand(new RunExample("5", ex5.toString(), ctr5));
        menu.addCommand(new RunExample("5", ex5.toString(), ctr5));
        menu.addCommand(new RunExample("6", ex6.toString(), ctr6));
        menu.show();
    }
}
