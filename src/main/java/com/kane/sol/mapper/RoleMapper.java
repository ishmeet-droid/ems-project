package com.kane.sol.mapper;

import com.kane.sol.dto.RoleDto;
import com.kane.sol.entities.Role;

public class RoleMapper {
    public static RoleDto toDto(Role r) {
        if (r == null) return null;
        RoleDto d = new RoleDto();
        d.setId(r.getId());
        d.setName(r.getName() != null ? r.getName().name() : null);
        return d;
    }
}
