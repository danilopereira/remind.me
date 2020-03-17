package com.danilopereira.remidme.repo.service;

import com.danilopereira.remidme.repo.dto.RepositoryDTO;

import java.util.List;

public interface RepositoryService {

    List<RepositoryDTO> getRepositories(String username);
}
