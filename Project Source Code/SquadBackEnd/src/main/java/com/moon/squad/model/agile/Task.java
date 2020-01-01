package com.moon.squad.model.agile;

import org.springframework.data.annotation.Id;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

import static com.moon.squad.shared.Constants.NOT_BLANK;
import static com.moon.squad.shared.Constants.NOT_NULL;

public class Task {

    @Id
    @Getter
    @Setter
    private String id;
    @Getter
    @Setter
    @NotBlank(message = NOT_BLANK)
    private String name;
    @Getter
    @Setter
    @NotNull(message = NOT_NULL)
    private Date date;
    @Getter
    @Setter
    @NotNull(message = NOT_NULL)
    private Report report;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Task{");
        sb.append("name='").append(name).append('\'');
        sb.append(", date=").append(date);
        sb.append(", report=").append(report);
        sb.append('}');
        return sb.toString();
    }
}
