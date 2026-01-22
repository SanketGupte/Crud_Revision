package com.sanket.crud.services.impl;

import com.sanket.crud.entities.Employee;
import com.sanket.crud.repositories.EmployeeRepository;
import com.sanket.crud.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> fetchAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if(employee.isPresent()) {
            return employee.get();
        }
        return null;
    }

    @Override
    public Employee updateEmployeeById(Long id, Employee employee) {
        Optional<Employee> employee1= employeeRepository.findById(id);
        if(employee1.isPresent()){
            Employee originalEmployee = employee1.get();

            if(Objects.nonNull(employee.getEmployeeId()) && !"".equalsIgnoreCase(employee.getEmployeeName())) {
                originalEmployee.setEmployeeName(employee.getEmployeeName());
            }
            if(Objects.nonNull(employee.getEmployeeSalary()) && !"".equals(employee.getEmployeeSalary())){
                originalEmployee.setEmployeeSalary(employee.getEmployeeSalary());
            }
            return  employeeRepository.save(originalEmployee);
        }
        return null;
    }

    @Override
    public String deleteDepartmentById(Long id) {
        if(employeeRepository.findById(id).isPresent()){
            employeeRepository.deleteById(id);
            return "Employee has been deleted successfully";
        }
        return  "No such employee found";
    }
}
