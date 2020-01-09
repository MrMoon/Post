package com.moon.squad.repository.agile;

import com.moon.squad.model.agile.Event;
import com.moon.squad.repository.shared.CRUDRepository;

import java.util.Date;
import java.util.List;

public interface EventRepository extends CRUDRepository<Event> {
    List<Event> findAllByOrderByDate();

    List<Event> findAllByDate(Date date);
}
