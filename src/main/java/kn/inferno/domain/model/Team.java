package kn.inferno.domain.model;

import kn.inferno.domain.model.constants.Department;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="team")
public class Team {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TEAM_SEQ")
    @SequenceGenerator(name="TEAM_SEQ", sequenceName="TEAM_SEQ", allocationSize=1)
    private int id;

    @NotNull
    @Column(name = "name")
    private String name;
    @NotNull
    @Column(name = "department")
    private int departmentId;

    public int getId() {
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

    public int getDepartmentId() { return departmentId; }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public Department getDepartment () {return Department.parse(departmentId);}

    public void setDepartment (Department department) {this.departmentId = department.getValue();}
}
