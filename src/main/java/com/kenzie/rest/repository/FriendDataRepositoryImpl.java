package com.kenzie.rest.repository;

import com.kenzie.db.DiscordUserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

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

    public DiscordUserEntity updateFriend(UUID uuid, String name, String medsTime, String timeZone) {
        DiscordUserEntity existingUser = discordUserDAO.findById(uuid)
                .orElseThrow(() -> new IllegalArgumentException("User with ID " + uuid + " not found"));

        if (name != null) {
            existingUser.setName(name);
        }
        if (medsTime != null) {
            existingUser.setMedsTime(medsTime);
        }
        if (timeZone != null) {
            existingUser.setTimeZone(timeZone);
        }
        
        return discordUserDAO.save(existingUser);
    }

}
