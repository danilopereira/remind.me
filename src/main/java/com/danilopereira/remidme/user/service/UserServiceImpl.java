package com.danilopereira.remidme.user.service;

import com.danilopereira.remidme.repo.dto.RepositoryDTO;
import com.danilopereira.remidme.repo.service.RepositoryService;
import com.danilopereira.remidme.user.dto.UserRequestDTO;
import com.danilopereira.remidme.user.dto.UserResponseDTO;
import com.danilopereira.remidme.user.exception.UserNotFoundException;
import com.danilopereira.remidme.user.model.User;
import com.danilopereira.remidme.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        User user = userRequestDTO.getEntity();

        user = userRepository.save(user);

        return  new UserResponseDTO(user);
    }

    @Override
    public UserResponseDTO getUser(String uuid) {
        final User user = userRepository.findByUuid(uuid);

        if(user == null){
            return null;
        }

        return new UserResponseDTO(user);
    }

    @Override
    public UserResponseDTO updateUser(String uuid, UserRequestDTO userRequestDTO) throws UserNotFoundException {
        final User user = userRepository.findByUuid(uuid);

        if(user == null){
            throw new UserNotFoundException();
        }

        user.setFirstName(userRequestDTO.getFirstName());
        user.setSurName(userRequestDTO.getSurname());
        user.setGithubUrl(userRequestDTO.getGithubUrl());
        user.setPosition(userRequestDTO.getPosition());
        return new UserResponseDTO(userRepository.save(user));
    }

    @Override
    public void deleteUser(String uuid) throws UserNotFoundException {
        final User user = userRepository.findByUuid(uuid);

        if(user == null){
            throw new UserNotFoundException();
        }

        userRepository.delete(user);
    }

    @Override
    public Page<RepositoryDTO> getRepositories(String uuid, Pageable pageable) throws UserNotFoundException {
        final User user = userRepository.findByUuid(uuid);

        if(user == null){
            throw new UserNotFoundException();
        }

        final String username = getUsername(user);
        final List<RepositoryDTO> repositoriesByUser = repositoryService.getRepositories(username);

        return generatePageableResource(pageable, repositoriesByUser);
    }

    private Page<RepositoryDTO> generatePageableResource(Pageable pageable, List<RepositoryDTO> repositoriesByUser) {
        final int start = (int) pageable.getOffset();
        final int end = (Math.min((start + pageable.getPageSize()), repositoriesByUser.size()));

        return new PageImpl<>(repositoriesByUser.subList(start, end), pageable, repositoriesByUser.size());
    }

    private String getUsername(User user) {
        final String githubUrl = user.getGithubUrl();
        final int beginIndex = githubUrl.lastIndexOf("/");
        return githubUrl.substring(beginIndex);
    }
}
