package com.danilopereira.remidme.user.dto;

import com.danilopereira.remidme.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
    private String uuid;
    private String firstName;
    private String surname;
    private String position;
    private String githubUrl;

    public UserResponseDTO(User user){
        this.uuid = user.getUuid();
        this.firstName = user.getFirstName();
        this.surname = user.getSurName();
        this.position = user.getPosition();
        this.githubUrl = user.getGithubUrl();
    }

    private static String generateUuidFromId(Long id) {
        return UUID.fromString(id.toString()).toString();
    }
}
