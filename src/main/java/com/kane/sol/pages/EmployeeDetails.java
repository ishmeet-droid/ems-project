package com.kane.sol.pages;

import com.kane.sol.entities.Employee;
import com.kane.sol.services.EmployeeService;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.annotations.ActivationRequestParameter;

public class EmployeeDetails {

    @Inject
    private EmployeeService employeeService;

    @Property
    private Employee employee;

    void onActivate(Long id) {
        employee = employeeService.getEmployeeById(id);
    }

    Long onPassivate() {
        return employee != null ? employee.getId() : null;
    }
}
