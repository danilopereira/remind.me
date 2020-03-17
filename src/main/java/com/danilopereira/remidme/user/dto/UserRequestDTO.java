package com.danilopereira.remidme.user.dto;

import com.danilopereira.remidme.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequestDTO {

    @NotNull
    @NotBlank
    private String firstName;
    @NotNull
    @NotBlank
    private String surname;
    @NotNull
    @NotBlank
    private String position;
    @NotNull
    @NotBlank
    private String githubUrl;


    public User getEntity(){
        return User.builder()
                .firstName(this.getFirstName())
                .surName(this.getSurname())
                .position(this.getPosition())
                .githubUrl(this.getGithubUrl())
                .uuid(UUID.randomUUID().toString())
                .build();
    }

}
