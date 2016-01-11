package kn.inferno.domain.service;


import kn.inferno.domain.model.Absence;

import java.util.List;

public interface AbsenceService {
    Iterable<Absence> listAllAbsences();
    Iterable<Absence> listAllAbsencesByEmployeeId (Integer id);
    Absence getAbsenceById(Integer id);
    Absence saveAbsence(Absence absence);
    Absence updateAbsence (Absence absence);
    Iterable<Absence> getAbsencesForMonth (int month, int year);
    List<Absence> getAbsencesForMonthByEmployee (int employeeId, int month, int year);
    void deleteAbsence(Integer id);
}
