package kn.inferno.domain.model;


import java.util.ArrayList;

public class EmployeeWithAbsencesPerMonth {
    private Employee employee;
    private ArrayList<Absence> absencesPerMonth;
    private ArrayList<AbsenceTotal> absenceTotals;

    public ArrayList<AbsenceTotal> getAbsenceTotals() {
        return absenceTotals;
    }

    public void setAbsenceTotals(ArrayList<AbsenceTotal> absenceTotals) {
        this.absenceTotals = absenceTotals;
    }

    private String teamName;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public ArrayList<Absence> getAbsencesPerMonth() {
        return absencesPerMonth;
    }

    public void setAbsencesPerMonth(ArrayList<Absence> absencesPerMonth) {
        this.absencesPerMonth = absencesPerMonth;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
}
