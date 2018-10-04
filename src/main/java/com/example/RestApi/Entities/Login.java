package com.example.RestApi.Entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "login",schema = "springboot")
public class Login {
    @Id
    @Column(name = "userid", nullable = false)
    private long userId;
    @Column(name = "username")
    private String userName;
    @Column(name = "active", nullable = true)
    private Integer active;
    @Column(name = "password", nullable = true, length = 50)
    private String password;
    @OneToMany(mappedBy = "user")
    private List<UserRole> userRoles;

}
