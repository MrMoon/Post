package com.moon.squad.model.agile;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

import static com.moon.squad.shared.ApplicationConstants.NOT_BLANK;
import static com.moon.squad.shared.ApplicationConstants.NOT_NULL;

@Data
@Document
public class Task implements Serializable {

    @Id
    private String id;
    @NotBlank (message = NOT_BLANK)
    private String name;
    @NotNull (message = NOT_NULL)
    private Date date;
    @NotNull (message = NOT_NULL)
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
