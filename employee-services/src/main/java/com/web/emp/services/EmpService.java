package com.web.emp.services;

import com.web.emp.entities.Employee;
import reactor.core.publisher.Mono;

public interface EmpService {
    Mono<Employee> createEmployee(Employee employee);

    Mono<Employee> getEmployeeById(Long id);

    Mono<Employee> updateEmployee(Long id, Employee employee);

    Mono<Boolean> deleteEmployee(Long id);

    Mono<Employee> patchEmployee(Long id, Employee employee);
}
