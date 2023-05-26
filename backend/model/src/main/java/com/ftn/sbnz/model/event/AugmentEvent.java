package com.ftn.sbnz.model.event;

import lombok.Data;
import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;

import java.util.Date;

@Role(Role.Type.EVENT)
@Expires(("1m"))
@Timestamp("executionTime")
@Data
public class AugmentEvent {
    private String name;
    private Date executionTime;
}