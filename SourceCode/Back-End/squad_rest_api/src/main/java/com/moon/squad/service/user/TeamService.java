package com.moon.squad.service.user;

import com.moon.squad.model.agile.Event;
import com.moon.squad.model.agile.Project;
import com.moon.squad.model.agile.Report;
import com.moon.squad.model.user.Team;
import com.moon.squad.model.user.User;
import com.moon.squad.service.shared.CRUDService;

import java.util.List;

public interface TeamService extends CRUDService<Team> {
    List<Team> findAllByTeamName(String teamName);

    List<Team> findAllByOrderByTeamName();

    List<User> findAllTeamMembersByTeamId(String id);

    List<Report> findAllTeamReportsByTeamId(String id);

    List<Event> findAllTeamEventsByTeamId(String id);

    List<Project> findAllTeamProjectsByTeamId(String id);
}
