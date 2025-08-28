package com.kane.sol.mapper;

import com.kane.sol.dto.EmployeeDto;
import com.kane.sol.entities.Employee;

public class EmployeeMapper {
    public static EmployeeDto toDto(Employee e) {
        if (e == null) return null;
        EmployeeDto d = new EmployeeDto();
        d.setId(e.getId());
        d.setName(e.getName());
        d.setAge(e.getAge());
        d.setAddress(e.getAddress());
        d.setRoleName(e.getRole() != null ? e.getRole().getName().name() : null);
        d.setDob(e.getDob());
        d.setGender(e.getGender());
        return d;
    }

    public static Employee toEntity(EmployeeDto d, Employee target) {
        if (target == null) target = new Employee();
        target.setName(d.getName());
        target.setAge(d.getAge() != null ? d.getAge() : 0);
        target.setAddress(d.getAddress());
        return target;
    }
}
