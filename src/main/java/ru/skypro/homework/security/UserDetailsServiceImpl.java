package ru.skypro.homework.security;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepository;

/**
 * Класс с методами для работы с аутентификацией и создания новой учетной записи пользователя.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;


    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Способ поиска пользователя в базе данных по имени пользователя.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new AdsUserDetails(userRepository.findByEmail(username).orElseThrow(UserNotFoundException::new));
    }

    /**
     * Существует метод проверки пользователя.
     */
    public boolean userExists(String username) {
        User userNotExists = new User();
        User users = userRepository.findByEmail(username).orElse(userNotExists);
        return !userNotExists.equals(users);
    }

    /**
     * Способ создания пользователя.
     */
    public void createUser(Register register, String password) {
        User user = new User();
        user.setPassword(password);
        user.setPhone(register.getPhone());
        user.setEmail(register.getUsername());
        user.setFirstName(register.getFirstName());
        user.setLastName(register.getLastName());
        user.setRole(register.getRole());
        userRepository.save(user);
    }
}