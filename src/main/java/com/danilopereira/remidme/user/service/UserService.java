package com.danilopereira.remidme.user.service;

import com.danilopereira.remidme.user.dto.UserRequest;
import com.danilopereira.remidme.user.dto.UserResponse;

public interface UserService {

    UserResponse createUser(UserRequest userRequest);

    UserResponse getUser(String uuid);

    UserResponse updateUser(String uuid, UserRequest userRequest);

    void deleteUser(String uuid);

}
