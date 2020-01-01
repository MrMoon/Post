package com.moon.squad.model.user;

import com.moon.squad.model.agile.Event;
import com.moon.squad.model.agile.Project;
import com.moon.squad.model.agile.Report;
import com.moon.squad.shared.Constants;

import org.springframework.data.annotation.Id;

import java.util.List;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

public class Team {

    @Id
    @Getter
    @Setter
    private String id;
    @NotBlank(message = Constants.NOT_BLANK)
    @Getter
    @Setter
    private String teamName;
    @Getter
    @Setter
    private List<User> users;
    @Getter
    @Setter
    private List<Report> reports;
    @Getter
    @Setter
    private List<Event> events;
    @Getter
    @Setter
    private List<Project> projects;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Team{");
        sb.append("teamName='").append(teamName).append('\'');
        sb.append(", users=").append(users);
        sb.append(", reports=").append(reports);
        sb.append(", events=").append(events);
        sb.append(", projects=").append(projects);
        sb.append('}');
        return sb.toString();
    }
}
