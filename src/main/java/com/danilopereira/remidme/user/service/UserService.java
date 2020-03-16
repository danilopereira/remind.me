package com.danilopereira.remidme.user.service;

import com.danilopereira.remidme.code.repositories.dto.RepositoryDTO;
import com.danilopereira.remidme.user.dto.UserRequestDTO;
import com.danilopereira.remidme.user.dto.UserResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    UserResponseDTO createUser(UserRequestDTO userRequestDTO);

    UserResponseDTO getUser(String uuid);

    UserResponseDTO updateUser(String uuid, UserRequestDTO userRequestDTO);

    void deleteUser(String uuid);

    Page<RepositoryDTO> getRepositories(String uuid, Pageable pageable);
}
