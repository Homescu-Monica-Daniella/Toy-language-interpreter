package Model.Values;

import Model.Types.InterfaceType;
import Model.Types.BooleanType;

public class BooleanValue implements InterfaceValue {

    boolean val;

    public BooleanValue(boolean val) {
        this.val = val;
    }

    public boolean getValue() {
        return this.val;
    }

    @Override
    public InterfaceType getType() {
        return new BooleanType();
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof BooleanValue))
            return false;
        return ((BooleanValue) obj).val == this.val;
    }

    @Override
    public String toString() {
        return String.valueOf(this.val);
    }

}
