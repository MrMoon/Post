package com.moon.squad.model.agile;

import com.moon.squad.model.user.User;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

import static com.moon.squad.shared.ApplicationConstants.NOT_BLANK;
import static com.moon.squad.shared.ApplicationConstants.NOT_NULL;

@Document
@Data
public class Project implements Serializable {

    @Id
    private String id;
    @NotBlank (message = NOT_BLANK)
    private String projectName, teamName;
    @NotNull (message = NOT_NULL)
    private Date deadline;
    private List<User> users;
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
