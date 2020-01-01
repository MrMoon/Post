package com.moon.squad.model.agile;

import com.moon.squad.model.user.User;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

import static com.moon.squad.shared.Constants.*;

public class Event {

    @Id
    @Getter
    @Setter
    private String id;
    @NotBlank
    @Length(min = 2 , max = 10)
    @Getter
    @Setter
    private String type;
    @NotNull(message = NOT_NULL)
    @Getter
    @Setter
    private Date date;
    @NotEmpty(message = NOT_EMPTY)
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
