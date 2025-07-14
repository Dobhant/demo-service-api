package org.example.demoserviceapi.controller;

import org.example.demoserviceapi.dto.UserDto;
import org.example.demoserviceapi.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST-контроллер для управления пользователями.
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Получить список всех пользователей.
     *
     * @return список пользователей
     */
    @GetMapping
    public List<UserDto> getAll() {
        return userService.getAllUsers();
    }

    /**
     * Получить пользователя по его ID.
     *
     * @param id идентификатор пользователя
     *
     * @return данные пользователя
     */
    @GetMapping("/{id}")
    public UserDto getById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    /**
     * Создать нового пользователя.
     *
     * @param dto данные пользователя
     *
     * @return созданный пользователь
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto create(@RequestBody UserDto dto) {
        return userService.createUser(dto);
    }

    /**
     * Обновить данные пользователя.
     *
     * @param id  идентификатор пользователя
     * @param dto обновлённые данные
     *
     * @return обновлённый пользователь
     */
    @PutMapping("/{id}")
    public UserDto update(@PathVariable Long id, @RequestBody UserDto dto) {
        return userService.updateUser(id, dto);
    }

    /**
     * Удалить пользователя по ID.
     *
     * @param id идентификатор пользователя
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
