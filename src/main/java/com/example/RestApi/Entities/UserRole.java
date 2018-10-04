package com.example.RestApi.Entities;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "userrole",schema = "springboot")
public class UserRole {
    private long userRoleId;
    private Login user;
    private RoleDefinition roleDefinition;

    @Id
    @Column(name = "userroleid", nullable = false)
    public long getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(long userRoleId) {
        this.userRoleId = userRoleId;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userid")
    public Login getUser() {
        return user;
    }

    public void setUser(Login user) {
        this.user = user;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "roledefinitionid")
    public RoleDefinition getRoleDefinition() {
        return roleDefinition;
    }

    public void setRoleDefinition(RoleDefinition roleDefinition) {
        this.roleDefinition = roleDefinition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRole userRole = (UserRole) o;
        return userRoleId == userRole.userRoleId &&
                Objects.equals(user, userRole.user) &&
                Objects.equals(roleDefinition, userRole.roleDefinition);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userRoleId, user, roleDefinition);
    }
}
