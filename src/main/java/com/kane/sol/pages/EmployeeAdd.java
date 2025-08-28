package com.kane.sol.pages;

import com.kane.sol.dto.EmployeeDto;
import com.kane.sol.entities.Employee;
import com.kane.sol.services.EmployeeService;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.corelib.components.Form;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;


@Import(library = "context:js/validate-age.js")
public class EmployeeAdd {

    @Inject
    private EmployeeService employeeService;

    @Property
    private String name;

    @Property
    @Min(18)
    private int age;

    @Property
    @NotBlank
    private String address;

    Object onFailureFromForm() { return this; }

    Object onSuccess() {
        EmployeeDto e = new EmployeeDto();
        e.setName(name);
        e.setAge(age);
        e.setAddress(address);

        employeeService.saveEmployee(e);
        return EmployeeList.class;
    }
}
