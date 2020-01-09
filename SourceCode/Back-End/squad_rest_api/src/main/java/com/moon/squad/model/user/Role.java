package com.moon.squad.model.user;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

import lombok.Data;

@Document
@Data
public class Role implements Serializable {
    @Id
    private String id;
    @Indexed (unique = true, direction = IndexDirection.DESCENDING, dropDups = true)
    private String role;
}
