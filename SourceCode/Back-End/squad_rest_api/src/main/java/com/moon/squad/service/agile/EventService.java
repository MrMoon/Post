package com.moon.squad.service.agile;

import com.moon.squad.model.agile.Event;
import com.moon.squad.model.user.User;
import com.moon.squad.service.shared.CRUDService;

import java.util.Date;
import java.util.List;

public interface EventService extends CRUDService<Event> {
    List<Event> findAllByDate(Date date);

    List<Event> findAllByOrderByDate();

    List<User> findAllEventUsersByEventId(String id);

}
