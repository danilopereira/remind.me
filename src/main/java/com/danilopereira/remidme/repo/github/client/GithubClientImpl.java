package com.danilopereira.remidme.repo.github.client;

import com.danilopereira.remidme.repo.github.dto.GitHubRepositoryInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class GithubClientImpl implements GithubClient {

    private static final String URI_USER_REPOS = "/users/%s/repos";

    private RestTemplate restTemplate;
    private String githubAPIUrl;

    public GithubClientImpl(@Autowired RestTemplate restTemplate,
                            @Value("${github.api.url}") String githubAPIUrl){
        this.restTemplate = restTemplate;
        this.githubAPIUrl = githubAPIUrl;
    }

    @Override
    public List<GitHubRepositoryInfo> getRepositoriesByUser(String username) {
        final String endpoint = String.format(URI_USER_REPOS, username);
        final String getRepositoriesUrl = githubAPIUrl.concat(endpoint);
        final GitHubRepositoryInfo[] repositories = restTemplate.getForObject(getRepositoriesUrl, GitHubRepositoryInfo[].class);
        return Arrays.asList(repositories);
    }
}
