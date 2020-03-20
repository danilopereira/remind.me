package com.danilopereira.remidme.repo.github.client;

import com.danilopereira.remidme.repo.exception.GeneralException;
import com.danilopereira.remidme.repo.github.dto.GitHubRepositoryInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class GithubClientImpl implements GithubClient {

    private static final String URI_USER_REPOS = "/users/%s/repos";

    private RestTemplate restTemplate;
    private String githubAPIUrl;
    private String accessToken;

    public GithubClientImpl(@Autowired RestTemplate restTemplate,
                            @Value("${github.api.url}") String githubAPIUrl,
                            @Value("${github.api.access_token}") String accessToken){
        this.restTemplate = restTemplate;
        this.githubAPIUrl = githubAPIUrl;
        this.accessToken = accessToken;
    }

    @Override
    public List<GitHubRepositoryInfo> getRepositoriesByUser(String username) throws GeneralException {
        try{
            final String endpoint = String.format(URI_USER_REPOS, username);
            final String getRepositoriesUrl = githubAPIUrl.concat(endpoint);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Bearer "+ accessToken);
            final ResponseEntity<GitHubRepositoryInfo[]> response = restTemplate.exchange(getRepositoriesUrl, HttpMethod.GET, new HttpEntity<>("parameters", headers), GitHubRepositoryInfo[].class);
            return Arrays.asList(response.getBody());
        }catch (RuntimeException e){
            throw new GeneralException();
        }

    }
}
