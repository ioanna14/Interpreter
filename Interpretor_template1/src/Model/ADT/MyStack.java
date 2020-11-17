package Model.ADT;

import Exceptions.ADTException;

import java.util.Stack;

public class MyStack<T> implements IStack<T> {
    Stack<T> stack;

    public MyStack() {
        stack = new Stack<T>();
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
        return "Stack: " + stack;
    }
}
