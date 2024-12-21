package com.web.emp.services;

import com.web.emp.entities.Employee;
import com.web.emp.services.client.EmployeeClientService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class EmpServiceImpl implements EmpService{

    private final EmployeeClientService employeeService;

    public EmpServiceImpl(EmployeeClientService employeeService) {
        this.employeeService = employeeService;
    }

    // Create Employee
    @Override
    public Mono<Employee> createEmployee(Employee employee) {
        return employeeService.createEmployee(employee);
    }

    // Get Employee by ID
    @Override
    public Mono<Employee> getEmployeeById(Long id) {
        return employeeService.getEmployeeById(id);
    }

    // Update Employee
    @Override
    public Mono<Employee> updateEmployee(Long id, Employee employee) {
        return employeeService.updateEmployee(id, employee);
    }

    // Delete Employee
    @Override
    public Mono<Boolean> deleteEmployee(Long id) {
        return employeeService.deleteEmployee(id);
    }

    // Partially Update Employee
    @Override
    public Mono<Employee> patchEmployee(Long id, Employee employee) {
        return employeeService.patchEmployee(id, employee);
    }
}
