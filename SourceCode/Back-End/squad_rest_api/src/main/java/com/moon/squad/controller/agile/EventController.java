package com.moon.squad.controller.agile;

import com.moon.squad.model.agile.Event;
import com.moon.squad.model.user.User;
import com.moon.squad.service.agile.EventService;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import static com.moon.squad.shared.ApplicationConstants.ADDED_SUCCESSFULLY;
import static com.moon.squad.shared.ApplicationConstants.DELETED_SUCCESSFULLY;
import static com.moon.squad.shared.ApplicationConstants.EVENT_MAPPING;
import static com.moon.squad.shared.ApplicationConstants.ID;
import static com.moon.squad.shared.ApplicationConstants.ID_MAPPING;
import static com.moon.squad.shared.ApplicationConstants.LOCALHOST_4200;
import static com.moon.squad.shared.ApplicationConstants.SLASH;
import static com.moon.squad.shared.ApplicationConstants.USER_MAPPING;

@RestController
@RequestMapping (EVENT_MAPPING)
@CrossOrigin(origins = LOCALHOST_4200)
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @CrossOrigin (origins = LOCALHOST_4200)
    @GetMapping (SLASH)
    public List<Event> getAllEvents() {
        return eventService.findAllByOrderByDate();
    }

    @CrossOrigin(origins = LOCALHOST_4200)
    @GetMapping (ID_MAPPING)
    public @NotNull Optional<Event> getEventById(@PathVariable (ID) String id) {
        return eventService.findById(id);
    }

    @CrossOrigin(origins = LOCALHOST_4200)
    @GetMapping (ID_MAPPING + USER_MAPPING)
    public List<User> getAllEventUsers(@PathVariable (ID) String id) {
        return eventService.findAllEventUsersByEventId(id);
    }

    @CrossOrigin(origins = LOCALHOST_4200)
    @PostMapping (SLASH)
    public ResponseEntity<?> saveOrUpdateEvent(@RequestBody Event event) {
        eventService.saveOrUpdate(event);
        return new ResponseEntity<>(event.toString() + "\n " + event.getClass().getSimpleName() + ' ' + ADDED_SUCCESSFULLY, HttpStatus.OK);
    }

    @CrossOrigin(origins = LOCALHOST_4200)
    @DeleteMapping (ID_MAPPING)
    public ResponseEntity<?> deleteEventById(@PathVariable String id) {
        eventService.deleteUserById(id);
        return new ResponseEntity<>(id + "\n " + Event.class.getSimpleName() + ' ' + DELETED_SUCCESSFULLY, HttpStatus.OK);
    }
}
