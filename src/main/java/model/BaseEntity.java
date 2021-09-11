package model;

import java.io.Serializable;

public interface BaseEntity<ID> extends Serializable {
    ID getId();
}
