package com.example.spring_k8s_crud.controller;

import com.example.spring_k8s_crud.exeption.ResourceNotFoundExpetion;
import com.example.spring_k8s_crud.model.Employee;
import com.example.spring_k8s_crud.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    // GET
    @GetMapping("employees")
    public List<Employee> getAllEmployee() {
        return this.employeeRepository.findAll();
    }

    // GET by id
    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId) throws ResourceNotFoundExpetion {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundExpetion("Employee not found for this id :: " + employeeId));
        return ResponseEntity.ok().body(employee);
    }
    // save
    @PostMapping("employees")
    public Employee createEmployee(@RequestBody Employee employee) {
        return this.employeeRepository.save(employee);
    }

    // update
    @PutMapping("employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employeeId, @Validated @RequestBody Employee employeeDetails) throws ResourceNotFoundExpetion {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundExpetion("Employee not found for this id :: " + employeeId));
        employee.setEmail(employeeDetails.getEmail());
        employee.setFirstName(employeeDetails.getFirstName());
        employee.setLastName(employeeDetails.getLastName());

        return ResponseEntity.ok(this.employeeRepository.save(employee));
    }

    // delete
    @DeleteMapping
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId) throws ResourceNotFoundExpetion {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundExpetion("Employee not found for this id :: " + employeeId));

        this.employeeRepository.delete(employee);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }


}
