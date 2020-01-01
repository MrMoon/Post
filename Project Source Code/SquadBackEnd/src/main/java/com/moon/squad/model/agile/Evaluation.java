package com.moon.squad.model.agile;

import org.springframework.data.annotation.Id;

import java.util.Optional;

import lombok.Getter;
import lombok.Setter;

public class Evaluation {

    @Id
    @Getter
    @Setter
    private String id;
    @Getter
    @Setter
    private Optional<Long> progress , results;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Evaluation{");
        sb.append("id='").append(id).append('\'');
        sb.append(", progress=").append(progress);
        sb.append(", results=").append(results);
        sb.append('}');
        return sb.toString();
    }
}
