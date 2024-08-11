package ru.skypro.homework.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.skypro.homework.dto.Role;

@ToString
@Setter
@Getter
@Data
public class User {
    private int Id;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private Role role;
    private String image;

    public User() {
    }

    public User(int id, String email, String firstName, String lastName, String phone, Role role, String image) {
        Id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.role = role;
        this.image = image;
    }
}
