package com.kenzie.rest.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FriendDataRequestDTO {
    private String name;
    private Long discordId;
    private String medsTime;
    private String timeZone;
}
