package com.example.volleyball_api_rest.Controller;

import com.example.volleyball_api_rest.Events;
import com.example.volleyball_api_rest.Repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/event")
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @GetMapping("/event")
    public List<Events> getAllEvents() {
        return eventRepository.findAll();
    }

    @GetMapping("/event/{id}")
    public ResponseEntity<Events> getEventById(@PathVariable(value = "id") Integer id){
        Optional<Events> events = eventRepository.findById(id);
        if (events.isPresent()) {
            return ResponseEntity.ok().body(events.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/events/nombre/{event_name}")
    public List<Events> getEventsByName(@PathVariable(value = "event_name") String event_name){
        return eventRepository.findByEvent_name(event_name);
    }

    @GetMapping("/club/{club_id}")
    public List<Events> getEventsByClubId(@PathVariable(value = "club_id") Integer club_id) {
        return eventRepository.findByClub_id(club_id);
    }

    @GetMapping("/public")
    public List<Events> getPublicEvents() {
        return eventRepository.findEventsByEvent_public("publico");
    }

    @PostMapping("/crearEvento")
    public ResponseEntity<Events> createEvents(@RequestBody Events events) {
        {
            return new ResponseEntity<>(eventRepository.save(events), HttpStatus.OK);
        }
    }

    @PutMapping("/event/{id}")
    public ResponseEntity<Events> updateEvents(@PathVariable(value = "id") Integer id, @RequestBody Events eventsDetails) {
        Optional<Events> optionalEvents = eventRepository.findById(id);
        if (optionalEvents.isPresent()) {
            Events events = optionalEvents.get();
            events.setEvent_name(eventsDetails.getEvent_name());
            events.setEvent_date(eventsDetails.getEvent_date());
            events.setEvent_description(eventsDetails.getEvent_description());
            events.setEvent_time(eventsDetails.getEvent_time());
            // Actualiza los demás campos según sea necesario
            final Events updateEvent = eventRepository.save(events);
            return ResponseEntity.ok(updateEvent);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    @DeleteMapping("/event/{id}")
    public ResponseEntity<?> deleteEvents(@PathVariable(value = "id") Integer id) {
        Optional<Events> events = eventRepository.findById(id);
        if (events.isPresent()) {
            eventRepository.delete(events.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
