package com.danilopereira.remidme.user.controller;

import com.danilopereira.remidme.user.dto.UserRequest;
import com.danilopereira.remidme.user.dto.UserResponse;
import com.danilopereira.remidme.user.service.UserService;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest){
        return ResponseEntity.ok(userService.createUser(userRequest));
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<UserResponse> getUser(@PathVariable String uuid) {
        return ResponseEntity.ok(userService.getUser(uuid));
    }

    @PutMapping("/uuid")
    public ResponseEntity<UserResponse> updateUser(@PathVariable String uuid, @RequestBody UserRequest userRequest){
        return ResponseEntity.ok(userService.updateUser(uuid, userRequest));
    }

    @DeleteMapping("/uuid")
    public ResponseEntity.HeadersBuilder<?> deleteUser(String uuid){
        userService.deleteUser(uuid);
        return ResponseEntity.noContent();
    }

}
