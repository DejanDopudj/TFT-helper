package com.ftn.sbnz.model.event;

import com.ftn.sbnz.model.PlayerPosition;
import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;

import java.io.Serializable;
import java.util.Date;

@Role(Role.Type.EVENT)
@Timestamp("timestamp")
@Expires("10m")
public class PlayerPositionEvent implements Serializable {
    private static final long serialVersionUID = 1L;
    private Date timestamp;
    private PlayerPosition position;

    public PlayerPositionEvent() {
        super();
        this.timestamp = new Date();
        this.position = PlayerPosition.NEUTRAL;
    }

    public PlayerPositionEvent(PlayerPosition position) {
        super();
        this.timestamp = new Date();
        this.position = position;
    }

    public PlayerPosition getPosition() {
        return position;
    }

    public void setPosition(PlayerPosition position) {
        this.position = position;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
