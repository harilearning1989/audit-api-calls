package com.web.emp.services.client;

import com.web.emp.entities.Employee;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.*;
import reactor.core.publisher.Mono;

@HttpExchange(url = "employees", accept = "application/json", contentType = "application/json")
public interface EmployeeClientService {

    // Create Employee (POST)
    @PostExchange
    Mono<Employee> createEmployee(@RequestBody Employee employee); // Returns the created employee

    // Get Employee (GET)
    @GetExchange("/{id}")
    Mono<Employee> getEmployeeById(@PathVariable("id") Long id); // Returns the employee by ID

    // Update Employee (PUT)
    @PutExchange("/{id}")
    Mono<Employee> updateEmployee(@PathVariable("id") Long id, @RequestBody Employee employee); // Returns the updated employee

    // Delete Employee (DELETE)
    @DeleteExchange("/{id}")
    Mono<Boolean> deleteEmployee(@PathVariable("id") Long id); // Returns true if deletion was successful

    // Partially Update Employee (PATCH)
    @PatchExchange("/{id}")
    Mono<Employee> patchEmployee(@PathVariable("id") Long id, @RequestBody Employee employee); // Returns the patched employee

    /*@GetExchange("employees")
    EmpResponseRec listAllEmployees();

    @GetExchange("/employees")
    Flux<EmpResponseRec> getAll();

    @GetExchange("/employee/{id}")
    EmpSingleResponseRec getById(@PathVariable("id") int id);

    @DeleteExchange("/{id}")
    Mono<ResponseEntity<Void>> delete(@PathVariable Long id);*/

}
