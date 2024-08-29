package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.user.NewPasswordDto;
import ru.skypro.homework.dto.user.UpdateUserDto;
import ru.skypro.homework.dto.user.UserDto;
import ru.skypro.homework.service.UserService;

import javax.validation.Valid;

/**
 * Метод регистрации пользователя с проверкой входных данных в классе-контроллере для запуска конечных точек пользователя.
 */
@Slf4j
@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * Метод обновления пароля для зарегистрированных пользователей с проверкой входных данных.
     */
    @PostMapping("/set_password")
    public void updatePassword(@RequestBody @Valid NewPasswordDto newPassword, Authentication authentication) {
        userService.updatePassword(newPassword, authentication.getName());
    }

    /**
     * Метод получения информации о профиле для зарегистрированных пользователей.
     */
    @GetMapping("/me")
    public UserDto getInformation(Authentication authentication) {
        return userService.getInformation(authentication.getName());
    }

    /**
     * Метод обновления информации (имя, фамилия, телефон) для зарегистрированных пользователей.
     */
    @PatchMapping("/me")
    public UpdateUserDto updateInformationAboutUser(@RequestBody @Valid UpdateUserDto updateUserDto, Authentication authentication) {
        return userService.updateInformationAboutUser(updateUserDto, authentication.getName());
    }

    /**
     * Способ обновления изображения в профиле для зарегистрированных пользователей.
     */
    @PatchMapping("/me/image")
    public ResponseEntity<byte[]> updateImage(@RequestPart MultipartFile image, Authentication authentication) {
        userService.updateImage(image, authentication.getName());
        return ResponseEntity.ok().build();
    }

    /**
     * Метод вывода изображения на дисплей в профиле для зарегистрированных пользователей
     */
    @GetMapping(value = "/{id}/image", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE, "image/*"})
    public byte[] getImage(@PathVariable("id") String id) {
        return userService.getImage(id);
    }

}
