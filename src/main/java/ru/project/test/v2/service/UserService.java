package ru.project.test.v2.service;

import com.sun.istack.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.project.test.v2.dto.UserDto;
import ru.project.test.v2.mapper.ModelMapperConverter;
import ru.project.test.v2.model.User;
import ru.project.test.v2.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

/**
 * @author TorstendasTost
 * @since 25.10.2022
 */
@Slf4j
@Service
public class UserService {
    private final UserRepository userRepository;

    private final ModelMapperConverter modelMapperConverter;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        modelMapperConverter = new ModelMapperConverter();
    }

    @Transactional
    public User saveUser(UserDto userDto) {
        User user = modelMapperConverter.convertToUser(userDto);
        User savedUser = userRepository.save(user);
        log.info("User с id: {}, сохранён", savedUser.getId());
        return savedUser;
    }

    @Transactional
    public List<UserDto> saveUsers(List<UserDto> usersDto) {
        List<User> users = modelMapperConverter.convertToUserList(usersDto);
        List<User> savedUsers = (List<User>) userRepository.saveAll(users);
        List<UserDto> savedUserDto = modelMapperConverter.revertToUserDtoList(savedUsers);
        savedUsers.forEach(System.out::println);
        log.info("Сохранены");
        return savedUserDto;
    }

    @Transactional
    public User getUser(Long id) {
        if (id == null) {
            throw new RuntimeException();
        }
        User user = userRepository
                .findById(id)
                .orElseThrow(NullPointerException::new);
        log.info("user с id: {} был получен", user.getId());
        return user;
    }

    @Transactional
    public User deleteUser(Long id) {
        User user = getUser(id);
        userRepository.deleteById(id);
        log.info("user с id: {} был удалён", user.getId());
        return user;
    }

    @Transactional
    public List<User> testQuery1(@NotNull String country) {
        List<User> users = userRepository.testQuery1(country);
        log.info("users страна: {}",country);
        return users;
    }

    @Transactional
    public List<User> testQuery2(){
        return userRepository.testQuery2();
    }

    @Transactional
    public List testQuery3(){
        return userRepository.testQuery3();
    }

    @Transactional
    public Map<String,Integer> testQuerySql(){
        return userRepository.testQuerySqlRequest();
    }
}
