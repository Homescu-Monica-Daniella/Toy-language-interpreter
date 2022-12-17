package Model.Types;

import Model.Values.InterfaceValue;
import Model.Values.ReferenceValue;

public class ReferenceType implements InterfaceType {

    InterfaceType inner;

    public ReferenceType(InterfaceType inner) {
        this.inner = inner;
    }

    public InterfaceType getInner() {
        return this.inner;
    }

    @Override
    public boolean equals(InterfaceType typ) {
        if (typ instanceof ReferenceType)
            return this.inner.equals(((ReferenceType) typ).getInner());
        else
            return false;
    }

    @Override
    public InterfaceValue defaultValue() {
        return new ReferenceValue(0, this.inner);
    }

    @Override
    public String toString() {
        return "Ref " + this.inner.toString();
    }

}
