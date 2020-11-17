package View;

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
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws IOException {
        MyStack<IStatement> exeStack = new MyStack<IStatement>();
        IDict<String, Value> symTable = new Dict<String, Value>();
        IDict<StringValue, BufferedReader> fileTable = new Dict<StringValue, BufferedReader>();
        IList<Value> out = new MyList<Value>();
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
        if (!exeStack.isEmpty())
            exeStack.pop();
        exeStack.push(ex1);
        PrgState prg1 = new PrgState(exeStack, symTable, fileTable, out, ex1);
        IRepository repo1 = new Repository(prg1, "log1.txt");
        Controller ctr1 = new Controller(repo1);
        ctr1.addProgram(prg1);
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
        if (!exeStack.isEmpty())
            exeStack.pop();
        exeStack.push(ex2);
        PrgState prg2 = new PrgState(exeStack, symTable, fileTable, out, ex2);
        IRepository repo2 = new Repository(prg2, "log2.txt");
        Controller ctr2 = new Controller(repo2);
        ctr2.addProgram(prg2);
        IStatement ex3 = new CompStatement(
                new VarDeclaration("a", new BoolType()),
                new CompStatement(new VarDeclaration("v", new IntType()),
                        new CompStatement(
                                new AssignStatement("a", new ValueExpression(new BoolValue(true))),
                                new CompStatement(
                                        new IfStatement(new VarExpression("a"),
                                                new AssignStatement("v", new ValueExpression(new IntValue(2))),
                                                new AssignStatement("v", new ValueExpression(new IntValue(3)))
                                        ),
                                        new PrintStatement(new VarExpression("v"))
                                )
                        )
                )
        );
        if (!exeStack.isEmpty())
            exeStack.pop();
        exeStack.push(ex3);
        PrgState prg3 = new PrgState(exeStack, symTable, fileTable, out, ex3);
        IRepository repo3 = new Repository(prg3, "log3.txt");
        Controller ctr3 = new Controller(repo3);
        ctr3.addProgram(prg3);
        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExample("1", ex1.toString(), ctr1));
        menu.addCommand(new RunExample("2", ex2.toString(), ctr2));
        menu.addCommand(new RunExample("3", ex3.toString(), ctr3));
        menu.show();
    }
}


class TextMenu {
    private final Map<String, Command> commands;

    public TextMenu() {
        commands = new HashMap<>();
    }

    public void addCommand(Command c) {
        commands.put(c.getKey(), c);
    }

    private void printMenu() {
        for (Command com : commands.values()) {
            String line = String.format("%4s : %s", com.getKey(), com.getDescription());
            System.out.println(line);
        }
    }

    public void show() throws IOException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printMenu();
            System.out.printf("Input the option: ");
            String key = scanner.nextLine();
            Command com = commands.get(key);
            if (com == null) {
                System.out.println("Invalid Option");
                continue;
            }
            com.execute();
        }
    }
}