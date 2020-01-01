package com.moon.squad.service.agile.implementations;

import com.moon.squad.model.agile.Event;
import com.moon.squad.repository.agile.EventRepository;
import com.moon.squad.repository.shared.SequenceRepository;
import com.moon.squad.service.agile.EventService;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImp implements EventService {

    private final SequenceRepository sequenceRepository;
    private final EventRepository eventRepository;

    @Autowired
    public EventServiceImp(SequenceRepository sequenceRepository, EventRepository eventRepository) {
        this.sequenceRepository = sequenceRepository;
        this.eventRepository = eventRepository;
    }

    @Override
    public @NotNull Optional<Event> findById(String id) {
        return eventRepository.findById(id);
    }

    @Override
    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    @Override
    public List<Event> findAllByDate(Date date) {
        return eventRepository.findAllByOrderByDate();
    }

    @Override
    public List<Event> findByDate(Date date) {
        return eventRepository.findByDate(date);
    }

    @Override
    public List<Event> findAllByOrderByDate() {
        return eventRepository.findAllByOrderByDate();
    }

    @Override
    public Event saveOrUpdate(@NotNull Event event) {
        return eventRepository.save(event);
    }

    @Override
    public void deleteById(String id) {
        eventRepository.deleteById(id);
    }
}
