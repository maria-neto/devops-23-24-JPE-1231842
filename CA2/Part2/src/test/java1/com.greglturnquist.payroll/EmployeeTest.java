package com.greglturnquist.payroll;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertThrows;


public class EmployeeTest {
    @Test
    public void createEmployee() throws InstantiationException {
        // arrange
        String firstName = "Frodo";
        String lastName = "Baggins";
        String description = "ring bearer";
        int jobYears = 4;
        String email = "frodo@shire.com";

        // act
        Employee employee = new Employee(firstName, lastName, description, jobYears, email);

        //assert
        assertNotNull(employee);
    }

    @Test
    public void invalidFirstName_shouldThrowException() {
        // arrange
        String firstName = null;
        String lastName = "Baggins";
        String description = "ring bearer";
        int jobYears = 4;
        String expectedMessage = "Invalid arguments";
        String email = "frodo@shire.com";

        // act
        Exception exception = assertThrows(InstantiationException.class, () ->
                new Employee(firstName, lastName, description, jobYears, email)
        );
        String actualMessage = exception.getMessage();

        // assert
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void invalidLastName_shouldThrowException() {
        // arrange
        String firstName = "Frodo";
        String lastName = "";
        String description = "ring bearer";
        int jobYears = 4;
        String expectedMessage = "Invalid arguments";
        String email = "frodo@shire.com";

        // act
        Exception exception = assertThrows(InstantiationException.class, () ->
                new Employee(firstName, lastName, description, jobYears, email)
        );
        String actualMessage = exception.getMessage();

        // assert
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void invalidDescription_shouldThrowException() {
        // arrange
        String firstName = "Frodo";
        String lastName = "Baggins";
        String description = " ";
        int jobYears = 4;
        String expectedMessage = "Invalid arguments";
        String email = "frodo@shire.com";

        // act
        Exception exception = assertThrows(InstantiationException.class, () ->
                new Employee(firstName, lastName, description, jobYears, email)
        );
        String actualMessage = exception.getMessage();

        // assert
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void invalidJobYears_shouldThrowException() {
        // arrange
        String firstName = "Frodo";
        String lastName = "Baggins";
        String description = "ring bearer";
        int jobYears = -1;
        String expectedMessage = "Invalid arguments";
        String email = "frodo@shire.com";

        // act
        Exception exception = assertThrows(InstantiationException.class, () ->
                new Employee(firstName, lastName, description, jobYears, email)
        );
        String actualMessage = exception.getMessage();

        // assert
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void invalidEmail_shouldThrowException() {
        // arrange
        String firstName = "Frodo";
        String lastName = "Baggins";
        String description = "ring bearer";
        int jobYears = 4;
        String email = null;
        String expectedMessage = "Invalid arguments";

        // act
        Exception exception = assertThrows(InstantiationException.class, () ->
                new Employee(firstName, lastName, description, jobYears, email)
        );
        String actualMessage = exception.getMessage();

        // assert
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void invalidEmailAddress_shouldThrowException() {
        // arrange
        String firstName = "Frodo";
        String lastName = "Baggins";
        String description = "ring bearer";
        int jobYears = 4;
        String email = "frodo@shire";
        String expectedMessage = "Invalid arguments";

        // act
        Exception exception = assertThrows(InstantiationException.class, () ->
                new Employee(firstName, lastName, description, jobYears, email)
        );
        String actualMessage = exception.getMessage();

        // assert
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void invalidEmailAddress1_shouldThrowException() {
        // arrange
        String firstName = "Frodo";
        String lastName = "Baggins";
        String description = "ring bearer";
        int jobYears = 4;
        String email = "frodo.shire.com";
        String expectedMessage = "Invalid arguments";

        // act
        Exception exception = assertThrows(InstantiationException.class, () ->
                new Employee(firstName, lastName, description, jobYears, email)
        );
        String actualMessage = exception.getMessage();

        // assert
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void invalidEmailAddress2_shouldThrowException() {
        // arrange
        String firstName = "Frodo";
        String lastName = "Baggins";
        String description = "ring bearer";
        int jobYears = 4;
        String email = "@frodo.shire";
        String expectedMessage = "Invalid arguments";

        // act
        Exception exception = assertThrows(InstantiationException.class, () ->
                new Employee(firstName, lastName, description, jobYears, email)
        );
        String actualMessage = exception.getMessage();

        // assert
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void setAndGetId() throws InstantiationException {
        // arrange
        Long id = 123456789L;
        String firstName = "Frodo";
        String lastName = "Baggins";
        String description = "ring bearer";
        int jobYears = 4;
        String email = "frodo@shire.com";

        Employee employee = new Employee(firstName, lastName, description, jobYears, email);

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
        String email = "frodo@shire.com";
        Employee employee = new Employee(firstName, lastName, description, jobYears, email);
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
        String email = "frodo@shire.com";
        Employee employee = new Employee(firstName, lastName, description, jobYears, email);
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
        String email = "frodo@shire.com";
        Employee employee = new Employee(firstName, lastName, description, jobYears, email);
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
        String email = "frodo@shire.com";
        Employee employee = new Employee(firstName, lastName, description, jobYears, email);
        int newJobYears = 5;

        // act
        employee.setJobYears(newJobYears);

        // assert
        assertEquals(newJobYears, employee.getJobYears());
    }

    @Test
    public void setAndGetEmail() throws InstantiationException {
        // arrange
        String firstName = "Frodo";
        String lastName = "Baggins";
        String description = "ring bearer";
        int jobYears = 4;
        String email = "frodo@shire.com";
        Employee employee = new Employee(firstName, lastName, description, jobYears, email);
        String newEmail = "frodo@mordor.com";

        // act
        employee.setEmail(newEmail);

        // assert
        assertEquals(newEmail, employee.getEmail());
    }

    @Test
    public void setInvalidEmail_shouldThrowException() throws InstantiationException {
        // arrange
        String firstName = "Frodo";
        String lastName = "Baggins";
        String description = "ring bearer";
        int jobYears = 4;
        String email = "frodo@shire.com";
        String newEmail = "frodo.shire.com";
        String expectedMessage = "Invalid email";
        Employee employee = new Employee(firstName, lastName, description, jobYears, email);

        // act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                employee.setEmail(newEmail)
        );
        String actualMessage = exception.getMessage();

        // assert
        assertEquals(expectedMessage, actualMessage);
    }
}
