package com.kenzie.rest.controller;

import com.kenzie.rest.manager.KenzieBotManager;
import com.kenzie.rest.model.DiscordUserDTO;
import com.kenzie.rest.model.FriendDataRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
public class KenzieBotController {

    private final KenzieBotManager kenzieBotManager;

    @PostMapping("/api/v2/kenziebot/friends")
    public ResponseEntity<String> addFriend(@RequestBody FriendDataRequestDTO friendData) {
        // Logic to create a contact
        log.info("Adding {} with discord Id: {}, medsTime: {}, timeZone: {}",
                friendData.getName(), friendData.getDiscordId(), friendData.getMedsTime(), friendData.getTimeZone());
        kenzieBotManager.addFriend(friendData.getName(), friendData.getDiscordId(), friendData.getMedsTime(), friendData.getTimeZone());
        log.info("Friend {} was successfully added to the list!", friendData.getName());

        return ResponseEntity.ok(String.format("%s was successfully added to the friend's list!", friendData.getName()));
    }

    @GetMapping("/api/v2/kenziebot/friends")
    public List<DiscordUserDTO> getFriends() {
        // Logic to retrieve friends
        log.info("Retrieving friends");
        return kenzieBotManager.getFriends();
    }
}
