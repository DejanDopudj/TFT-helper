package com.ftn.sbnz.model.event;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;

import java.io.Serializable;
import java.util.Date;

@Role(Role.Type.EVENT)
@Timestamp("timestamp")
@Expires("10m")
public class PlayerPositionValueEvent implements Serializable {
    private static final long serialVersionUID = 1L;
    private Date timestamp;
    private double value;

    public PlayerPositionValueEvent(double value) {
        this.timestamp = new Date();
        this.value = value;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
