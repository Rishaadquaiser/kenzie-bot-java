package com.kenzie.rest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
@AllArgsConstructor
public class FriendDataRequestDTO {
    @NotNull
    private String name;
    @NotNull
    private Long discordId;
    @NotNull
    private String medsTime;
    @NotNull
    private String timeZone;
}
