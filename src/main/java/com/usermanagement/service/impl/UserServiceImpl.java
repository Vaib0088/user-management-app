package com.usermanagement.service.impl;

import com.usermanagement.dto.UserDto;
import com.usermanagement.entity.User;
import com.usermanagement.exception.EmailAlreadyExistException;
import com.usermanagement.exception.ResourceNotFoundException;
import com.usermanagement.mapper.UserMapper;
import com.usermanagement.repository.UserRepository;
import com.usermanagement.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        //User user = UserMapper.mapToUser(userDto);
        Optional<User> optionalUser= userRepository.findByEmail(userDto.getEmail());

        if(optionalUser.isPresent()){
            throw  new EmailAlreadyExistException("Email Already Exist");
        }

        User user = modelMapper.map(userDto, User.class);
        User savedUser = userRepository.save(user);

        //return UserMapper.mapToUserDto(savedUser);
        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("user", "id", id)
        );
        //return UserMapper.mapToUserDto(user);
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        //return users.stream().map(UserMapper::mapToUserDto).collect(Collectors.toList());
        return users.stream().map((user)->(modelMapper.map(user, UserDto.class))).collect(Collectors.toList());

    }

    @Override
    public UserDto updateUser(UserDto user) {
        User existinguser = userRepository.findById(user.getId()).orElseThrow(
                () -> new ResourceNotFoundException("user", "id", user.getId())
        );
        existinguser.setFirstName(user.getFirstName());
        existinguser.setLastName(user.getLastName());
        existinguser.setEmail(user.getEmail());
        User updatedUser = userRepository.save(existinguser);
        //return UserMapper.mapToUserDto(updatedUser);
        return modelMapper.map(updatedUser, UserDto.class);
    }

    @Override
    public void deleteById(Long id) {
        User existinguser = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("user", "id", id)
        );
        userRepository.deleteById(id);
    }
}
