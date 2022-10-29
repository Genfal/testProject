package ru.project.test.v2.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.project.test.v2.model.User;
import ru.project.test.v2.repository.UserRepository;
import ru.project.test.v2.service.UserService;

import java.util.Optional;

/**
 * @author TorstendasTost
 * @since 27.10.2022
 */
class UserServiceTest {
    private static final Long ID = 1L;

    private static final User USER_WO_ID = User.builder().id(ID).build();

    private static final User USER_WITH_ID = User.builder().build();

    private static final Optional<User> OPTIONAL_USER = Optional.ofNullable(USER_WITH_ID);

    private UserService userService;

    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository = Mockito.mock(UserRepository.class);
        userService = new UserService(userRepository);
    }

    @Test
    void saveUser() {
        Mockito.when(userRepository.save(USER_WO_ID)).thenReturn(USER_WITH_ID);

        Assertions.assertEquals(userRepository.save(USER_WO_ID), (USER_WITH_ID));

        Mockito.verify(userRepository).save(USER_WO_ID);
    }

    @Test
    void saveUserRuntimeException() {
        Mockito.when(userRepository.save(USER_WO_ID)).thenThrow(RuntimeException.class);

        Assertions.assertThrows(RuntimeException.class, () -> userRepository.save(USER_WO_ID));

        Mockito.verify(userRepository).save(USER_WO_ID);
    }

    @Test
    void getUser() {
        Mockito.when(userRepository.findById(ID)).thenReturn(OPTIONAL_USER);

        Assertions.assertEquals(userRepository.findById(ID), (OPTIONAL_USER));

        Mockito.verify(userRepository).findById(ID);
    }

    @Test
    void getUserRuntimeException() {
        Mockito.when(userRepository.findById(ID)).thenThrow(RuntimeException.class);

        Assertions.assertThrows(RuntimeException.class, () -> userRepository.findById(ID));

        Mockito.verify(userRepository).findById(ID);
    }

    @Test
    void deleteUser() {
        Mockito.when(userRepository.findById(ID)).thenReturn(OPTIONAL_USER);

        userService.deleteUser(ID);

        Mockito.verify(userRepository).deleteById(ID);
    }
}