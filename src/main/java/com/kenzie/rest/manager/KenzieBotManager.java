package com.kenzie.rest.manager;

import com.kenzie.rest.model.DiscordUserDTO;
import com.kenzie.rest.model.DiscordUserMapper;
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
    private final DiscordUserMapper discordUserMapper;

    public void addFriend(String name, Long discordId, String medsTime, String timeZone) {
        // Logic to add a friend
        friendDataRepository.addFriend(name, discordId, medsTime, timeZone);
    }

    public List<DiscordUserDTO> getFriends() {
        // Logic to retrieve friends
        return friendDataRepository.getFriends()
                .stream().map(discordUserMapper::toDiscordUserDTO).toList();
    }
}
