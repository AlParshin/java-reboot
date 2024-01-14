package ru.sberbank.edu.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.sberbank.edu.entity.User;
import ru.sberbank.edu.repository.UserRepository;
import ru.sberbank.edu.service.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * Unit test for simple App.
 */
@WebMvcTest({UserService.class})
public class UserServiceTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserController controller;

    @MockBean
    private UserRepository repository;

    /**
     * Проверка метода findAllUsers у UserService
     */
    @Test
    void findAllUsersTest() throws Exception {
        User user1 = new User(4L, "Igor", 52);
        User user2 = new User(5L, "Semen", 44);

        List users = new ArrayList();

        users.add(user1);
        users.add(user2);

        StringBuffer sb = new StringBuffer();
        sb.append("User(id=4, name=Igor, age=52) ................. User(id=5, name=Semen, age=44) ................. ");

        when(controller.getAllUsers()).thenReturn(sb);

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().string("User(id=4, name=Igor, age=52) ................. User(id=5, name=Semen, age=44) ................. "));
    }


    /**
     * Проверка метода findUserById у UserService
     */
    @Test
    void findUserByIdTest() throws Exception {
        User user1 = new User(4L, "Igor", 52);
        User user2 = new User(5L, "Semen", 44);

        when(controller.getUserById(4)).thenReturn("User(id=4, name=Igor, age=52)");

        mockMvc.perform(get("/users/4"))
                .andExpect(status().isOk())
                .andExpect(content().string("User(id=4, name=Igor, age=52)"));
    }




    /**
     * Проверка метода updateUserById у UserService
     */
    @Test
    void updateUserByIdTest() throws Exception {

        User user1 = new User(4L, "Igor", 52);
        User user2 = new User(5L, "Semen", 44);

        mockMvc.perform(get("/users/update/4/name=Maga&age=15"))
                .andExpect(status().is3xxRedirection());
    }

    /**
     * Проверка метода add у UserService
     */
    @Test
    void addTest() throws Exception {

        User user1 = new User(4L, "Igor", 52);
        User user2 = new User(5L, "Semen", 44);

        mockMvc.perform(get("/users/create/id=6&name=Maga&age=15"))
                .andExpect(status().is3xxRedirection());

    }

    /**
     * Проверка метода delete у UserService
     */
    @Test
    void delete() throws Exception {

        User user1 = new User(4L, "Igor", 52);
        User user2 = new User(5L, "Semen", 44);

        mockMvc.perform(get("/users/delete/4"))
                .andExpect(status().is3xxRedirection());

    }
}