package kn.inferno.domain.model;

import kn.inferno.domain.model.constants.Department;
import kn.inferno.domain.model.constants.Location;
import kn.inferno.domain.model.constants.Role;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="employee")
public class Employee {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EMPLOYEE_SEQ")
    @SequenceGenerator(name="EMPLOYEE_SEQ", sequenceName="EMPLOYEE_SEQ", allocationSize=1)
    private int id;

    @NotNull
    @Column(name = "name")
    private String name;
    @NotNull
    @Column(name = "surename")
    private String surename;
    @NotNull
    @Column(name = "role_id")
    private int roleId;
    @NotNull
    @Column(name = "team_id")
    private int teamId;
    @NotNull
    @Column(name = "department_id")
    private int departmentId;
    @NotNull
    @Column(name = "location_id")
    private int locationId;

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int location_id) {
        this.locationId = location_id;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurename() {
        return surename;
    }

    public void setSurename(String surename) { this.surename = surename;}

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int role_id) {
        this.roleId = role_id;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int team_id) {
        this.teamId = team_id;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int department_id) {
        this.departmentId = department_id;
    }

    public Role getRole (){
        return Role.parse(roleId);
    }

    public void setRole (Role role){
        this.roleId = role.getValue();
    }

    public Location getLocation () {return Location.parse(locationId);}

    public void setLocation (Location location) {this.locationId = location.getValue();}

    public Department getDepartment () {return Department.parse(departmentId);}

    public void setDepartment (Department department) {this.departmentId = department.getValue();}
}
