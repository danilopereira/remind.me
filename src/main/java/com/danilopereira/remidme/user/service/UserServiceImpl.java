package com.danilopereira.remidme.user.service;

import com.danilopereira.remidme.repo.dto.RepositoryDTO;
import com.danilopereira.remidme.repo.service.RepositoryService;
import com.danilopereira.remidme.user.dto.UserRequestDTO;
import com.danilopereira.remidme.user.dto.UserResponseDTO;
import com.danilopereira.remidme.user.exception.UserAlreadyExistsException;
import com.danilopereira.remidme.user.exception.UserNotFoundException;
import com.danilopereira.remidme.user.model.User;
import com.danilopereira.remidme.user.repository.UserRepository;
import com.danilopereira.remidme.user.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RepositoryService repositoryService;

    public UserServiceImpl(@Autowired UserRepository userRepository,
                           @Autowired RepositoryService repositoryService){
        this.userRepository = userRepository;
        this.repositoryService = repositoryService;
    }

    @Override
    @Transactional
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) throws UserAlreadyExistsException {
        final User userByGithubUrl = userRepository.findByGithubUrl(userRequestDTO.getGithubUrl());

        if(userByGithubUrl != null){
            throw new UserAlreadyExistsException();
        }

        User user = userRequestDTO.getEntity();
        user = userRepository.save(user);

        return  new UserResponseDTO(user);
    }

    @Override
    public UserResponseDTO getUser(String uuid) {
        try {
            final User user = getValidUser(uuid);
            return new UserResponseDTO(user);
        } catch (UserNotFoundException e) {
            return null;
        }
    }

    @Override
    public UserResponseDTO updateUser(String uuid, UserRequestDTO userRequestDTO) throws UserNotFoundException {
        final User user = getValidUser(uuid);

        user.setFirstName(userRequestDTO.getFirstName());
        user.setSurName(userRequestDTO.getSurname());
        user.setGithubUrl(userRequestDTO.getGithubUrl());
        user.setPosition(userRequestDTO.getPosition());

        return new UserResponseDTO(userRepository.save(user));
    }

    @Override
    public void deleteUser(String uuid) throws UserNotFoundException {
        final User user = getValidUser(uuid);
        userRepository.delete(user);
    }

    @Override
    public Page<RepositoryDTO> getRepositories(String uuid, Pageable pageable) throws UserNotFoundException {
        final User user = getValidUser(uuid);

        final String username = UserUtils.extractUserName(user.getGithubUrl());
        final List<RepositoryDTO> repositoriesByUser = repositoryService.getRepositories(username);

        return UserUtils.generatePageableResource(pageable, repositoriesByUser);
    }

    private User getValidUser(String uuid) throws UserNotFoundException {
        final User user = userRepository.findByUuid(uuid);

        if (user == null) {
            throw new UserNotFoundException();
        }
        return user;
    }
}
