package ru.skypro.homework.model;

import lombok.*;
import ru.skypro.homework.dto.Role;

import javax.persistence.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "first_Name")
    private String firstName;

    @Column(name = "last_Name")
    private String lastName;

    @Column(name = "phone")
    private String phone;

    @Enumerated
    @Column(name = "role")
    private Role role;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "image_id")
    private ImageUser imageUser;

    @Column(nullable = false, name = "password")
    private String password;

}
