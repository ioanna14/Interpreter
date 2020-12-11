package Model.ADT;

import Exceptions.ADTException;

import java.util.ArrayDeque;
import java.util.Deque;

public class MyStack<T> implements IStack<T> {
    Deque<T> stack;

    public MyStack() {
        stack = new ArrayDeque<T>();
    }

    @Override
    public T pop() throws ADTException {
        if (this.stack.isEmpty()) {
            throw new ADTException("The stack is empty!");
        }
        return this.stack.pop();
    }

    @Override
    public void push(T v) {
        this.stack.push(v);
    }

    @Override
    public boolean isEmpty() {
        return this.stack.isEmpty();
    }

    @Override
    public String toString() {
        StringBuffer buff = new StringBuffer();
        for (T statement : stack) {
            buff.append(statement.toString()).append("\n");
        }
        return buff.toString();

    }
}
