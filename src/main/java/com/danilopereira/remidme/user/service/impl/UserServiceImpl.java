package com.danilopereira.remidme.user.service.impl;

import com.danilopereira.remidme.user.dto.UserRequestDTO;
import com.danilopereira.remidme.user.dto.UserResponseDTO;
import com.danilopereira.remidme.user.entity.User;
import com.danilopereira.remidme.user.repository.UserRepository;
import com.danilopereira.remidme.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(@Autowired UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        User user = userRequestDTO.getEntity();

        user = userRepository.save(user);

        return  new UserResponseDTO(user);
    }

    @Override
    public UserResponseDTO getUser(String uuid) {
        return new UserResponseDTO(userRepository.findByUuid(uuid));
    }

    @Override
    public UserResponseDTO updateUser(String uuid, UserRequestDTO userRequestDTO) {
        final User user = userRepository.findByUuid(uuid);
        user.setFirstName(userRequestDTO.getFirstName());
        user.setSurName(userRequestDTO.getSurname());
        user.setGithubUrl(userRequestDTO.getGithubUrl());
        user.setPosition(userRequestDTO.getPosition());
        return new UserResponseDTO(userRepository.save(user));
    }

    @Override
    public void deleteUser(String uuid) {
        userRepository.delete(userRepository.findByUuid(uuid));
    }
}
