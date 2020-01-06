package com.moon.squad.model.agile;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Optional;

import lombok.Data;

@Data
@Document
public class Evaluation implements Serializable {

    @Id
    private String id;
    private Optional<Long> progress, results;

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
