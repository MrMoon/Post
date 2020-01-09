package com.moon.squad.model.agile;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Optional;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
@Document
public class Evaluation implements Serializable {

    @Id
    private String id;
    @NotNull
    @NotBlank
    private String progress, results;
}
