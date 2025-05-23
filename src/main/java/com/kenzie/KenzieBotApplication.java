package com.kenzie;

import com.kenzie.rest.manager.KenzieBotManager;
import com.kenzie.rest.model.DiscordUserDTO;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@EntityScan(basePackages = {"com.kenzie"})
@EnableJpaRepositories(basePackages = {"com.kenzie"})
@SpringBootApplication
@EnableScheduling
@Slf4j
@RequiredArgsConstructor
public class KenzieBotApplication extends ListenerAdapter {

    @Value("${kenzie.bot.token}")
    private String token;

    private final KenzieBotManager kenzieBotManager;

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

    public static void main(String[] args) {
        System.setProperty("spring.profiles.active", "LOCAL");
        SpringApplication.run(KenzieBotApplication.class, args);
    }


    @Scheduled(fixedRate = 60000) // Run every minute
    public void sendReminders() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a", Locale.US);
        // Retrieve friends from the database
        List<DiscordUserDTO> friends = kenzieBotManager.getFriends();
        for (DiscordUserDTO friend : friends) {
            String friendName = friend.getName();
            String friendTimeZone = friend.getTimeZone();
            String friendMedsTime = friend.getMedsTime();


            // Get current time in friend's timezone
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
    }
}