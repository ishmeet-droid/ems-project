package com.kane.sol.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


@Embeddable
public class RolePermissionId implements Serializable {
    private Long roleId;
    private Long permissionId;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        RolePermissionId that = (RolePermissionId) o;
        return Objects.equals(roleId, that.roleId) && Objects.equals(permissionId, that.permissionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, permissionId);
    }
}
