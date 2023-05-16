package com.ftn.sbnz.model.event;

import com.ftn.sbnz.model.RoundResult;
import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;

import java.io.Serializable;
import java.util.Date;

@Role(Role.Type.EVENT)
@Timestamp("timestamp")
@Expires("30m")
public class RoundResultEvent implements Serializable {
    private static final long serialVersionUID = 1L;
    private Date timestamp;
    private RoundResult result;

    public RoundResultEvent() {
        super();
        this.timestamp = new Date();
    }

    public RoundResultEvent(RoundResult result) {
        super();
        this.result = result;
        this.timestamp = new Date();
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public RoundResult getResult() {
        return result;
    }

    public void setResult(RoundResult result) {
        this.result = result;
    }
}
