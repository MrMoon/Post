package com.moon.squad.model.shared;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

import static com.moon.squad.shared.ApplicationConstants.SEQUENCE;

@Data
@Document (collation = SEQUENCE)
public class SequenceId {
    @Id
    private String id;
    private String sequence;
}
