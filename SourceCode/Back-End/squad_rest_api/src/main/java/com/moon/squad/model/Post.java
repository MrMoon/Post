package com.moon.squad.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Document
public class Post implements Serializable {
    @Id
    @Setter
    @Getter
    private String id;
    @Getter
    private String date = new SimpleDateFormat("E, DD MMM YYYY HH:mm:ss z\t").format(new Date());
    @Setter
    @Getter
    private String description;
    @Setter
    @Getter
    private String name;
    @Setter
    @Getter
    private String author;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Post{");
        sb.append("id='").append(id).append('\'');
        sb.append(", date='").append(date).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", author='").append(author).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
