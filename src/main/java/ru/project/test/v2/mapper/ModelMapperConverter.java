package ru.project.test.v2.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.project.test.v2.dto.UserDto;
import ru.project.test.v2.model.Email;
import ru.project.test.v2.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @author TorstendasTost
 * @since 25.10.2022
 */
@Component
public class ModelMapperConverter implements ConverterToUser<UserDto, User> {
    private final ModelMapper modelMapper;

    public ModelMapperConverter() {
        modelMapper = new ModelMapper();
    }

    @Override
    public User convertToUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        List<Email> emailsList = new ArrayList<>();
        for (String el : userDto.getEmail()) {
            Email email = Email.builder().userEmail(el).user(user).build();
            emailsList.add(email);
        }
        user.setEmail(emailsList);
        return user;
    }

    @Override
    public UserDto revert(User user) {
        UserDto userDto = modelMapper.map(user, UserDto.class);
        List<String> emailList = new ArrayList<>();
        for (Email el : user.getEmail()){
            emailList.add(el.getUserEmail());
        }
        userDto.setEmail(emailList);
        return userDto;
    }
}
