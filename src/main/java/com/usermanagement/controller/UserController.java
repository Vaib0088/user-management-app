package com.usermanagement.controller;

import com.usermanagement.dto.UserDto;
import com.usermanagement.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(
        name = "CRUD REST APIs for User Resource"
)
@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(
            summary = "Create User REST API",
            description = "Used to create a particular user in database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP STATUS 201 CREATED"
    )
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto user){
        UserDto savedUser = userService.createUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Retrieve User By Id REST API",
            description = "Used to retrieve a particular user in database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP STATUS 200 SUCCESS"
    )
    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId){
        UserDto user = userService.getUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Operation(
            summary = "Retrieve All Users REST API",
            description = "Used to retrieve all users in database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP STATUS 200 SUCCESS"
    )
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUser(){
        List<UserDto> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @Operation(
            summary = "Update User By Id REST API",
            description = "Used to update a particular user in database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP STATUS 200 SUCCESS"
    )
    @PutMapping("{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long userId, @RequestBody UserDto user){
        user.setId(userId);
        UserDto updatedUser = userService.updateUser(user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @Operation(
            summary = "Delete User By Id REST API",
            description = "Used to delete a particular user in database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP STATUS 200 SUCCESS"
    )
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable("id") Long userId){
        userService.deleteById(userId);
        return new ResponseEntity<>("User Deleted Successfully", HttpStatus.OK);
    }



}
