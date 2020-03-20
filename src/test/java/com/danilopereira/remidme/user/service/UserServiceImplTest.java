package com.danilopereira.remidme.user.service;

import com.danilopereira.remidme.repo.dto.RepositoryDTO;
import com.danilopereira.remidme.repo.service.RepositoryService;
import com.danilopereira.remidme.user.dto.UserRequestDTO;
import com.danilopereira.remidme.user.dto.UserResponseDTO;
import com.danilopereira.remidme.user.exception.UserAlreadyExistsException;
import com.danilopereira.remidme.user.exception.UserNotFoundException;
import com.danilopereira.remidme.user.model.User;
import com.danilopereira.remidme.user.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

public class UserServiceImplTest {

    private UserRepository userRepository;
    private RepositoryService repositoryService;
    private UserService userService;

    @Before
    public void setUp() throws Exception {
        userRepository = Mockito.mock(UserRepository.class);
        repositoryService = Mockito.mock(RepositoryService.class);

        userService = new UserServiceImpl(userRepository, repositoryService);

        when(userRepository.findByUuid(anyString())).thenReturn(mockUser());
        when(userRepository.save(any())).thenReturn(mockUser());
        when(userRepository.findByGithubUrl(anyString())).thenReturn(null);
        when(userRepository.findByUuid(anyString())).thenReturn(mockUser());
    }

    @Test
    public void testCreateUser() throws UserAlreadyExistsException {
        final UserResponseDTO userResponse = userService.createUser(mockUserRequestDTO());
        assertNotNull(userResponse);
    }

    @Test(expected = UserAlreadyExistsException.class)
    public void testCreateUserUsersAlreadyExists() throws UserAlreadyExistsException {
        when(userRepository.findByGithubUrl(anyString())).thenReturn(mockUser());
        userService.createUser(mockUserRequestDTO());
    }

    @Test
    public void testGetUser() {
        final UserResponseDTO userResponse = userService.getUser(UUID.randomUUID().toString());
        assertNotNull(userResponse);
    }

    @Test
    public void testUpdateUser() throws UserNotFoundException {
        final UserResponseDTO userResponse = userService.updateUser(UUID.randomUUID().toString(), mockUserRequestDTO());
        assertNotNull(userResponse);
    }

    @Test(expected = UserNotFoundException.class)
    public void testUpdateUserUserNotFound() throws UserNotFoundException {
        when(userRepository.findByUuid(anyString())).thenReturn(null);
        userService.updateUser(UUID.randomUUID().toString(), mockUserRequestDTO());
    }

    @Test
    public void testDeleteUser() throws UserNotFoundException {
        final String uuid = UUID.randomUUID().toString();
        userService.deleteUser(uuid);
        when(userRepository.findByUuid(uuid)).thenReturn(null);
        final UserResponseDTO userResponse = userService.getUser(uuid);
        assertNull(userResponse);
    }

    @Test(expected = UserNotFoundException.class)
    public void testDelettUserUserNotFound() throws UserNotFoundException {
        when(userRepository.findByUuid(anyString())).thenReturn(null);
        userService.deleteUser(UUID.randomUUID().toString());
    }

    @Test
    public void testGetRepositories() throws UserNotFoundException {
        when(repositoryService.getRepositories(anyString())).thenReturn(mockRepositories());
        Pageable pageable = PageRequest.of(0, 1, Sort.unsorted());

        final Page<RepositoryDTO> repositories = userService.getRepositories(UUID.randomUUID().toString(), pageable);

        assertNotNull(repositories.getContent());
        assertFalse(repositories.getContent().isEmpty());
    }

    private List<RepositoryDTO> mockRepositories() {
        final RepositoryDTO repositoryDTO = RepositoryDTO.builder()
                .name("repo_test")
                .language("Java")
                .url("http://github.com/testuser/repo_test")
                .build();
        return Collections.singletonList(repositoryDTO);
    }

    private UserRequestDTO mockUserRequestDTO() {
        return UserRequestDTO.builder()
                .firstName("Test")
                .surname("User")
                .githubUrl("http://github.com/testuser")
                .position("Test Engineer")
                .build();
    }

    private User mockUser() {
        return User.builder()
                .uuid(UUID.randomUUID().toString())
                .firstName("Test")
                .surName("User")
                .id(1l)
                .githubUrl("http://github.com/testuser")
                .position("Test Engineer")
                .build();
    }
}