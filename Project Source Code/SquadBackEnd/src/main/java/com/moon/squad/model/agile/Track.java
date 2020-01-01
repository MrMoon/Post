package com.moon.squad.model.agile;

import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

import static com.moon.squad.shared.Constants.NOT_NULL;

public class Track {

    @Id
    @Getter
    @Setter
    private String id;
    @Getter
    @Setter
    @NotNull(message = NOT_NULL)
    private Report report;
    @Getter
    @Setter
    @NotNull(message = NOT_NULL)
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
