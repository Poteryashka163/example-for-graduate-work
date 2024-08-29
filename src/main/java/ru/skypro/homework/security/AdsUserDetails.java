package ru.skypro.homework.security;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.model.User;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Этот класс используется при проверке подлинности и авторизации, реализует класс UserDetails.
 */
@Data
@NoArgsConstructor
public class AdsUserDetails implements UserDetails {

    private Integer id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private Role role;

    public AdsUserDetails(User user) {
        this.id = user.getId();
        this.username = user.getEmail();
        this.password = user.getPassword();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.phone = user.getPhone();
        this.role = user.getRole();
    }

    /**
     * Переопределенный метод получения списка ролей пользователя.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Optional.ofNullable(role)
                .map(role -> "ROLE_" + role)
                .map(SimpleGrantedAuthority::new)
                .map(List::of)
                .orElse(Collections.emptyList());
    }

    /**
     * Переопределенный метод получения пароля.
     */
    @Override
    public String getPassword() {
        return this.password;
    }

    /**
     * Переопределенный метод получения имени пользователя.
     */
    @Override
    public String getUsername() {
        return this.username;
    }

    /**
     * Переопределенный метод проверки пользователя учетной записи с истекшим сроком действия.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Переопределенный метод проверки пользователя с разблокированной или заблокированной учетной записью.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Переопределенный метод проверки пользователя на наличие просроченных учетных данных.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Переопределенный метод проверки учетной записи пользователя включен или нет.
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
