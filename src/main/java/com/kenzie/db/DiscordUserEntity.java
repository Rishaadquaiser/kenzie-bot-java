package com.kenzie.db;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "discord_friend")
@AllArgsConstructor
@Setter
@Getter
@Builder
public class DiscordUserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "discord_id", nullable = false)
    private Long discordId;

    @Column(name = "meds_time", nullable = false)
    private String medsTime;

    @Column(name = "time_zone", nullable = false)
    private String timeZone;
    // Constructors, getters, and setters

    protected DiscordUserEntity() {

    }
}