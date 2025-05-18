package com.kenzie.rest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiscordUserDTO {
    private String name;
    private Long discordId;
    private String medsTime;
    private String timeZone;
}
