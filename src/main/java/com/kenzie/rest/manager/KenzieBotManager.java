package com.kenzie.rest.manager;

import com.kenzie.db.DiscordUserEntity;
import com.kenzie.rest.model.DiscordUserDTO;
import com.kenzie.rest.repository.FriendDataRepositoryImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class KenzieBotManager {

    private final FriendDataRepositoryImpl friendDataRepository;

    public void addFriend(String name, Long discordId, String medsTime, String timeZone) {
        // Logic to add a friend
        friendDataRepository.addFriend(name, discordId, medsTime, timeZone);
    }

    public List<DiscordUserDTO> getFriends() {
        // Logic to retrieve friends
        List<DiscordUserEntity> friends = friendDataRepository.getFriends();
        return friends.stream()
                .map(this::mapToDTO)
                .toList();

    }

    public DiscordUserDTO updateFriend(Long discordId, String name, String medsTime, String timeZone) {
        // Logic to update a friend
        DiscordUserEntity updatedUser = friendDataRepository.updateFriend(name, discordId, medsTime, timeZone);
        return mapToDTO(updatedUser);
    }

    private DiscordUserDTO mapToDTO(DiscordUserEntity friend) {
        return new DiscordUserDTO(friend.getName(), friend.getDiscordId(), friend.getMedsTime(), friend.getTimeZone());
    }
}
