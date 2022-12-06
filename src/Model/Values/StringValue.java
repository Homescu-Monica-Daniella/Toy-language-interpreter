package Model.Values;

import Model.Types.InterfaceType;
import Model.Types.StringType;

public class StringValue implements InterfaceValue {

    String val;

    public StringValue(String val) {
        this.val = val;
    }

    public String getValue() {
        return this.val;
    }

    @Override
    public InterfaceType getType() {
        return new StringType();
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof StringValue))
            return false;
        return ((StringValue) obj).val.equals(this.val);
    }

    @Override
    public String toString() {
        return this.val;
    }

}
