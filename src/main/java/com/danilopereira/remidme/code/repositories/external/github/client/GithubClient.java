package com.danilopereira.remidme.code.repositories.external.github.client;

import com.danilopereira.remidme.code.repositories.dto.RepositoryDTO;
import com.danilopereira.remidme.code.repositories.external.client.ExternalRepositoryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class GithubClient implements ExternalRepositoryClient {

    private static final String URI_USER_REPOS = "/users/%s/repos";

    private RestTemplate restTemplate;
    private String githubAPIUrl;

    public GithubClient(@Autowired RestTemplate restTemplate,
                        @Value("${github.api.url}") String githubAPIUrl){
        this.restTemplate = restTemplate;
        this.githubAPIUrl = githubAPIUrl;
    }

    @Override
    public List<RepositoryDTO> getRepositoriesByUser(String username) {
        final String endpoint = String.format(URI_USER_REPOS, username);
        final String getRepositoriesUrl = githubAPIUrl.concat(endpoint);
        final RepositoryDTO[] repositories = restTemplate.getForObject(getRepositoriesUrl, RepositoryDTO[].class);
        return Arrays.asList(repositories);
    }
}
