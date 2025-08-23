package com.kane.sol.services;

import com.kane.sol.dao.EmployeeDao;
import com.kane.sol.dao.RoleDao;
import com.kane.sol.entities.Employee;
import com.kane.sol.entities.Role;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Inject //@Autowired..
    private EmployeeDao employeeDao;

    @Inject
    private RoleDao roleDao;

    @Override
    public List<Employee> getAllEmployees() {
        return employeeDao.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeDao.findById(id);
    }


    @Override
    public Employee findByCredentials(String name, String password) {
        return employeeDao.findByCredentials(name, password);
    }


    @Override
    public void saveEmployee(Employee employee) {
        employee.setRole(roleDao.findByName(Role.Roles.EMPLOYEE));
        employee.setPassword("default");
        employeeDao.save(employee);
    }

    @Override
    public void deleteEmployee(Long id) {
        Employee e = employeeDao.findById(id);
        if (e != null) {
            employeeDao.delete(e);
        }
    }
}

