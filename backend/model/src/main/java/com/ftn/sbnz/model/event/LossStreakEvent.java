package com.ftn.sbnz.model.event;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;

import java.io.Serializable;
import java.util.Date;


@Role(Role.Type.EVENT)
@Timestamp("timestamp")
@Expires("30m")
public class LossStreakEvent implements Serializable {
    private static final long serialVersionUID = 1L;
    private Date timestamp;

    public LossStreakEvent() {
        super();
        this.timestamp = new Date();
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
