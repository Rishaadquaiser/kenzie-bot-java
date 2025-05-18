package com.kenzie.rest.repository;

import com.kenzie.db.DiscordUserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class FriendDataRepositoryImpl implements FriendDataRepository {

    private final DiscordUserDAO discordUserDAO;

    public void addFriend(String name, Long discordId, String medsTime, String timeZone) {

        DiscordUserEntity discordUser = DiscordUserEntity.builder()
                .name(name)
                .discordId(discordId)
                .medsTime(medsTime)
                .timeZone(timeZone)
                .build();
        // Implementation for adding a friend to the database
        discordUserDAO.save(discordUser);
    }

    public List<DiscordUserEntity> getFriends() {
        return discordUserDAO.findAll();
    }

    public long count() {
        return discordUserDAO.count();
    }

}
