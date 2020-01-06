package com.moon.squad.model.agile;

import com.moon.squad.model.user.User;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

import static com.moon.squad.shared.ApplicationConstants.NOT_BLANK;
import static com.moon.squad.shared.ApplicationConstants.NOT_EMPTY;
import static com.moon.squad.shared.ApplicationConstants.NOT_NULL;

@Data
@Document
public class Event implements Serializable {

    @Id
    private String id;
    @NotBlank (message = NOT_BLANK)
    private String name;
    @NotBlank
    @Length (min = 2, max = 10)
    private String type;
    @NotNull (message = NOT_NULL)
    private Date date;
    @NotEmpty (message = NOT_EMPTY)
    private List<User> users;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Event{");
        sb.append("id='").append(id).append('\'');
        sb.append(", type='").append(type).append('\'');
        sb.append(", date=").append(date);
        sb.append(", users=").append(users);
        sb.append('}');
        return sb.toString();
    }
}
