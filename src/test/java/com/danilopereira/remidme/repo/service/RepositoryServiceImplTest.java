package com.danilopereira.remidme.repo.service;

import com.danilopereira.remidme.repo.dto.RepositoryDTO;
import com.danilopereira.remidme.repo.github.client.GithubClient;
import com.danilopereira.remidme.repo.github.dto.GitHubRepositoryInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

public class RepositoryServiceImplTest {

    private RepositoryService repositoryService;
    private GithubClient githubClient;


    @Before
    public void setUp() {
        githubClient = Mockito.mock(GithubClient.class);
        repositoryService = new RepositoryServiceImpl(githubClient);
    }

    @Test
    public void getRepositories() {
        when(githubClient.getRepositoriesByUser(anyString())).thenReturn(mockRepositories());
        final List<RepositoryDTO> repositories = repositoryService.getRepositories("test_user");
        assertNotNull(repositories);
        assertFalse(repositories.isEmpty());

    }

    private List<GitHubRepositoryInfo> mockRepositories() {
        List<GitHubRepositoryInfo> repositories = null;
        try {
            final GitHubRepositoryInfo[] gitHubRepositoryInfos =
                    new ObjectMapper().readValue(new URL("file:src/test/resources/repos.json"), GitHubRepositoryInfo[].class);
            repositories = Arrays.asList(gitHubRepositoryInfos);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return repositories;
    }
}