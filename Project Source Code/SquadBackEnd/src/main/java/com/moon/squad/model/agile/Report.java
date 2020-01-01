package com.moon.squad.model.agile;

import org.springframework.data.annotation.Id;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

import static com.moon.squad.shared.Constants.NOT_BLANK;
import static com.moon.squad.shared.Constants.NOT_NULL;

public class Report {

    @Id
    @Getter
    @Setter
    private String id;
    @NotNull(message = NOT_NULL)
    @Getter
    @Setter
    private Date date;
    @NotBlank(message = NOT_BLANK)
    @Getter
    @Setter
    private String description;
    @NotNull(message = NOT_NULL)
    @Getter
    @Setter
    private Result result;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Report{");
        sb.append("id='").append(id).append('\'');
        sb.append(", date=").append(date);
        sb.append(", description='").append(description).append('\'');
        sb.append(", result=").append(result);
        sb.append('}');
        return sb.toString();
    }
}
