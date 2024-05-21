package com.example.volleyball_api_rest.Repository;

import com.example.volleyball_api_rest.Events;
import com.example.volleyball_api_rest.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventRepository extends JpaRepository<Events, Integer> {
    @Query("SELECT e FROM Events e WHERE e.event_name = :eventName")
    List<Events> findByEvent_name(@Param("eventName") String eventName);
    Events save(Events events);

    void delete(Events events);
    @Query("SELECT e FROM Events e WHERE e.club_id = :club_id")
    List<Events> findByClub_id(Integer club_id);

    @Query("SELECT e FROM Events e WHERE e.event_public = :event_public")
    List<Events> findEventsByEvent_public(@Param("event_public") String event_public);

}
