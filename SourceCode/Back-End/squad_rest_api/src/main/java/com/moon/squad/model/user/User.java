package com.moon.squad.model.user;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.NumberFormat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

import static com.moon.squad.shared.ApplicationConstants.NOT_BLANK;

@Data
@Document
public class User implements Serializable {
    @Id
    private String id;
    @NotBlank (message = NOT_BLANK)
    private String name;
    @Email
    @NotBlank
    @NotBlank (message = NOT_BLANK)
    private String email;
    @Length (min = 6, max = 32)
    private String password;
    @NotBlank
    private String jobTitle;
    @NumberFormat
    @NotBlank
    @NotBlank (message = NOT_BLANK)
    @Length (min = 10)
    private String phoneNumber;
    @NotBlank (message = NOT_BLANK)
    private String type;
    private ArrayList<User> friends;
    private boolean enabled;
    @DBRef
    private Set<Role> roles;

    public User() {
        friends = new ArrayList<>();
    }

    public void addFriends(User user) {
        if(friends.stream().noneMatch(user1 -> user1.getId().equals(user.getId()))) friends.add(user);
    }
}
