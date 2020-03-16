package com.danilopereira.remidme.code.repositories.service;

import com.danilopereira.remidme.code.repositories.dto.RepositoryDTO;

import java.util.List;

public interface RepositoryService {

    List<RepositoryDTO> getRepositories(String username);
}
