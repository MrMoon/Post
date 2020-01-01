package com.moon.squad.service.agile;

import com.moon.squad.model.agile.Task;
import com.moon.squad.service.shared.CRUDService;

import java.util.Date;
import java.util.List;

public interface TaskService extends CRUDService<Task> {
    List<Task> findAllByOrderByDate();
    List<Task> findAllByDate(Date date);
}
