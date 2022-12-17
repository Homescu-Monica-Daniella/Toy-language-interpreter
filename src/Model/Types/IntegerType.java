package Model.Types;

import Model.Values.InterfaceValue;
import Model.Values.IntegerValue;

public class IntegerType implements InterfaceType {

    @Override
    public boolean equals(InterfaceType typ) {
        return typ instanceof IntegerType;
    }

    @Override
    public InterfaceValue defaultValue() {
        return new IntegerValue(0);
    }

    @Override
    public String toString() {
        return "int";
    }

}
