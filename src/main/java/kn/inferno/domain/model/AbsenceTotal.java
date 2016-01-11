package kn.inferno.domain.model;

import kn.inferno.domain.model.constants.AbsenceType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Entity
@Table(name="absence_total")
public class AbsenceTotal {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ABSENCE_TOTAL_SEQ")
    @SequenceGenerator(name="ABSENCE_TOTAL_SEQ", sequenceName="ABSENCE_TOTAL_SEQ", allocationSize=1)
    private int id;

    @NotNull
    @Column(name = "employee_id")
    private int employeeId;

    @NotNull
    @Column(name = "absence_type_id")
    private int absenceTypeId;

    @NotNull
    @Column(name = "year")
    private Date year;

    @NotNull
    @Column (name="absence_total")
    private int absenceTotal;

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public int getEmployeeId() {return employeeId;}

    public void setEmployeeId(int employeeId) {this.employeeId = employeeId;}

    public int getAbsenceTypeId() {return absenceTypeId;}

    public void setAbsenceTypeId(int absenceTypeId) {this.absenceTypeId = absenceTypeId;}

    public Date getYear() {return year;}

    public void setYear(Date year) {this.year = year;}

    public AbsenceType getAbsenceType (){return AbsenceType.parse(absenceTypeId);}

    public void setAbsenceType (AbsenceType absenceType) {this.absenceTypeId = absenceType.getValue();}

    public int getAbsenceTotal() {
        return absenceTotal;
    }

    public void setAbsenceTotal(int absenceTotal) {
        this.absenceTotal = absenceTotal;
    }
}
