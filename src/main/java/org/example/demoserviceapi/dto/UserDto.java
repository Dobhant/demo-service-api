package org.example.demoserviceapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

/**
 * DTO для передачи данных о пользователе.
 */
@Schema(description = "Данные пользователя")
public class UserDto {

    @Schema(description = "Идентификатор пользователя", example = "1")
    private Long id;

    @Schema(description = "Имя пользователя", example = "Антон")
    private String name;

    @Schema(description = "Email пользователя", example = "anton@example.com")
    private String email;

    @Schema(description = "Возраст пользователя", example = "25")
    private int age;

    @Schema(description = "Дата и время создания пользователя", example = "2025-07-15T12:30:00")
    private LocalDateTime createdAt;

    // Явные геттеры и сеттеры (вместо Lombok)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
