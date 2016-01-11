package kn.inferno.app.controllers;

import kn.inferno.domain.model.Absence;
import kn.inferno.domain.model.Employee;
import kn.inferno.domain.model.PublicHoliday;
import kn.inferno.domain.service.AbsenceService;
import kn.inferno.domain.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
public class EmployeeController {
    private EmployeeService employeeService;
    private AbsenceService absenceService;

    @Autowired
    public void setEmployeeService (EmployeeService employeeService) {this.employeeService=employeeService;}
    @Autowired
    public void setAbsenceService (AbsenceService absenceService){this.absenceService=absenceService;}

    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public ArrayList<Employee> listAllEmployees() {
        ArrayList<Employee> employees = (ArrayList<Employee>) employeeService.listAllEmployees();
        if(employees.isEmpty()){
            return null;
        } else {
            return employees;
        }
    }

    @RequestMapping(value = "/addemployee", method = RequestMethod.POST)
    public Employee addEmployee (@RequestBody Employee employee){
        try {
        employeeService.saveEmployee(employee);
        return employee;
        } catch (Exception e){
            return null;
        }
    }

    @RequestMapping(value = "/updateemployee{id}", method = RequestMethod.PUT)
    public Employee updateEmployee (@RequestBody @Valid Employee employee, @PathVariable("id") int id){
        try {
            employeeService.saveEmployee(employee);
            return employee;
        } catch (Exception e){
            return null;
        }
    }

    @RequestMapping (value = "/searchemployee{name}")
    public ArrayList<Employee> getEmployeesByName (@PathVariable("name") String name){
        ArrayList<Employee> employees = (ArrayList<Employee>) employeeService.getEmployeesByName(name);
        if(employees.isEmpty()){
            return null;
        } else {
            return employees;
        }
    }

    @RequestMapping (value = "/searchemployee{name}{surename}")
    public ArrayList<Employee> getEmployeesByNameAndSurename (@PathVariable("name") String name,
                                                              @PathVariable("surename") String surename){
        ArrayList<Employee> employees = (ArrayList<Employee>) employeeService.getEmployeesByNameAndSurename(name, surename);
        if(employees.isEmpty()){
            return null;
        } else {
            return employees;
        }
    }

    @RequestMapping(value = "/deleteemployee{id}", method = RequestMethod.GET)
    public ArrayList<Employee> deleteEmployee (@PathVariable ("id") int id){
        ArrayList<Employee> employees = (ArrayList<Employee>) employeeService.listAllEmployees();
        ArrayList<Absence> absences = (ArrayList<Absence>) absenceService.listAllAbsencesByEmployeeId(id);

        for (Absence absence: absences){
            if (absence.getEmployeeId()==id){
                if(employees.isEmpty()){
                    return null;
                } else {
                    return employees;
                }
            }
        }
        if(!employeeService.getEmployeeById(id).equals(null)) {
            employeeService.deleteEmployee(id);
        }
        employees = (ArrayList<Employee>) employeeService.listAllEmployees();
        if(employees.isEmpty()){
            return null;
        } else {
            return employees;
        }
    }
}
