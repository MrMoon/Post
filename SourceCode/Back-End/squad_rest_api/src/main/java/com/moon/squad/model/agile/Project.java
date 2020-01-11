package com.moon.squad.model.agile;

import com.moon.squad.model.user.User;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import static com.moon.squad.shared.ApplicationConstants.NOT_BLANK;
import static com.moon.squad.shared.ApplicationConstants.NOT_NULL;

@Document
public class Project implements Serializable {

    @Id
    @Getter
    @Setter
    private String id;
    @NotBlank (message = NOT_BLANK)
    @Getter
    @Setter
    private String projectName, teamName;
    @Getter
    private String deadline = new SimpleDateFormat("E, DD MMM YYYY HH:mm:ss z\t").format(new Date());
    @Getter
    @Setter
    private List<User> users;
    @Getter
    @Setter
    private List<Task> tasks;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Project{");
        sb.append("projectName='").append(projectName).append('\'');
        sb.append(", teamName='").append(teamName).append('\'');
        sb.append(", deadline=").append(deadline);
        sb.append(", users=").append(users);
        sb.append(", tasks=").append(tasks);
        sb.append('}');
        return sb.toString();
    }
}
