package com.moon.squad.repository.agile;

import com.moon.squad.model.agile.Task;
import com.moon.squad.repository.shared.CRUDRepository;

import java.util.Date;
import java.util.List;

public interface TaskRepository extends CRUDRepository<Task> {
    List<Task> findAllByDate(Date date);

    List<Task> findAllByOrderByDate();
}
