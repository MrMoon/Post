package com.moon.squad.model.agile;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import static com.moon.squad.shared.ApplicationConstants.NOT_BLANK;
import static com.moon.squad.shared.ApplicationConstants.NOT_NULL;

@Document
public class Report implements Serializable {

    @Id
    @Getter
    @Setter
    private String id;
    @Getter
    private String date = new SimpleDateFormat("E, DD MMM YYYY HH:mm:ss z\t").format(new Date());
    @NotBlank (message = NOT_BLANK)
    @Getter
    @Setter
    private String description;
    @NotNull (message = NOT_NULL)
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
