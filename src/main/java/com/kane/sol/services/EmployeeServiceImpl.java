package com.kane.sol.services;

import com.kane.sol.dao.EmployeeDao;
import com.kane.sol.dao.RoleDao;
import com.kane.sol.dto.EmployeeDto;
import com.kane.sol.entities.Employee;
import com.kane.sol.entities.Role;
import com.kane.sol.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

   @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<EmployeeDto> getAllEmployees() {
        return employeeDao.findAll()
                .stream().map(EmployeeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto getEmployeeById(Long id) {
        return EmployeeMapper.toDto(employeeDao.findById(id));
    }


    @Override
    public EmployeeDto findByCredentials(String name, String password) {
        return EmployeeMapper.toDto(employeeDao.findByCredentials(name, password));
    }


    @Override
    public void saveEmployee(EmployeeDto dto) {

        Employee e = dto.getId() != null ? employeeDao.findById(dto.getId()) : null;
        e = EmployeeMapper.toEntity(dto, e);


        // set role
        Role.Roles defaultRole = Role.Roles.EMPLOYEE;
        if (dto.getRoleName() != null) {

            defaultRole = Role.Roles.valueOf(dto.getRoleName());

        }
        e.setRole(roleDao.findByName(defaultRole));

        e.setPassword("default");

        employeeDao.save(e);
        dto.setId(e.getId());

    }

    @Override
    public void deleteEmployee(Long id) {
        Employee e = employeeDao.findById(id);
        if (e != null) {
            employeeDao.delete(e);
        }
    }

    @Override
    public void promoteToManager(Long id) {
        Employee e = employeeDao.findById(id);
        if (e == null) return;
        e.setRole(roleDao.findByName(Role.Roles.MANAGER));
        employeeDao.save(e);
    }

    @Override
    public EmployeeDto getEmployeeByUserName(String userName) {
        return EmployeeMapper.toDto(employeeDao.findByUsername(userName));
    }

    @Override
    public List<String> suggestEmployeeNames(String startsWith) {
        return employeeDao.search(startsWith).stream()
                .map(Employee::getName)
                .distinct()
                .limit(20)
                .collect(java.util.stream.Collectors.toList());
    }


}

