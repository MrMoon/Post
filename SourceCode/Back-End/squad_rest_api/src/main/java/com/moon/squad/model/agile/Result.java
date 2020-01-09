package com.moon.squad.model.agile;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

import static com.moon.squad.shared.ApplicationConstants.NOT_NULL;

@Data
@Document
public class Result implements Serializable {

    @Id
    private String id;
    @NotNull (message = NOT_NULL)
    private Date date;
    @NotBlank
    private String description;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Result{");
        sb.append("id='").append(id).append('\'');
        sb.append(", date=").append(date);
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }

}
