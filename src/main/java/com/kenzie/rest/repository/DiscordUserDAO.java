package com.kenzie.rest.repository;

import com.kenzie.db.DiscordUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DiscordUserDAO extends JpaRepository<DiscordUserEntity, UUID> {

}
