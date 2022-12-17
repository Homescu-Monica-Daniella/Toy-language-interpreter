package Model.Types;

import Model.Values.InterfaceValue;

public interface InterfaceType {

    boolean equals(InterfaceType typ);

    InterfaceValue defaultValue();

}
