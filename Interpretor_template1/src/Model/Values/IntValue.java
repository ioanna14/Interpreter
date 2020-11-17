package Model.Values;

import Model.Types.IntType;
import Model.Types.Type;

import java.util.Objects;

public class IntValue implements Value{
    int value;

    public IntValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IntValue)) return false;
        IntValue intValue = (IntValue) o;
        return value == intValue.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public Type getType() {
        return new IntType();
    }

    @Override
    public String toString() {
        return "int(" + value + ")";
    }
}
