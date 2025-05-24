package com.kenzie.reminder;

import com.kenzie.rest.manager.KenzieBotManager;
import com.kenzie.rest.model.DiscordUserDTO;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReminderService {
    private final KenzieBotManager kenzieBotManager;
    
    @Value("${kenzie.bot.token}")
    private String token;

    @PostConstruct
    public void init() {
        JDABuilder builder = JDABuilder.createDefault(token);
        builder.addEventListeners(this);
        try {
            builder.build();
        } catch (Exception e) {
            log.error("Failed to initialize Kenzie Bot: {}", e.getMessage());
        }
    }

    @Scheduled(fixedRate = 60000)
    public ResponseEntity<String> sendReminder() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a", Locale.US);
        List<DiscordUserDTO> friends = kenzieBotManager.getFriends();
        for (DiscordUserDTO friend : friends) {
            String friendName = friend.getName();
            String friendTimeZone = friend.getTimeZone();
            String friendMedsTime = friend.getMedsTime();

            LocalTime now = LocalTime.now(ZoneId.of(friendTimeZone));
            String currentTime = now.format(formatter);

            if (currentTime.equals(friendMedsTime)) {
                try {
                    User user = JDABuilder.createDefault(token).build().retrieveUserById(friend.getDiscordId()).complete();
                    if (user != null) {
                        user.openPrivateChannel().queue(channel -> {
                            String message = String.format("Hello, %s, Kenzie Bot here! It is now %s, so remember " +
                                    "to take your medication!", friendName, friendMedsTime);
                            channel.sendMessage(message).queue();
                        });
                        log.info("Reminder sent to {}", friendName);
                    }
                } catch (Exception e) {
                    log.error("Failed to send message to {}: {}", friendName, e.getMessage());
                }
            }
        }
        return ResponseEntity.ok("Reminders sent successfully!");
    }
}
