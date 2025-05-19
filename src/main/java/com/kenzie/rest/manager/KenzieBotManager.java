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
        return mapToDTOList(friends);

    }

    private List<DiscordUserDTO> mapToDTOList(List<DiscordUserEntity> friends) {
        return friends.stream()
                .map(friend -> new DiscordUserDTO(friend.getName(), friend.getDiscordId(), friend.getMedsTime(), friend.getTimeZone()))
                .toList();
    }
}
