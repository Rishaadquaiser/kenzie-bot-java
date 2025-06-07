package com.kenzie.rest.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FriendDataUpdateRequestDTO {
    private String name;
    private String medsTime;
    private String timeZone;
}
