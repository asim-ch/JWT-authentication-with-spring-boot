package com.example.RestApi.Entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "roledefinition",schema = "springboot")
public class RoleDefinition {
    private long roleId;
    private String roleName;
    private String roleDefinition;

    @Id
    @Column(name = "roleid", nullable = false)
    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    @Basic
    @Column(name = "rolename", nullable = true, length = 20)
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Basic
    @Column(name = "roledefinition", nullable = true, length = 20)
    public String getRoleDefinition() {
        return roleDefinition;
    }

    public void setRoleDefinition(String roleDefinition) {
        this.roleDefinition = roleDefinition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleDefinition that = (RoleDefinition) o;
        return roleId == that.roleId &&
                Objects.equals(roleName, that.roleName) &&
                Objects.equals(roleDefinition, that.roleDefinition);
    }

    @Override
    public int hashCode() {

        return Objects.hash(roleId, roleName, roleDefinition);
    }
}
