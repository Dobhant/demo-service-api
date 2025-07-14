package org.example.demoserviceapi.mapper;

import org.example.demoserviceapi.dto.UserDto;
import org.example.demoserviceapi.entity.User;
import org.springframework.stereotype.Component;

/**
 * Маппер для преобразования между {@link User} и {@link UserDto}.
 */
@Component
public class UserMapper {

    /**
     * Преобразует сущность пользователя в DTO.
     *
     * @param user сущность пользователя
     *
     * @return DTO пользователя или {@code null}, если входное значение {@code null}
     */
    public UserDto toDto(User user) {
        if (user == null) return null;
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setAge(user.getAge());
        dto.setCreatedAt(user.getCreatedAt());

        return dto;
    }

    /**
     * Преобразует DTO в сущность пользователя.
     *
     * @param dto DTO пользователя
     *
     * @return сущность пользователя или {@code null}, если входное значение {@code null}
     */
    public User toEntity(UserDto dto) {
        if (dto == null) return null;
        User user = new User();
        user.setId(dto.getId());
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setAge(dto.getAge());
        user.setCreatedAt(dto.getCreatedAt());

        return user;
    }
}
