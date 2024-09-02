package ru.skypro.homework.controller;

import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AuthService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
public class AuthControllerIntegrationTests {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository usersRepository;

    @Container
    private static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:latest")
            .withUsername("postgres")
            .withPassword("73aberiv");

    @DynamicPropertySource
    static void postgresProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }


    private void addToDb() {
        usersRepository.deleteAll();
        User user = new User(1,
                "user@gmail.com",
                "ivan",
                "ivanov",
                "+7(777)777-77-77",
                Role.USER, null,
                "$2a$10$mShIMZIKnJ.EVqUycC2OE.qunAUqKJPFZq6ADSuJ.IYmVWBmXqWMi");
        usersRepository.save(user);
    }

    @Test
    public void login_status_isOk() throws Exception {
        addToDb();
        JSONObject login = new JSONObject();
        login.put("username", "user@gmail.com");
        login.put("password", "password");
        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(login.toString()))
                .andExpect(status().isOk());
    }

    @Test
    public void login_status_throw401() throws Exception {
        addToDb();
        JSONObject login = new JSONObject();
        login.put("username", "admin@gmail.com");
        login.put("password", "password1");
        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(login.toString()))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void register_status_is201() throws Exception {
        usersRepository.deleteAll();
        JSONObject register = new JSONObject();
        register.put("username", "user@gmail.com");
        register.put("password", "password");
        register.put("firstName", "user");
        register.put("lastName", "userovich");
        register.put("phone", "+7(777)777-77-77");
        register.put("role", Role.USER);
        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(register.toString()))
                .andExpect(status().isCreated());
    }

    @Test
    public void register_status_throw400() throws Exception {
        addToDb();
        JSONObject register = new JSONObject();
        register.put("username", "user@gmail.com");
        register.put("password", "password");
        register.put("firstName", "user");
        register.put("lastName", "userovich");
        register.put("phone", "+7(777)777-77-77");
        register.put("role", Role.USER);
        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(register.toString()))
                .andExpect(status().isBadRequest());
    }

}
