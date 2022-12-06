package Model.Values;

import Model.Types.InterfaceType;
import Model.Types.ReferenceType;

public class ReferenceValue implements InterfaceValue {

    int address;
    InterfaceType locationType;

    public ReferenceValue(int address, InterfaceType locationType) {
        this.address = address;
        this.locationType = locationType;
    }

    public int getAddress() {
        return this.address;
    }

    public InterfaceType getLocationType() {
        return this.locationType;
    }

    @Override
    public InterfaceType getType() {
        return new ReferenceType(this.locationType);
    }

    @Override
    public String toString() {
        return "(" + String.valueOf(this.address) + ", " + String.valueOf(this.locationType) + ")";
    }

}
