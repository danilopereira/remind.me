package com.danilopereira.remidme.repo.github.client;

import com.danilopereira.remidme.repo.exception.GeneralException;
import com.danilopereira.remidme.repo.github.dto.GitHubRepositoryInfo;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.junit.Assert.assertFalse;

public class GithubClientImplTest {

    private RestTemplate restTemplate = new RestTemplate();
    private GithubClient githubClient = new GithubClientImpl(restTemplate, "http://localhost:8089", "");

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8089);

    @Test
    public void getRepositoriesByUser() {
        stubFor(get(urlEqualTo("/users/test_user/repos"))
                .withHeader("Accept", equalTo("application/json, application/*+json"))
                .willReturn(aResponse()
                        .withFixedDelay(200)
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("users/repos.json")
                )
        );
        final List<GitHubRepositoryInfo> repos = githubClient.getRepositoriesByUser("test_user");
        assertFalse(repos.isEmpty());
    }

    @Test(expected = GeneralException.class)
    public void getRepositoriesByUserGeneralException() {
        stubFor(get(urlEqualTo("/users/test_user/repos"))
                .withHeader("Accept", equalTo("application/json, application/*+json"))
                .willReturn(aResponse()
                        .withStatus(404)
                )
        );
        githubClient.getRepositoriesByUser("test_user");
    }
}