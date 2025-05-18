package com.kenzie.rest.repository;

import com.kenzie.db.DiscordUserEntity;

import java.util.List;


public interface FriendDataRepository {
    // Custom query methods can be defined here if needed
    // For example, find by userId or any other field

    void addFriend(String name, Long discordId, String medsTime, String timeZone);

    List<DiscordUserEntity> getFriends();

}
