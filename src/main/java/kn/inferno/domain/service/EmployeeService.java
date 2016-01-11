package kn.inferno.domain.service;


import kn.inferno.domain.model.Employee;

public interface EmployeeService {
    Iterable<Employee> listAllEmployees();
    Employee getEmployeeById(Integer id);
    Employee saveEmployee(Employee employee);
    void deleteEmployee(Integer id);
    Iterable<Employee> getEmployeesByName(String name);
    Iterable<Employee> getEmployeesByNameAndSurename(String name, String surename);
    Iterable<Employee> getEmployeesByTeam (int teamId);
}
