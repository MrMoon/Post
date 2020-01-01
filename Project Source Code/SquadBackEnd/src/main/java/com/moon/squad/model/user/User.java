package com.moon.squad.model.user;

import com.moon.squad.shared.Constants;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Document
public class User {
    @Id
    @Getter
    @Setter
    private String id;
    @NotBlank(message = Constants.NOT_BLANK)
    @Getter
    @Setter
    private String name;
    @Email
    @NotBlank
    @Getter
    @Setter
    @NotBlank(message = Constants.NOT_BLANK)
    private String email;
    @Length(min = 6 , max = 32)
    @Getter
    @Setter
    private String password;
    @NotBlank
    @Getter
    @Setter
    private String jobTitle;
    @NumberFormat
    @NotBlank
    @NotBlank(message = Constants.NOT_BLANK)
    @Length(min = 10)
    @Getter
    @Setter
    private String phoneNumber;
    @NotBlank(message = Constants.NOT_BLANK)
    @Getter
    @Setter
    private String type;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("id='").append(id).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", jobTitle='").append(jobTitle).append('\'');
        sb.append(", phoneNumber='").append(phoneNumber).append('\'');
        sb.append(", type='").append(type).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
