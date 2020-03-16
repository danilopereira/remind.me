package com.danilopereira.remidme.code.repositories.external.github;

import com.danilopereira.remidme.code.repositories.dto.RepositoryDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GitHubRepositoryInfo extends RepositoryDTO {
    private float id;
    private String name;
    @JsonProperty("full_name")
    private String fullName;
    private Owner owner;
    @JsonProperty("private")
    private boolean privateCheck;
    @JsonProperty("html_url")
    private String htmlUrl;
    private String description;

}