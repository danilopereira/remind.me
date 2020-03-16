package com.danilopereira.remidme.user.service;

import com.danilopereira.remidme.code.repositories.dto.RepositoryDTO;
import com.danilopereira.remidme.code.repositories.external.client.ExternalRepositoryClient;
import com.danilopereira.remidme.user.dto.UserRequestDTO;
import com.danilopereira.remidme.user.dto.UserResponseDTO;
import com.danilopereira.remidme.user.model.User;
import com.danilopereira.remidme.user.repository.UserRepository;
import com.danilopereira.remidme.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private ExternalRepositoryClient externalRepositoryClient;

    public UserServiceImpl(@Autowired UserRepository userRepository,
                           @Autowired ExternalRepositoryClient externalRepositoryClient){
        this.userRepository = userRepository;
        this.externalRepositoryClient = externalRepositoryClient;
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

    @Override
    public Page<RepositoryDTO> getRepositories(String uuid, Pageable pageable) {
        final User user = userRepository.findByUuid(uuid);

        if(user == null){
            //TODO user not found
        }
        final String username = getUsername(user);
        final List<RepositoryDTO> repositoriesByUser = externalRepositoryClient.getRepositoriesByUser(username);

        return generatePageableResource(pageable, repositoriesByUser);
    }

    private Page<RepositoryDTO> generatePageableResource(Pageable pageable, List<RepositoryDTO> repositoriesByUser) {
        final int start = (int) pageable.getOffset();
        final int end = (int) (Math.min((start + pageable.getPageSize()), repositoriesByUser.size()));

        return new PageImpl<RepositoryDTO>(repositoriesByUser.subList(start, end), pageable, repositoriesByUser.size());
    }

    private String getUsername(User user) {
        final String githubUrl = user.getGithubUrl();
        final int beginIndex = githubUrl.lastIndexOf("/");
        return githubUrl.substring(beginIndex);
    }
}
