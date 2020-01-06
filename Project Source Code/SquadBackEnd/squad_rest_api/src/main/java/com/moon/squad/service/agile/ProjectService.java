package com.moon.squad.service.agile;

import com.moon.squad.model.agile.Project;
import com.moon.squad.model.agile.Task;
import com.moon.squad.model.user.User;
import com.moon.squad.service.shared.CRUDService;

import java.util.Date;
import java.util.List;

public interface ProjectService extends CRUDService<Project> {
    List<Project> findAllOrderByDeadline();

    List<Project> findAllByDeadline(Date date);

    List<User> findAllProjectUsersByProjectId(String id);

    List<Task> findAllProjectTasksByProjectId(String id);
}
