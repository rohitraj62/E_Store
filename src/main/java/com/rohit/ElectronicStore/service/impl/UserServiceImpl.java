package com.rohit.ElectronicStore.service.impl;

import com.rohit.ElectronicStore.dtos.PageableResponse;
import com.rohit.ElectronicStore.dtos.UserDto;
import com.rohit.ElectronicStore.entities.User;
import com.rohit.ElectronicStore.exceptions.ResourceNotFoundException;
import com.rohit.ElectronicStore.helper.Helper;
import com.rohit.ElectronicStore.repositories.UserRepository;
import com.rohit.ElectronicStore.service.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;







    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);


    @Override
    public UserDto createUser(UserDto userDto) {

        //a universally unique identifier (UUID) is being generated and then converted into a string.
        String userId = UUID.randomUUID().toString();

        //by using object of userDto we are setting or saving the userID
        userDto.setUserId(userId);


        //we are using dtoToEntity method which I created to  userDto type to user and passing the object of userDto.
        User user = dtoToEntity(userDto);

        //here using userRepository we are saving the user type data and storing it to savedUser .
        User savedUser = userRepository.save(user);

        //Again converting to UserDto  because our return type is User DTo
        UserDto newDto = entityToDto(savedUser);
        return newDto;

    }





    @Override
    public UserDto updateUser(UserDto userDto, String userId) {
        //Otional is part of the java.util package and was introduced in Java 8 to help avoid null pointer exceptions and make code more readable when dealing with potentially absent values. It's commonly used as a return type or for wrapping nullable values
        User user=userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User not Found"));
        user.setName(userDto.getName());
        user.setAbout(userDto.getAbout());
        user.setGender(userDto.getGender());
        user.setPassword(userDto.getPassword());
      //  user.setImageName(userDto.getImageName());
        User updatedUser=userRepository.save(user);
        UserDto updatedDto=entityToDto(updatedUser);
        return updatedDto;
    }






    @Override
    public void deleteUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with given id !!"));
        userRepository.delete(user);

    }






    @Override
    public PageableResponse<UserDto> getAllUser(int pageNumber, int pageSize, String sortBy, String sortDir) {

        Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());
//        pageNumber default starts from 0
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

        Page<User> page = userRepository.findAll(pageable);

        PageableResponse<UserDto> response = Helper.getPageableResponse(page, UserDto.class);

        return response;

    }





    @Override
    public UserDto getUserById(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user not found with given id !!"));
        return entityToDto(user);

    }




    @Override
    public UserDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User not found with given email id !!"));
        return entityToDto(user);
    }




    @Override
    public List<UserDto> searchUser(String keyword) {
        List<User> users = userRepository.findByNameContaining(keyword);
        List<UserDto> dtoList = users.stream().map(user -> entityToDto(user)).collect(Collectors.toList());
        return dtoList;
    }





    @Override
    public Optional<User> findUserByEmailOptional(String email) {
        return userRepository.findByEmail(email);
    }

    private User dtoToEntity(UserDto userDto) {
//        User user = User.builder()
//                .userId(userDto.getUserId())
//                .name(userDto.getName())
//                .email(userDto.getEmail())
//                .password(userDto.getPassword())
//                .about(userDto.getAbout())
//                .gender(userDto.getGender())
//                .imageName(userDto.getImageName())
//                .build();

        User user=modelMapper.map(userDto, User.class);
        return user;
    }

    private UserDto entityToDto(User savedUser) {
//        UserDto userDto = UserDto.builder()
//                .userId(savedUser.getUserId())
//                .name(savedUser.getName())
//                .email(savedUser.getEmail())
//                .password(savedUser.getPassword())
//                .about(savedUser.getAbout())
//                .gender(savedUser.getGender())
//                .imageName(savedUser.getImageName())
//                .build();
        UserDto userDto=modelMapper.map(savedUser,UserDto.class);
        return userDto;
    }
}
