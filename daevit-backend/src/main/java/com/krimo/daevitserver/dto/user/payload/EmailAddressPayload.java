package com.krimo.daevitserver.dto.user.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public record EmailAddressPayload(
        String id,
        String email_address

) {
}
