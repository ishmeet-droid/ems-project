package com.kane.sol.pages;


import com.kane.sol.dto.EmployeeDto;
import com.kane.sol.entities.Employee;
import com.kane.sol.services.EmployeeService;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.corelib.components.Form;

@Import(library = "context:js/validate-age.js")
public class EmployeeEdit {
    @Property
    private Long employeeId;

    @Property
    private EmployeeDto employee;

    @Inject
    private EmployeeService employeeService;

    // Called when URL is /employeeedit/{id}
    void onActivate(Long id) {
        this.employeeId = id;
        this.employee = employeeService.getEmployeeById(id);
    }

    Long onPassivate() {
        return employeeId;
    }

    Object onSuccessFromForm() {
        employeeService.saveEmployee(employee);
        return EmployeeList.class;
    }
}
