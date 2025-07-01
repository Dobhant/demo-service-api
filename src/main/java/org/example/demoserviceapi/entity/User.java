package org.example.demoserviceapi.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

/**
 * Сущность пользователя, отображаемая на таблицу "users".
 */
@Entity
@Table(name = "users")
@Data
public class User {

    /** Уникальный идентификатор пользователя. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Имя пользователя. */
    @Column(nullable = false)
    private String name;

    /** Email пользователя (уникален). */
    @Column(nullable = false, unique = true)
    private String email;

    /** Возраст пользователя. */
    @Column(nullable = false)
    private int age;

    /** Дата и время создания пользователя. Устанавливается автоматически. */
    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}
