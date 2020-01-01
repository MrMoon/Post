package com.moon.squad.model.shared;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

import static com.moon.squad.shared.Constants.SEQUENCE;

@Document(collation = SEQUENCE)
public class SequenceId {
    @Id
    @Setter
    @Getter
    private String id;
    @Setter
    @Getter
    private String sequence;
}
