package com.krimo.daevitserver.dto.user.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record EmailAddressPayload(
        String id,
        String email_address

) {
}
