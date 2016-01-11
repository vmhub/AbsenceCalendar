package kn.inferno.app.controllers;

import kn.inferno.domain.model.*;
import kn.inferno.domain.service.AbsenceService;
import kn.inferno.domain.service.EmployeeService;
import kn.inferno.domain.service.PublicHolidayService;
import kn.inferno.domain.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@RestController
public class CalendarController {
    private EmployeeService employeeService;
    private AbsenceService absenceService;
    private PublicHolidayService publicHolidayService;

    @Autowired
    public void setEmployeeService (EmployeeService employeeService) {this.employeeService=employeeService;}
    @Autowired
    public void setAbsenceService (AbsenceService absenceService){this.absenceService=absenceService;}
    @Autowired
    public void setPublicHolidayService (PublicHolidayService publicHolidayService){this.publicHolidayService = publicHolidayService;}

    @RequestMapping(value = "/teamcalendar/addabsence", method = RequestMethod.POST)
    public Absence addAbsence (@RequestBody Absence absence) {
        try {
            absenceService.saveAbsence(absence);
            setTotalAbsences(employeeService.getEmployeeById(absence.getEmployeeId()));
            return absence;
        } catch (Exception e){
            return null;
        }
    }

       @RequestMapping(value = "/teamcalendar/updateabsence{id}", method = RequestMethod.PATCH)
    public Absence updateAbsence (@RequestBody @Valid Absence absence, @PathVariable ("id") int id) {
        try {
            absence.setId(id);
            absenceService.saveAbsence(absence);
            return absence;
        } catch (Exception e){
            return null;
        }
    }

    @RequestMapping(value = "/teamcalendar/removeabsence{id}")
    public void removeAbsence (@PathVariable("id") int id) {
        if(!absenceService.getAbsenceById(id).equals(null)) {
            absenceService.deleteAbsence(id);
        }
        // minimalism
    }

    @RequestMapping(value = "/teamcalendar{teamId}/{month}", method = RequestMethod.GET)
    public List<EmployeeWithAbsencesPerMonth> listAllEntriesByTeam (@PathVariable("teamId") int teamId, @PathVariable("month") int month){
        int year = Calendar.getInstance().get(Calendar.YEAR);
        List<EmployeeWithAbsencesPerMonth> employeesWithAbsencesPerMonth = generateEmployeeWithAbsencePerMonthByTeam(month, teamId, year);
        if (employeesWithAbsencesPerMonth.isEmpty())
            return null;
        return employeesWithAbsencesPerMonth;
    }

    @RequestMapping(value = "/teamcalendar{month}", method = RequestMethod.GET)
    public List<EmployeeWithAbsencesPerMonth> listAllEntries (@PathVariable ("month") int month){
        int year = Calendar.getInstance().get(Calendar.YEAR);
        List<EmployeeWithAbsencesPerMonth> employeesWithAbsencesPerMonth = generateEmployeeWithAbsencePerMonthForAllEmployees(month, year);
        if (employeesWithAbsencesPerMonth.isEmpty())
            return null;
        return employeesWithAbsencesPerMonth;
    }


    public ArrayList<EmployeeWithAbsencesPerMonth> generateEmployeeWithAbsencePerMonthForAllEmployees (int month, int year){
        ArrayList<Employee> employees = (ArrayList<Employee>) employeeService.listAllEmployees();
        ArrayList<EmployeeWithAbsencesPerMonth> employeesWithAbsencesPerMonth = new ArrayList<EmployeeWithAbsencesPerMonth>();
        for (Employee employee: employees){
            ArrayList<Absence> requiredAbsences = (ArrayList<Absence>) absenceService.getAbsencesForMonthByEmployee((int) employee.getId(), month, year);
            EmployeeWithAbsencesPerMonth employeeWithAbsencesPerMonth = new EmployeeWithAbsencesPerMonth();
            employeeWithAbsencesPerMonth.setEmployee(employee);
            employeeWithAbsencesPerMonth.setAbsencesPerMonth(requiredAbsences);
            employeeWithAbsencesPerMonth.setAbsenceTotals(setTotalAbsences(employee));
            employeesWithAbsencesPerMonth.add(employeeWithAbsencesPerMonth);
        }
        return employeesWithAbsencesPerMonth;
    }

    public ArrayList<EmployeeWithAbsencesPerMonth> generateEmployeeWithAbsencePerMonthByTeam (int month, int teamId, int year){
        ArrayList<Employee> employeesByTeam = (ArrayList<Employee>) employeeService.getEmployeesByTeam(teamId);
        ArrayList<EmployeeWithAbsencesPerMonth> employeesWithAbsencesPerMonth = new ArrayList<EmployeeWithAbsencesPerMonth>();
        for (Employee employee: employeesByTeam){
            ArrayList<Absence> requiredAbsences = (ArrayList<Absence>) absenceService.getAbsencesForMonthByEmployee((int) employee.getId(), month, year);
            EmployeeWithAbsencesPerMonth employeeWithAbsencesPerMonth = new EmployeeWithAbsencesPerMonth();
            employeeWithAbsencesPerMonth.setEmployee(employee);
            employeeWithAbsencesPerMonth.setAbsencesPerMonth(requiredAbsences);
            employeeWithAbsencesPerMonth.setAbsenceTotals(setTotalAbsences(employee));
            employeesWithAbsencesPerMonth.add(employeeWithAbsencesPerMonth);
        }
        return employeesWithAbsencesPerMonth;
    }

    // add year check
    public ArrayList<AbsenceTotal> setTotalAbsences (Employee employee){
        ArrayList<AbsenceTotal> totalAbsences = new ArrayList<AbsenceTotal>();
        ArrayList<Absence> absences = (ArrayList<Absence>) absenceService.listAllAbsencesByEmployeeId((int) employee.getId());

        AbsenceTotal absenceTotalVacation = new AbsenceTotal();
        absenceTotalVacation.setEmployeeId((int) employee.getId());
        absenceTotalVacation.setAbsenceTypeId(1);

        AbsenceTotal absenceTotalOffdays = new AbsenceTotal();
        absenceTotalOffdays.setEmployeeId((int) employee.getId());
        absenceTotalOffdays.setAbsenceTypeId(2);

        AbsenceTotal absenceTotalTraining = new AbsenceTotal();
        absenceTotalTraining.setEmployeeId((int) employee.getId());
        absenceTotalTraining.setAbsenceTypeId(3);

        for (Absence absence : absences) {
            switch (absence.getAbsenceTypeId()) {
                case 1:
                    absenceTotalVacation.setAbsenceTotal(absenceTotalVacation.getAbsenceTotal()+getDaysBetweenDates
                            (absence.getDateFrom(), absence.getDateTo()) -
                            getPublicHolidaysFromPeriod(absence.getDateFrom(), absence.getDateTo()));
                case 2:
                    absenceTotalOffdays.setAbsenceTotal(absenceTotalOffdays.getAbsenceTotal()+getDaysBetweenDates
                            (absence.getDateFrom(), absence.getDateTo()) -
                            getPublicHolidaysFromPeriod(absence.getDateFrom(), absence.getDateTo()));
                case 3:
                    absenceTotalTraining.setAbsenceTotal(absenceTotalTraining.getAbsenceTotal()+getDaysBetweenDates
                            (absence.getDateFrom(), absence.getDateTo()) -
                            getPublicHolidaysFromPeriod(absence.getDateFrom(), absence.getDateTo()));
            }
        }
        totalAbsences.add(absenceTotalVacation);
        totalAbsences.add(absenceTotalOffdays);
        totalAbsences.add(absenceTotalTraining);
        return totalAbsences;
    }

    public List<Date> getDatesBetweenDates(Date startdate, Date enddate)
    {
        List<Date> dates = new ArrayList<Date>();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(startdate);

        while (calendar.getTime().before(enddate))
        {
            Date result = new Date(calendar.getTime().getTime());
            dates.add(result);
            calendar.add(Calendar.DATE, 1);
        }
        return dates;
    }

    public int getDaysBetweenDates(Date startdate, Date enddate)
    {
        int daysBetweenDates = 0;
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(startdate);

        while (calendar.getTime().before(enddate))
        {
            daysBetweenDates++;
            calendar.add(Calendar.DATE, 1);
        }
        return daysBetweenDates;
    }

    public int getPublicHolidaysFromPeriod(Date startdate, Date enddate)
    {
        int publicHolidays = 0;
        ArrayList<Date> publicHolidayDates = (ArrayList<Date>) publicHolidayService.getAllPublicHolidayDates();
        if(!publicHolidayDates.isEmpty()) {
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(startdate);
            while (calendar.getTime().before(enddate)) {
                Date result = new Date(calendar.getTime().getTime());
                if (publicHolidayDates.contains(result)) {
                    publicHolidays++;
                }
                calendar.add(Calendar.DATE, 1);
            }
        }
        return publicHolidays;
    }
}
