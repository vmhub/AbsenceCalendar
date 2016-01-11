package kn.inferno.domain.service;

import kn.inferno.domain.model.Absence;
import kn.inferno.domain.repository.AbsenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@Service
public class AbsenceServiceImpl implements AbsenceService {
    private AbsenceRepository abscenceRepository;

    @Autowired
    public void setAbscenceRepository (AbsenceRepository abscenceRepository){this.abscenceRepository=abscenceRepository;}

    @Override
    public Iterable<Absence> listAllAbsences() {
        return abscenceRepository.findAll();
    }

    @Override
    public Iterable<Absence> listAllAbsencesByEmployeeId(Integer id) {
        return abscenceRepository.findByEmployeeId(id);
    }

    @Override
    public Absence getAbsenceById(Integer id) {
        return abscenceRepository.findOne(id);
    }

    @Override
    public Absence saveAbsence(Absence absence) {
        return abscenceRepository.save(absence);
    }

    @Override
    public Absence updateAbsence (Absence absence){return abscenceRepository.save(absence);}

    @Override
    public Iterable<Absence> getAbsencesForMonth(int month, int year) {
        int firstDay = 1;
        Calendar calendar = new GregorianCalendar(year, month, firstDay);
        int totalDaysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        Date dateFrom = new Date(year, month, firstDay);
        Date dateTo = new Date(year, month, totalDaysInMonth);

        return abscenceRepository.findByDateFromBetween(dateFrom, dateTo);
    }

    @Override
    public List<Absence> getAbsencesForMonthByEmployee(int employeeId, int month, int year) {
        int firstDay = 1;
        Calendar calendar = new GregorianCalendar(year, month, firstDay);
        int totalDaysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        Date dateFrom = new Date(year-1900, month, firstDay);
        Date dateTo = new Date(year-1900, month, totalDaysInMonth);

//        List<Absence> requiredAbsences = abscenceRepository.findByEmployeeIdAndDateFromBetween(employeeId,
//                dateFrom, dateTo);
//        requiredAbsences.addAll(abscenceRepository.findByEmployeeIdAndDateToBetween(
//                employeeId,dateFrom,dateTo));

        return abscenceRepository.findByEmployeeIdAndDateToBetween(
                employeeId,dateFrom,dateTo);
    }

    @Override
    public void deleteAbsence(Integer id) {
        abscenceRepository.delete(id);
    }
}
