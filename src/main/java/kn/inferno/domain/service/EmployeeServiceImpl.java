package kn.inferno.domain.service;

import kn.inferno.domain.model.Employee;
import kn.inferno.domain.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;

    @Autowired
    public void setEmployeeRepository (EmployeeRepository employeeRepository) {this.employeeRepository=employeeRepository;}

    @Override
    public Iterable<Employee> listAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Integer id) {
        return employeeRepository.findOne(id);
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(Integer id) {
        employeeRepository.delete(id);
    }

    @Override
    public Iterable<Employee> getEmployeesByName(String name) {
        return employeeRepository.findByNameContainingIgnoreCaseOrderByName(name);
    }

    @Override
    public Iterable<Employee> getEmployeesByNameAndSurename(String name, String surename) {
        return employeeRepository.findByNameAndSurenameContainingIgnoreCaseOrderByName(name, surename);}

    @Override
    public Iterable<Employee> getEmployeesByTeam(int teamId) {
        return employeeRepository.findByTeamId(teamId);
    }
}
