package com.moon.squad.model.agile;

import com.moon.squad.model.user.User;

import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

import static com.moon.squad.shared.Constants.NOT_BLANK;
import static com.moon.squad.shared.Constants.NOT_NULL;

public class Project {

    @Id
    @Getter
    @Setter
    private String id;
    @Getter
    @Setter
    @NotBlank(message = NOT_BLANK)
    private String projectName, teamName;
    @Getter
    @Setter
    @NotNull(message = NOT_NULL)
    private Date deadline;
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
