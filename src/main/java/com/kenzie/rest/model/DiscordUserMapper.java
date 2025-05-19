package com.kenzie.rest.model;

import com.kenzie.db.DiscordUserEntity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

@Service
@Mapper(componentModel = "spring")
public interface DiscordUserMapper {
    DiscordUserDTO map(DiscordUserEntity discordUserEntity);
}
