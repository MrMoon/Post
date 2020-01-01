package com.moon.squad.controller.agile;

import com.moon.squad.model.agile.Event;
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

import static com.moon.squad.shared.Constants.ADDED_SUCCESSFULLY;
import static com.moon.squad.shared.Constants.ALL_MAPPING;
import static com.moon.squad.shared.Constants.DELETED_SUCCESSFULLY;
import static com.moon.squad.shared.Constants.EVENT_MAPPING;
import static com.moon.squad.shared.Constants.ID;
import static com.moon.squad.shared.Constants.ID_MAPPING;
import static com.moon.squad.shared.Constants.SLASH;

@RestController
@RequestMapping(EVENT_MAPPING)
@CrossOrigin(ALL_MAPPING)
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping(value = SLASH)
    public List<Event> getAllEvents(){
        return eventService.findAllByOrderByDate();
    }

    @GetMapping(value = ID_MAPPING)
    public @NotNull Optional<Event> getEventById(@PathVariable(ID) String id){
        return eventService.findById(id);
    }

    @PostMapping(value = SLASH)
    public ResponseEntity<?> saveOrUpdateEvent(@RequestBody Event event){
        eventService.saveOrUpdate(event);
        return new ResponseEntity<>(event.toString() + "\n " + event.getClass().getSimpleName() + ' ' + ADDED_SUCCESSFULLY , HttpStatus.OK);
    }

    @DeleteMapping(value = ID_MAPPING)
    public ResponseEntity<?> deleteEventById(@PathVariable String id){
        eventService.deleteById(id);
        return new ResponseEntity<>(id + "\n " + Event.class.getSimpleName() + ' ' + DELETED_SUCCESSFULLY , HttpStatus.OK);
    }
}
