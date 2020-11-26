package Model.Types;

import Model.Values.RefValue;
import Model.Values.Value;

import java.util.Objects;

public class RefType implements Type {
    Type inner;

    public RefType(Type inner) {
        this.inner = inner;
    }

    public Type getInner() {
        return inner;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof RefType)
            return inner.equals(((RefType) o).getInner());
        else return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(inner);
    }

    @Override
    public Value getDefaultValue() {
        RefValue val = new RefValue(0, inner);
        return val;
    }

    @Override
    public String toString() {
        return "ref(" + inner.toString() + ')';
    }
}
