package org.example.demoserviceapi.repository;

import org.example.demoserviceapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий для работы с сущностью {@link User}.
 *
 * Расширяет JpaRepository, предоставляя CRUD-операции.
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
