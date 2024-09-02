package ru.skypro.homework.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.user.ImageDto;
import ru.skypro.homework.dto.user.NewPasswordDto;
import ru.skypro.homework.dto.user.UpdateUserDto;
import ru.skypro.homework.dto.user.UserDto;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.exception.WrongCurrentPasswordException;
import ru.skypro.homework.model.ImageUser;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.ImageUserRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;

import java.io.IOException;
import java.util.Objects;

/**
 * Класс с методами для обновления и получения учетной записи пользователя.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private ImageUserRepository imageUserRepository;

    /**
     * Метод обновления пароля
     */
    @Override
    public void updatePassword(NewPasswordDto newPasswordDto, String username) {
        User user = userRepository.findByEmail(username).orElseThrow(UserNotFoundException::new);
        if (passwordEncoder.matches(newPasswordDto.getCurrentPassword(), user.getPassword())) {
            user.setPassword(passwordEncoder.encode(newPasswordDto.getNewPassword()));
            userRepository.save(user);
        } else {
            throw new WrongCurrentPasswordException("Your new password is incorrect.");
        }
    }

    /**
     * Способ получения информации об учетной записи пользователя.
     */
    @Override
    public UserDto getInformation(String username) {
        User user = userRepository.findByEmail(username).orElseThrow(UserNotFoundException::new);
        return UserDto.toUser(user);
    }

    /**
     * Способ обновления информации (имя, фамилия и телефон) для учетной записи пользователя.
     */
    @Override
    public UpdateUserDto updateInformationAboutUser(UpdateUserDto updateUserDto, String username) {
        User user = userRepository.findByEmail(username).orElseThrow(UserNotFoundException::new);
        user.setFirstName(updateUserDto.getFirstName());
        user.setLastName(updateUserDto.getLastName());
        user.setPhone(updateUserDto.getPhone());
        userRepository.save(user);
        return UpdateUserDto.toUpdateUser(user);
    }

    /**
     * Способ обновления изображения для учетной записи пользователя.
     */
    @Override
    public ImageDto updateImage(MultipartFile file, String username) {
        User user = userRepository.findByEmail(username).orElseThrow(UserNotFoundException::new);
        ImageUser image;
        if (!Objects.isNull(user.getImageUser())) {
            image = imageUserRepository.findById(user.getImageUser().getId()).orElse(new ImageUser());
        } else {
            image = new ImageUser();
            image.setId(user.getId().toString());
        }
        try {
            byte[] imageBytes = file.getBytes();
            image.setImage(imageBytes);
        } catch (IOException e) {
            throw new RuntimeException();
        }
        ImageUser returnImage = imageUserRepository.save(image);
        user.setImageUser(image);
        userRepository.save(user);
        return ImageDto.fromImage(returnImage);
    }

    /**
     * Способ получения изображения для учетной записи пользователя.
     */
    @Override
    public byte[] getImage(String id) {
        ImageUser image = imageUserRepository.findById(id).orElseThrow();
        return image.getImage();
    }

}
