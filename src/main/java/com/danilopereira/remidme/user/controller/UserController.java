package com.danilopereira.remidme.user.controller;

import com.danilopereira.remidme.user.dto.UserRequestDTO;
import com.danilopereira.remidme.user.dto.UserResponseDTO;
import com.danilopereira.remidme.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO userRequestDTO){
        return ResponseEntity.ok(userService.createUser(userRequestDTO));
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable String uuid) {
        return ResponseEntity.ok(userService.getUser(uuid));
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable String uuid, @RequestBody UserRequestDTO userRequestDTO){
        return ResponseEntity.ok(userService.updateUser(uuid, userRequestDTO));
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity.HeadersBuilder<?> deleteUser(String uuid){
        userService.deleteUser(uuid);
        return ResponseEntity.noContent();
    }



}
