package com.moon.squad.model.agile;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Data;

import static com.moon.squad.shared.ApplicationConstants.NOT_NULL;

@Data
@Document
public class Track implements Serializable {
    @Id
    private String id;
    @NotNull (message = NOT_NULL)
    private Report report;
    @NotNull (message = NOT_NULL)
    private Evaluation evaluation;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Track{");
        sb.append("id='").append(id).append('\'');
        sb.append(", report=").append(report);
        sb.append(", evaluation=").append(evaluation);
        sb.append('}');
        return sb.toString();
    }
}
