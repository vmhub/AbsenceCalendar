package kn.inferno.domain.repository;

import kn.inferno.domain.model.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
    List<Employee> findByNameContainingIgnoreCaseOrderByName (String name);
    List<Employee> findByNameAndSurenameContainingIgnoreCaseOrderByName (String name, String surename);
    List<Employee> findByTeamId (int teamId);
}
