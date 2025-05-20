package com.kenzie.db;


import com.kenzie.rest.repository.FriendDataRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DatabaseInitialization implements InitializingBean {

    private final FriendDataRepositoryImpl friendDataRepository;

    /*
         "Kenzie": {"discord_id": 662047058760433666, "meds_time": "10:30 AM", "tz": "Canada/Eastern"},
        "Rishaad": {"discord_id": 614239301198413824, "meds_time": "09:00 AM", "tz": "Canada/Eastern"},
        "Melody": {"discord_id": 615020963402219560, "meds_time": "07:00 AM", "tz": "Canada/Eastern"}

     */
    @Override
    public void afterPropertiesSet() throws Exception {
        // Check if the database is empty and initialize it with some data

        if (friendDataRepository.count() == 0) {
            friendDataRepository.addFriend("Kenzie", 662047058760433666L, "10:30 AM", "Canada/Eastern");
            friendDataRepository.addFriend("Rishaad", 614239301198413824L, "09:00 AM", "Canada/Eastern");
            friendDataRepository.addFriend("Melody", 615020963402219560L, "07:00 AM", "Canada/Eastern");
        }
    }
}
