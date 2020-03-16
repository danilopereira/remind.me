package com.danilopereira.remidme.code.repositories.external.client;

import com.danilopereira.remidme.code.repositories.dto.RepositoryDTO;

import java.util.List;

public interface ExternalRepositoryClient {

    List<RepositoryDTO> getRepositoriesByUser(String username);

}
