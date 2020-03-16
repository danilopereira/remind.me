package com.danilopereira.remidme.code.repositories.external.github;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Owner {
 private String login;
 private float id;
 @JsonProperty("avatar_url")
 private String avatarUrl;
 @JsonProperty("gravatar_id")
 private String gravatarId;
 private String url;
}