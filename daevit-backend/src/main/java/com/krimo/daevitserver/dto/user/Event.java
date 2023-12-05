package com.krimo.daevitserver.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.krimo.daevitserver.dto.user.payload.UserData;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Event(
        UserData data,
        String object,
        String type
) {
}

