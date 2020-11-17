package View;

import Controller.Controller;
import Exceptions.MyException;

import java.io.IOException;

public abstract class Command {
    private final String key;
    private final String description;

    public Command(String key, String description) {
        this.key = key;
        this.description = description;
    }

    public abstract void execute() throws IOException;

    public String getKey() {
        return key;
    }

    public String getDescription() {
        return description;
    }
}

class ExitCommand extends Command {
    public ExitCommand(String key, String desc) {
        super(key, desc);
    }

    @Override
    public void execute() {
        System.exit(0);
    }
}


class RunExample extends Command {
    private final Controller ctr;

    public RunExample(String key, String desc, Controller ctr) {
        super(key, desc);
        this.ctr = ctr;
    }

    @Override
    public void execute() throws IOException {
        try {
            ctr.allSteps();
        } catch (MyException | IOException me) {
            System.out.println(me.getMessage());
        }
    }
}