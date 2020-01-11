package com.moon.squad.model.agile;

import com.moon.squad.model.user.User;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import static com.moon.squad.shared.ApplicationConstants.NOT_BLANK;
import static com.moon.squad.shared.ApplicationConstants.NOT_EMPTY;
import static com.moon.squad.shared.ApplicationConstants.NOT_NULL;

@Document
public class Event implements Serializable {

    @Id
    @Getter
    @Setter
    private String id;
    @NotBlank (message = NOT_BLANK)
    @Getter
    @Setter
    private String name;
    @NotBlank
    @Getter
    @Setter
    @Length (min = 2, max = 10)
    private String type;
    @NotNull (message = NOT_NULL)
    @Getter
    private String date = new SimpleDateFormat("E, DD MMM YYYY HH:mm:ss z\t").format(new Date());
    @NotEmpty (message = NOT_EMPTY)
    @Getter
    @Setter
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
