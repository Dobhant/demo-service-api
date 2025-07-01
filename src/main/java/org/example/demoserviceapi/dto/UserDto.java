package org.example.demoserviceapi.dto;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * DTO для передачи данных о пользователе.
 */
@Data
public class UserDto {

    /** Идентификатор пользователя. */
    private Long id;

    /** Имя пользователя. */
    private String name;

    /** Email пользователя. */
    private String email;

    /** Возраст пользователя. */
    private int age;

    /** Дата и время создания пользователя. */
    private LocalDateTime createdAt;
}
