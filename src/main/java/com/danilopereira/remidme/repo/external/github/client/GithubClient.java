package com.danilopereira.remidme.repo.external.github.client;

import com.danilopereira.remidme.repo.external.github.dto.GitHubRepositoryInfo;

import java.util.List;

public interface GithubClient {

    List<GitHubRepositoryInfo> getRepositoriesByUser(String username);

}
