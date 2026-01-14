package org.example.demoserviceapi.service;

import org.example.demoserviceapi.dto.UserDto;
import java.util.List;

/**
 * Сервис для управления пользователями.
 */
public interface UserService {

    /**
     * Получить всех пользователей.
     *
     * @return список пользователей
     */
    List<UserDto> getAllUsers();

    /**
     * Получить пользователя по ID.
     *
     * @param id идентификатор пользователя
     * @return пользователь с указанным ID
     */
    UserDto getUserById(Long id);

    /**
     * Создать нового пользователя.
     *
     * @param userDto данные нового пользователя
     * @return созданный пользователь
     */
    UserDto createUser(UserDto userDto);

    /**
     * Обновить данные пользователя.
     *
     * @param id идентификатор пользователя для обновления
     * @param userDto новые данные пользователя
     * @return обновлённый пользователь
     */
    UserDto updateUser(Long id, UserDto userDto);

    /**
     * Удалить пользователя по ID.
     *
     * @param id идентификатор пользователя для удаления
     */
    void deleteUser(Long id);
}
