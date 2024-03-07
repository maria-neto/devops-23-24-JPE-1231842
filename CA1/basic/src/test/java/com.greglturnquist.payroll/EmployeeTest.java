package com.greglturnquist.payroll;

import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;

public class EmployeeTest {

    @Test
    public void createEmployee(){
        String firstName = "Frodo";
        String lastName = "Baggins";
        String description = "ring bearer";
        int jobYears = 4;
        Employee employee = new Employee(firstName, lastName, description, jobYears);
        assertNotNull(employee);

    }
}
