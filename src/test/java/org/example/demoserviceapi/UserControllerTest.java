package org.example.demoserviceapi;

import org.example.demoserviceapi.dto.UserDto;
import org.example.demoserviceapi.entity.User;
import org.example.demoserviceapi.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        userRepository.deleteAll(); // Чистим БД перед каждым тестом
    }

    @Test
    void testCreateUser() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setName("Alice");
        userDto.setEmail("alice@example.com");
        userDto.setAge(30);

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isCreated()) // ⬅ Ожидаем 201, а не 200
                .andExpect(jsonPath("$.id").exists());

    }

    @Test
    void testGetAllUsers() throws Exception {
        User user = new User();
        user.setName("Bob");
        user.setEmail("bob@example.com");
        user.setAge(25);
        userRepository.save(user);

        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name").value("Bob"));
    }

    @Test
    void testUpdateUser() throws Exception {
        User user = new User();
        user.setName("Charlie");
        user.setEmail("charlie@example.com");
        user.setAge(28);
        user = userRepository.save(user);

        UserDto updateDto = new UserDto();
        updateDto.setName("Charlie Updated");
        updateDto.setEmail("charlie@example.com");
        updateDto.setAge(29);

        mockMvc.perform(put("/api/users/" + user.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Charlie Updated"))
                .andExpect(jsonPath("$.age").value(29));
    }

    @Test
    void testDeleteUser() throws Exception {
        User user = new User();
        user.setName("Dave");
        user.setEmail("dave@example.com");
        user.setAge(33);
        user = userRepository.save(user);

        // ✅ Проверяем, что DELETE возвращает 204 No Content
        mockMvc.perform(delete("/api/users/" + user.getId()))
                .andExpect(status().isNoContent());

        // ✅ Проверяем, что GET возвращает 200 OK и пустой список
        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

}
