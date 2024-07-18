package com.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {
    @Test
    void testEmployeeInitialization() {

        Employee employee = new Employee("1", 50000);


        assertEquals("1", employee.getId());
        assertEquals(50000, employee.getSalary(), 0.001);
        assertFalse(employee.isPaid());


    }

    @Test
    @DisplayName("change trough setter method test")
    void changeTroughSetterMethodTest() {

        Employee employee = new Employee("1",6000);

        employee.setId("2");
        employee.setSalary(60000);
        employee.setPaid(true);


        assertEquals("2", employee.getId());
        assertEquals(60000, employee.getSalary(), 0.001);
        assertTrue(employee.isPaid());
    }

    @Test
    @DisplayName("To string method test")
    void toStringMethodTest() {

        Employee employee = new Employee("2",60000);

        String expectedToString = "Employee [id=2, salary=60000.0]";
        assertEquals(expectedToString, employee.toString());

    }

    @Test
    @DisplayName("Fail to string method")
    void failToStringMethod() {
        Employee employee = new Employee("1",4);
        String expectedToString = "Employee [id 1, salary = 45.0]";
        assertFalse(Boolean.parseBoolean(expectedToString), employee.toString());
    }
}