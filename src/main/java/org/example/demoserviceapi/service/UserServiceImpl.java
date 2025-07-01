package org.example.demoserviceapi.service;

import lombok.RequiredArgsConstructor;
import org.example.demoserviceapi.dto.UserDto;
import org.example.demoserviceapi.entity.User;
import org.example.demoserviceapi.mapper.UserMapper;
import org.example.demoserviceapi.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Реализация сервиса управления пользователями.
 *
 * Предоставляет CRUD операции над сущностью User.
 */
@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper mapper;

    /**
     * Получить всех пользователей.
     *
     * @return список пользователей
     */
    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Получить пользователя по идентификатору.
     *
     * @param id идентификатор пользователя
     * @return пользователь с указанным id
     * @throws RuntimeException если пользователь не найден
     */
    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return mapper.toDto(user);
    }

    /**
     * Создать нового пользователя.
     *
     * @param userDto данные пользователя для создания
     * @return созданный пользователь
     */
    @Override
    public UserDto createUser(UserDto userDto) {
        User user = mapper.toEntity(userDto);
        user.setId(null); // сбрасываем id для новой сущности
        User saved = userRepository.save(user);
        return mapper.toDto(saved);
    }

    /**
     * Обновить существующего пользователя.
     *
     * @param id идентификатор пользователя для обновления
     * @param userDto новые данные пользователя
     * @return обновленный пользователь
     * @throws RuntimeException если пользователь не найден
     */
    @Override
    public UserDto updateUser(Long id, UserDto userDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setAge(userDto.getAge());
        User saved = userRepository.save(user);
        return mapper.toDto(saved);
    }

    /**
     * Удалить пользователя по идентификатору.
     *
     * @param id идентификатор пользователя для удаления
     */
    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
