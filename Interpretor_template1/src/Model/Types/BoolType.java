package Model.Types;

import Model.Values.BoolValue;
import Model.Values.Value;

public class BoolType implements Type {
    @Override
    public boolean equals(Object obj) {
        return obj instanceof BoolType;
    }

    @Override
    public String toString() {
        return "boolean";
    }

    @Override
    public Value getDefaultValue() {
        return new BoolValue(false);
    }
}
