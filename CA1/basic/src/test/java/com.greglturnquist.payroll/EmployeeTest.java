package com.greglturnquist.payroll;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class EmployeeTest {

    @Test
    public void createEmployee() throws InstantiationException {
        // arrange
        String firstName = "Frodo";
        String lastName = "Baggins";
        String description = "ring bearer";
        int jobYears = 4;

        // act
        Employee employee = new Employee(firstName, lastName, description, jobYears);

        //assert
        assertNotNull(employee);
    }

    @Test
    public void setAndGetId() throws InstantiationException {
        // arrange
        Long id = 123456789L;
        String firstName = "Frodo";
        String lastName = "Baggins";
        String description = "ring bearer";
        int jobYears = 4;
        Employee employee = new Employee(firstName, lastName, description, jobYears);

        // act
        employee.setId(id);

        // assert
        assertEquals(id, employee.getId());
    }

    @Test
    public void setAndGetFirstName() throws InstantiationException {
        // arrange
        String firstName = "Frodo";
        String lastName = "Baggins";
        String description = "ring bearer";
        int jobYears = 4;
        Employee employee = new Employee(firstName, lastName, description, jobYears);
        String newFirstName = "Bilbo";

        // act
        employee.setFirstName(newFirstName);

        // assert
        assertEquals(newFirstName, employee.getFirstName());
    }

    @Test
    public void setAndGetLastName() throws InstantiationException {
        // arrange
        String firstName = "Frodo";
        String lastName = "Baggins";
        String description = "ring bearer";
        int jobYears = 4;
        Employee employee = new Employee(firstName, lastName, description, jobYears);
        String newLastName = "Simmons";

        // act
        employee.setLastName(newLastName);

        // assert
        assertEquals(newLastName, employee.getLastName());
    }

    @Test
    public void setAndGetDescription() throws InstantiationException {
        // arrange
        String firstName = "Frodo";
        String lastName = "Baggins";
        String description = "ring bearer";
        int jobYears = 4;
        Employee employee = new Employee(firstName, lastName, description, jobYears);
        String newDescription = "Elf friend";

        // act
        employee.setDescription(newDescription);

        // assert
        assertEquals(newDescription, employee.getDescription());
    }

    @Test
    public void setAndGetJobYears() throws InstantiationException {
        // arrange
        String firstName = "Frodo";
        String lastName = "Baggins";
        String description = "ring bearer";
        int jobYears = 4;
        Employee employee = new Employee(firstName, lastName, description, jobYears);
        int newJobYears = 5;

        // act
        employee.setJobYears(newJobYears);

        // assert
        assertEquals(newJobYears, employee.getJobYears());
    }

}
