package com.danilopereira.remidme.repo.github.client;

import com.danilopereira.remidme.repo.github.dto.GitHubRepositoryInfo;

import java.util.List;

public interface GithubClient {

    List<GitHubRepositoryInfo> getRepositoriesByUser(String username);

}
