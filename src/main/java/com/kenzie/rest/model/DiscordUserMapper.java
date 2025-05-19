package com.kenzie.rest.model;

import com.kenzie.db.DiscordUserEntity;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface DiscordUserMapper {

    DiscordUserDTO map(DiscordUserEntity discordUserEntity);
}
