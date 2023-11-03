package com.openclassrooms.webapp.service;

import com.openclassrooms.webapp.model.Employee;
import com.openclassrooms.webapp.repository.EmployeeProxy;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class EmployeeService {
    @Autowired
    private EmployeeProxy employeeProxy;

    public Employee getEmployee(final int id) {
        return employeeProxy.getEmployee(id);
    }

    public Iterable<Employee> getEmployees() {
        return employeeProxy.getEmployees();
    }

    public void deleteEmployee(final int id) {
        employeeProxy.deleteEmployee(id);;
    }

    public Employee addEmployee(Employee employee) {
        // Règle de gestion : Le nom de famille doit être mis en majuscule.
        employee.setLastName(employee.getLastName().toUpperCase());
        return employeeProxy.createEmployee(employee);
    }

    public Employee updateEmployee(Employee employee) {
        // Règle de gestion : Le nom de famille doit être mis en majuscule.
        System.out.println(employee.getId());
        employee.setLastName(employee.getLastName().toUpperCase());
        return employeeProxy.updateEmployee(employee);
    }

}
