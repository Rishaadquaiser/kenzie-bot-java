package com.kenzie.rest.model;

import com.kenzie.db.DiscordUserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Service;

@Service
@Mapper(componentModel = "spring")
public interface DiscordUserMapper {
    @Mappings({
            @Mapping(source = "discordId", target = "discordId"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "medsTime", target = "medsTime"),
            @Mapping(source = "timeZone", target = "timeZone")
    })
    DiscordUserDTO toDiscordUserDTO(DiscordUserEntity discordUserEntity);

}
