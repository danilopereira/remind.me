package com.danilopereira.remidme.user.service;

import com.danilopereira.remidme.user.dto.UserRequestDTO;
import com.danilopereira.remidme.user.dto.UserResponseDTO;

public interface UserService {

    UserResponseDTO createUser(UserRequestDTO userRequestDTO);

    UserResponseDTO getUser(String uuid);

    UserResponseDTO updateUser(String uuid, UserRequestDTO userRequestDTO);

    void deleteUser(String uuid);

}
