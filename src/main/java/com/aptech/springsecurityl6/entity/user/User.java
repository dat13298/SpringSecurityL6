package com.aptech.springsecurityl6.entity.user;

import com.aptech.springsecurityl6.entity.role.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "users")
@Builder
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Email(message = "Email invalid")
    @NotNull(message = "Email not null")
    private String email;
    @NotNull(message = "Password not null")
    private String password;
    @NotNull(message = "Name not null")
    @Size(min = 1, max = 50, message = "Name must be between 1 and 50 characters")
    private String name;
    @Column(name = "last_login_date")
    private Timestamp lastLoginDate;
    private String status;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
    )
    private Set<Role> roles;

    public User(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.name = user.getName();
        this.lastLoginDate = user.getLastLoginDate();
        this.status = user.getStatus();
    }

}
