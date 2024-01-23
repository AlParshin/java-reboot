package ru.sberbank.edu;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.sberbank.edu.controller.UserController;
import ru.sberbank.edu.dao.UserEntity;
import ru.sberbank.edu.service.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * Unit test for simple App.
 */
@WebMvcTest({UserController.class})
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService service;

    /**
     * Проверка метода getAllUsers у UserController
     */
    @Test
    void getAllUsersTest() throws Exception {
        UserEntity user1 = new UserEntity(4L, "Igor", 52);
        UserEntity user2 = new UserEntity(5L, "Semen", 44);

        List users = new ArrayList();

        users.add(user1);
        users.add(user2);

        when(service.findAllUsers()).thenReturn(users);

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().string("UserEntity(id=4, name=Igor, age=52) ................. UserEntity(id=5, name=Semen, age=44) ................. "));
    }

    /**
     * Проверка метода getUserById у UserController
     */
    @Test
    void getUserByIdTest() throws Exception {
        UserEntity user1 = new UserEntity(4L, "Igor", 52);
        UserEntity user2 = new UserEntity(5L, "Semen", 44);

        when(service.findUserById(4L)).thenReturn(user1);

        mockMvc.perform(get("/users/4"))
                .andExpect(status().isOk())
                .andExpect(content().string("UserEntity(id=4, name=Igor, age=52)"));
    }

    /**
     * Проверка метода updateUserById у UserController
     */
    @Test
    void updateUserByIdTest() throws Exception {
        UserEntity user1 = new UserEntity(4L, "Igor", 52);
        UserEntity user2 = new UserEntity(5L, "Semen", 44);

        when(service.updateUserById(4L, "Maga", 15)).thenReturn(new UserEntity(4L, "Maga", 15));
        when(service.findUserById(4L)).thenReturn(new UserEntity(4L, "Maga", 15));

        mockMvc.perform(get("/users/update/4/name=Maga&age=15"))
                .andExpect(status().is3xxRedirection());

        mockMvc.perform(get("/users/4"))
                .andExpect(status().isOk())
                .andExpect(content().string("UserEntity(id=4, name=Maga, age=15)"));
    }

    /**
     * Проверка метода createNewUser у UserController
     */
    @Test
    void createNewUserTest() throws Exception {
        UserEntity user1 = new UserEntity(4L, "Igor", 52);
        UserEntity user2 = new UserEntity(5L, "Semen", 44);

        when(service.findUserById(6L)).thenReturn(new UserEntity(6L, "Maga", 15));

        mockMvc.perform(get("/users/create/id=6&name=Maga&age=15"))
                .andExpect(status().is3xxRedirection());

        mockMvc.perform(get("/users/6"))
                .andExpect(status().isOk())
                .andExpect(content().string("UserEntity(id=6, name=Maga, age=15)"));
    }

    /**
     * Проверка метода createNewUser у UserController
     */
    @Test
    void deleteUserById() throws Exception {
        UserEntity user1 = new UserEntity(4L, "Igor", 52);
        UserEntity user2 = new UserEntity(5L, "Semen", 44);
        UserEntity user3 = new UserEntity(6L, "Maga", 15);

        List users = new ArrayList();

        users.add(user1);
        users.add(user2);

        when(service.findAllUsers()).thenReturn(users);

        mockMvc.perform(get("/users/delete/6"))
                .andExpect(status().is3xxRedirection());

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().string("UserEntity(id=4, name=Igor, age=52) ................. UserEntity(id=5, name=Semen, age=44) ................. "));
    }
}
