package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.user.ImageDto;
import ru.skypro.homework.dto.user.NewPasswordDto;
import ru.skypro.homework.dto.user.UpdateUserDto;
import ru.skypro.homework.dto.user.UserDto;

/**
 * Интерфейс со способами обновления и получения учетной записи пользователя.
 */
public interface UserService {
    void updatePassword(NewPasswordDto newPassword, String username);

    UserDto getInformation(String username);

    UpdateUserDto updateInformationAboutUser(UpdateUserDto updateUser, String username);

    ImageDto updateImage(MultipartFile file, String username);

    byte[] getImage(String id);
}
