package com.greglturnquist.payroll;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class EmployeeTest {

    @Test
    public void createEmployee(){
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
    public void setAndGetId() {
        // arrange
        Long id = 123456789L;
        Employee employee = new Employee("Frodo", "Baggins", "ring bearer", 4);

        // act
        employee.setId(id);

        // assert
        assertEquals(id, employee.getId());
    }

    @Test
    public void setAndGetFirstName() {
        // arrange
        String firstName = "Bilbo";
        Employee employee = new Employee("Frodo", "Baggins", "ring bearer", 4);

        // act
        employee.setFirstName(firstName);

        // assert
        assertEquals(firstName, employee.getFirstName());
    }

    @Test
    public void setAndGetLastName() {
        // arrange
        String lastName = "Simmons";
        Employee employee = new Employee("Frodo", "Baggins", "ring bearer", 4);

        // act
        employee.setLastName(lastName);

        // assert
        assertEquals(lastName, employee.getLastName());
    }

    @Test
    public void setAndGetDescription() {
        // arrange
        String description = "drummer";
        Employee employee = new Employee("Frodo", "Baggins", "ring bearer", 4);

        // act
        employee.setDescription(description);

        // assert
        assertEquals(description, employee.getDescription());
    }

    @Test
    public void setAndGetJobYears() {
        // arrange
        int jobYears = 5;
        Employee employee = new Employee("Frodo", "Baggins", "ring bearer", 4);

        // act
        employee.setJobYears(jobYears);

        // assert
        assertEquals(jobYears, employee.getJobYears());
    }

}
