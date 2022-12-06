package Model.Types;

import Model.Values.InterfaceValue;
import Model.Values.BooleanValue;

public class BooleanType implements InterfaceType {

    @Override
    public boolean equals(InterfaceType typ) {
        return typ instanceof BooleanType;
    }

    @Override
    public InterfaceValue defaultValue() {
        return new BooleanValue(false);
    }

    @Override
    public String toString() {
        return "bool";
    }

}
