package Model.Types;

import Model.Values.InterfaceValue;
import Model.Values.StringValue;

public class StringType implements InterfaceType {

    @Override
    public boolean equals(InterfaceType typ) {
        return typ instanceof StringType;
    }

    @Override
    public InterfaceValue defaultValue() {
        return new StringValue("");
    }

    @Override
    public String toString() {
        return "str";
    }

}
