package com.maksimbalashov.spring.service;

import com.maksimbalashov.spring.dao.UserRepository;
import com.maksimbalashov.spring.domain.User;
import com.maksimbalashov.spring.dto.UserDTO;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Map;
import java.util.UUID;

public class UserServiceImplTest {
    private UserServiceImpl userService;
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    @BeforeAll
    static void beforeAll(){
        System.out.println("Before All Tests");
    }
    @BeforeEach
    void setUp(){
        System.out.println("Before each test");
        passwordEncoder = Mockito.mock(PasswordEncoder.class);
        userRepository = Mockito.mock(UserRepository.class);

        userService = new UserServiceImpl(userRepository, passwordEncoder);
    }
    @AfterEach
    void afterEach(){
        System.out.println("After Each Test");
    }
    @AfterAll
    static void afterAll(){
        System.out.println("After All Test");
    }

    @Test
    void checkFindByName(){
        String name = "petr";
        User expectedUser = User.builder().id(1L).name(name).build();

        Mockito.when(userRepository.findFirstByName(Mockito.anyString())).thenReturn(expectedUser);

        User actualUser = userService.findByName(name);

        Assertions.assertNotNull(actualUser);
        Assertions.assertEquals(expectedUser, actualUser);

    }

    @Test
    void checkFindByNameExact(){
        String name = "petr";
        User expectedUser = User.builder().id(1L).name(name).build();

        Mockito.when(userRepository.findFirstByName(Mockito.eq(name))).thenReturn(expectedUser);

        User actualUser = userService.findByName(name);
        User rndUser = userService.findByName(UUID.randomUUID().toString());

        Assertions.assertNotNull(actualUser);
        Assertions.assertEquals(expectedUser, actualUser);

        Assertions.assertNull(rndUser);
    }

    @Test
    void checkSaveIncorrectPassword(){
        UserDTO userDTO = UserDTO.builder()
                .password("password")
                .matchingPassword("another")
                .build();

        Assertions.assertThrows(RuntimeException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                userService.save(userDTO);
            }
        });
    }

    @Test
    void checkSave(){
        UserDTO userDTO = UserDTO.builder()
                .username("name")
                .email("email")
                .password("password")
                .matchingPassword("password")
                .build();
        Mockito.when(passwordEncoder.encode(Mockito.anyString())).thenReturn("password");

        boolean result = userService.save(userDTO);

        Assertions.assertTrue(result);
        Mockito.verify(passwordEncoder).encode(Mockito.anyString());
        Mockito.verify(userRepository).save(Mockito.any());
    }

}
