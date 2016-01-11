package kn.inferno.domain.model;


import kn.inferno.domain.model.constants.AbsenceType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Entity
@Table(name="absence")
public class Absence {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ABSENCE_SEQ")
    @SequenceGenerator(name="ABSENCE_SEQ", sequenceName="ABSENCE_SEQ", allocationSize=1)
    private int id;

    @NotNull
    @Column(name = "date_from")
    private Date dateFrom;

    @NotNull
    @Column(name = "date_to")
    private Date dateTo;

    @NotNull
    @Column(name = "absence_type_id")
    private int absenceTypeId;

    @NotNull
    @Column(name = "employee_id")
    private int employeeId;

    @NotNull
    @Column(name = "commentary")
    private String comment;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {this.dateFrom = dateFrom;}

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {this.dateTo = dateTo;}

    public int getAbsenceTypeId() {
        return absenceTypeId;
    }

    public void setAbsenceTypeId(int absenceTypeId) {
        this.absenceTypeId = absenceTypeId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public AbsenceType getAbsenceType (){return AbsenceType.parse(absenceTypeId);}

    public void setAbsenceType (AbsenceType absenceType) {this.absenceTypeId = absenceType.getValue();}
}
