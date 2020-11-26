package Model.Values;

import Model.Types.BoolType;
import Model.Types.Type;

import java.util.Objects;

public class BoolValue implements Value{
    boolean value;

    public BoolValue(boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }

    @Override
    public Type getType() {
        return new BoolType();
    }

    @Override
    public Value getCopy() {
        return new BoolValue(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BoolValue)) return false;
        BoolValue boolValue = (BoolValue) o;
        return value == boolValue.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "boolean(" + value + ")";
    }
}
