package com.web.emp.controls;

import com.web.emp.entities.Employee;
import com.web.emp.services.EmpService;
import com.web.emp.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    private final EmpService empService;

    public EmployeeController(EmpService empService) {
        this.empService = empService;
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long id) {
        Optional<Employee> employee = employeeService.getEmployeeById(id);
        return employee.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        Employee createdEmployee = employeeService.createEmployee(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEmployee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") Long id, @RequestBody Employee employeeDetails) {
        Employee updatedEmployee = employeeService.updateEmployee(id, employeeDetails);
        return updatedEmployee != null ? ResponseEntity.ok(updatedEmployee)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable("id") Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public Mono<Employee> createEmp(@RequestBody Employee employee) {
        return empService.createEmployee(employee); // Returns the created employee
    }

    @GetMapping("/{id}")
    public Mono<Employee> getEmp(@PathVariable Long id) {
        return empService.getEmployeeById(id); // Returns the employee
    }

    @PutMapping("/{id}")
    public Mono<Employee> updateEmp(@PathVariable Long id, @RequestBody Employee employee) {
        return empService.updateEmployee(id, employee); // Returns the updated employee
    }

    @DeleteMapping("/{id}")
    public Mono<Boolean> deleteEmp(@PathVariable Long id) {
        return empService.deleteEmployee(id); // Returns a boolean indicating success or failure
    }

    @PatchMapping("/{id}")
    public Mono<Employee> patchEmp(@PathVariable Long id, @RequestBody Employee employee) {
        return empService.patchEmployee(id, employee); // Returns the partially updated employee
    }
}

