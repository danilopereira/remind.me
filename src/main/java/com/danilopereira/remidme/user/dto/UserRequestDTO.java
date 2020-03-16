package com.danilopereira.remidme.user.dto;

import com.danilopereira.remidme.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequestDTO {

    private String firstName;
    private String surname;
    private String position;
    private String githubUrl;


    public User getEntity(){
        return User.builder()
                .firstName(this.getFirstName())
                .surName(this.getSurname())
                .position(this.getPosition())
                .githubUrl(this.getGithubUrl())
                .build();
    }

}
