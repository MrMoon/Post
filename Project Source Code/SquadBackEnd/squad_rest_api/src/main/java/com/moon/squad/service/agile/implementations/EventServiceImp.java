package com.moon.squad.service.agile.implementations;

import com.moon.squad.model.agile.Event;
import com.moon.squad.model.user.User;
import com.moon.squad.repository.agile.EventRepository;
import com.moon.squad.repository.shared.SequenceRepository;
import com.moon.squad.service.agile.EventService;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;

import static com.moon.squad.shared.ApplicationConstants.CACHE_EVENT;
import static com.moon.squad.shared.ApplicationConstants.CACHE_ID;
import static com.moon.squad.shared.LoggingConstants.deleting;
import static com.moon.squad.shared.LoggingConstants.gettingAll;
import static com.moon.squad.shared.LoggingConstants.saving;

@Service
@CacheConfig (cacheNames = CACHE_EVENT)
@Slf4j
public class EventServiceImp implements EventService {

    private final SequenceRepository sequenceRepository;
    private final EventRepository eventRepository;

    @Autowired
    public EventServiceImp(SequenceRepository sequenceRepository, EventRepository eventRepository) {
        this.sequenceRepository = sequenceRepository;
        this.eventRepository = eventRepository;
    }

    @Cacheable (key = CACHE_ID)
    @Override
    public @NotNull Optional<Event> findById(String id) {
        return eventRepository.findById(id);
    }

    @Cacheable
    @Override
    public List<Event> findAll() {
        log.info(gettingAll(Event.class, false));
        return eventRepository.findAll();
    }

    @Cacheable
    @Override
    public List<Event> findAllByDate(@NotNull Date date) {
        log.info("Getting All Events in the date " + date.toString());
        return eventRepository.findAllByOrderByDate();
    }

    @Cacheable
    @Override
    public List<Event> findAllByOrderByDate() {
        log.info(gettingAll(Event.class, true) + " By Date");
        return eventRepository.findAllByOrderByDate();
    }

    @Cacheable
    @Override
    public List<User> findAllEventUsersByEventId(String id) {
        log.info("Getting All User from Event With id " + id);
        Optional<Event> event = eventRepository.findById(id);
        return event.isPresent() ? event.get().getUsers() : new ArrayList<>();
    }

    @CachePut (key = "#event.id")
    @Override
    public Event saveOrUpdate(@NotNull Event event) {
        log.info(saving(event.toString()));
        return eventRepository.save(event);
    }

    @CacheEvict (CACHE_ID)
    @Override
    public void deleteUserById(String id) {
        log.info(deleting(Event.class, id));
        eventRepository.deleteById(id);
    }
}
