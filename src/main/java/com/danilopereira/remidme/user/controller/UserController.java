package com.danilopereira.remidme.user.controller;

import com.danilopereira.remidme.repo.dto.RepositoryDTO;
import com.danilopereira.remidme.user.dto.UserRequestDTO;
import com.danilopereira.remidme.user.dto.UserResponseDTO;
import com.danilopereira.remidme.user.exception.UserAlreadyExistsException;
import com.danilopereira.remidme.user.exception.UserNotFoundException;
import com.danilopereira.remidme.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody @Valid UserRequestDTO userRequestDTO, UriComponentsBuilder uriComponentsBuilder) throws UserAlreadyExistsException {
        final UserResponseDTO userResponse = userService.createUser(userRequestDTO);
        final UriComponents uriComponents = uriComponentsBuilder.path("/users/{uuid}").buildAndExpand(userResponse.getUuid());
        return ResponseEntity.created(uriComponents.toUri()).body(userResponse);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable String uuid) {
        final UserResponseDTO user = userService.getUser(uuid);

        if(user == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(user);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable String uuid, @RequestBody UserRequestDTO userRequestDTO) throws UserNotFoundException {
        return ResponseEntity.ok(userService.updateUser(uuid, userRequestDTO));
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity.HeadersBuilder<?> deleteUser(String uuid) throws UserNotFoundException {
        userService.deleteUser(uuid);
        return ResponseEntity.noContent();
    }

    @GetMapping("/{uuid}/repositories")
    public ResponseEntity<Page<RepositoryDTO>> getRepositories(@PathVariable String uuid, @PageableDefault(size = 10) @SortDefault(sort = "name", direction = Sort.Direction.ASC) Pageable pageable) throws UserNotFoundException {
        return ResponseEntity.ok(userService.getRepositories(uuid, pageable));
    }



}
