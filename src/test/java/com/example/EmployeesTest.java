package com.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class EmployeesTest {

    @Test
    @DisplayName("Successful payment of employee")
    void successfulPaymentOfEmployee() {
        EmployeeRepositoryTestReplacement EmployeeRepositoryTestReplacement = mock(EmployeeRepositoryTestReplacement.class);

        BankServiceTestReplacement BankServiceTestReplacement = mock(BankServiceTestReplacement.class);

        Employees employees  = new Employees(EmployeeRepositoryTestReplacement,BankServiceTestReplacement);

        List<Employee> testForEmployee = new ArrayList<>();
        testForEmployee.add(new Employee("1",3000));
        testForEmployee.add(new Employee("2",30001));




        when(EmployeeRepositoryTestReplacement.findAll()).thenReturn(testForEmployee);
        doNothing().when(BankServiceTestReplacement).pay(anyString(), anyDouble());

        int payments = employees.payEmployees();

        assertEquals(testForEmployee.size(),payments);

        assertTrue(testForEmployee.get(0).isPaid());
        assertTrue(testForEmployee.get(1).isPaid());




    }
    @Test
    @DisplayName("Failed payment of employee")
    void failedPaymentOfEmployee() {

        EmployeeRepositoryTestReplacement employeeRepositoryTestReplacement = mock(EmployeeRepositoryTestReplacement.class);


        BankServiceTestReplacement bankServiceTestReplacement = mock(BankServiceTestReplacement.class);
        doThrow(new RuntimeException("Payment failed")).when(bankServiceTestReplacement).pay(anyString(), anyDouble());


        Employees employees = new Employees(employeeRepositoryTestReplacement, bankServiceTestReplacement);

        List<Employee> testForEmployee = new ArrayList<>();
        testForEmployee.add(new Employee("1", 3000));
        testForEmployee.add(new Employee("2", 30001));

        when(employeeRepositoryTestReplacement.findAll()).thenReturn(testForEmployee);


        int payments = employees.payEmployees();


        assertEquals(0, payments);


        assertFalse(testForEmployee.get(0).isPaid());
        assertFalse(testForEmployee.get(1).isPaid());
    }

}