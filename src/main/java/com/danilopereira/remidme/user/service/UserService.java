package com.danilopereira.remidme.user.service;

import com.danilopereira.remidme.repo.dto.RepositoryDTO;
import com.danilopereira.remidme.user.dto.UserRequestDTO;
import com.danilopereira.remidme.user.dto.UserResponseDTO;
import com.danilopereira.remidme.user.exception.UserAlreadyExistsException;
import com.danilopereira.remidme.user.exception.UserNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    UserResponseDTO createUser(UserRequestDTO userRequestDTO) throws UserAlreadyExistsException;

    UserResponseDTO getUser(String uuid);

    UserResponseDTO updateUser(String uuid, UserRequestDTO userRequestDTO) throws UserNotFoundException;

    void deleteUser(String uuid) throws UserNotFoundException;

    Page<RepositoryDTO> getRepositories(String uuid, Pageable pageable) throws UserNotFoundException;
}
