package ru.sberbank.edu;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.sberbank.edu.controller.UserController;
import ru.sberbank.edu.dao.UserEntity;
import ru.sberbank.edu.repository.UserRepository;
import ru.sberbank.edu.service.UserService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({UserRepository.class})
public class UserRepositoryTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserController controller;

    @MockBean
    private UserService service;

    /**
     * Проверка метода saveOrUpdate у UserRepository
     */
    @Test
    void saveOrUpdateTest() throws Exception {

        UserEntity user1 = new UserEntity(4L, "Igor", 52);
        UserEntity user2 = new UserEntity(5L, "Semen", 44);

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
}
