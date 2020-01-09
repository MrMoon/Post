package com.moon.squad.model.user;

import com.moon.squad.model.agile.Event;
import com.moon.squad.model.agile.Project;
import com.moon.squad.model.agile.Report;
import com.moon.squad.shared.ApplicationConstants;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
@Document
public class Team implements Serializable {
    @Id
    private String id;
    @NotBlank (message = ApplicationConstants.NOT_BLANK)
    private String teamName;
    private List<User> users;
    private List<Report> reports;
    private List<Event> events;
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
