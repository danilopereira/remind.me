package com.danilopereira.remidme.user.service;

import com.danilopereira.remidme.user.dto.UserRequest;
import com.danilopereira.remidme.user.dto.UserResponse;
import com.danilopereira.remidme.user.entity.User;
import com.danilopereira.remidme.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(@Autowired UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserResponse createUser(UserRequest userRequest) {
        User user = userRequest.getEntity();

        user = userRepository.save(user);

        return  new UserResponse(user);
    }

    @Override
    public UserResponse getUser(String uuid) {
        return new UserResponse(userRepository.findByUuid(uuid));
    }

    @Override
    public UserResponse updateUser(String uuid, UserRequest userRequest) {
        final User user = userRepository.findByUuid(uuid);
        user.setFirstName(userRequest.getFirstName());
        user.setSurName(userRequest.getSurname());
        user.setGithubUrl(userRequest.getGithubUrl());
        user.setPosition(userRequest.getPosition());
        return new UserResponse(userRepository.save(user));
    }

    @Override
    public void deleteUser(String uuid) {
        userRepository.delete(userRepository.findByUuid(uuid));
    }
}
