package org.example.demoserviceapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.demoserviceapi.dto.UserDto;
import org.example.demoserviceapi.mapper.UserModelAssembler;
import org.example.demoserviceapi.service.UserService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User API", description = "Операции с пользователями")
public class UserController {

    private final UserService userService;
    private final UserModelAssembler userAssembler;

    public UserController(UserService userService, UserModelAssembler userAssembler) {
        this.userService = userService;
        this.userAssembler = userAssembler;
    }

    @Operation(summary = "Получить всех пользователей")
    @ApiResponse(responseCode = "200", description = "Список пользователей успешно получен")
    @GetMapping
    public CollectionModel<EntityModel<UserDto>> getAll() {
        List<EntityModel<UserDto>> users = userService.getAllUsers().stream()
                .map(userAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(users,
                linkTo(methodOn(UserController.class).getAll()).withSelfRel());
    }

    @Operation(summary = "Получить пользователя по ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Пользователь найден"),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден")
    })
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<UserDto>> getById(
            @Parameter(description = "ID пользователя") @PathVariable Long id) {
        UserDto user = userService.getUserById(id);
        return ResponseEntity.ok(userAssembler.toModel(user));
    }

    @Operation(summary = "Создать нового пользователя")
    @ApiResponse(responseCode = "201", description = "Пользователь успешно создан")
    @PostMapping
    public ResponseEntity<EntityModel<UserDto>> create(
            @Parameter(description = "Данные пользователя") @RequestBody UserDto dto) {
        UserDto created = userService.createUser(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userAssembler.toModel(created));
    }

    @Operation(summary = "Обновить пользователя")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Пользователь обновлён"),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден")
    })
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<UserDto>> update(
            @Parameter(description = "ID пользователя") @PathVariable Long id,
            @Parameter(description = "Обновлённые данные") @RequestBody UserDto dto) {
        UserDto updated = userService.updateUser(id, dto);
        return ResponseEntity.ok(userAssembler.toModel(updated));
    }

    @Operation(summary = "Удалить пользователя")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Пользователь удалён"),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID пользователя") @PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
