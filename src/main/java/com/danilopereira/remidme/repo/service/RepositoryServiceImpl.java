package com.danilopereira.remidme.repo.service;

import com.danilopereira.remidme.repo.dto.RepositoryDTO;
import com.danilopereira.remidme.repo.github.client.GithubClient;
import com.danilopereira.remidme.repo.github.dto.GitHubRepositoryInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RepositoryServiceImpl implements RepositoryService {

    private GithubClient githubClient;

    public RepositoryServiceImpl(@Autowired GithubClient githubClient){
        this.githubClient = githubClient;
    }

    @Override
    public List<RepositoryDTO> getRepositories(String username) {
        final List<GitHubRepositoryInfo> repositoriesByUser = githubClient.getRepositoriesByUser(username);

        final List<RepositoryDTO> repositories = repositoriesByUser.stream().map(gitHubRepositoryInfo -> RepositoryDTO.builder()
                .language(gitHubRepositoryInfo.getLanguage())
                .name(gitHubRepositoryInfo.getName())
                .url(gitHubRepositoryInfo.getHtmlUrl())
                .build()).collect(Collectors.toList());

        return repositories;
    }
}
