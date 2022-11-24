package Model.Values;

import Model.Types.InterfaceType;
import Model.Types.IntegerType;

public class IntegerValue implements InterfaceValue {

    int val;

    public IntegerValue(int val) {
        this.val = val;
    }

    public int getValue() {
        return this.val;
    }

    @Override
    public InterfaceType getType() {
        return new IntegerType();
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof IntegerValue))
            return false;
        return ((IntegerValue) obj).val == this.val;
    }

    @Override
    public String toString() {
        return String.valueOf(this.val);
    }

}
