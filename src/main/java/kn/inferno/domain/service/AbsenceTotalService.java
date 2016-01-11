package kn.inferno.domain.service;


import kn.inferno.domain.model.AbsenceTotal;

public interface AbsenceTotalService {
    Iterable<AbsenceTotal> listAllAbsenceTotals();
    AbsenceTotal getAbsenceTotalById(Integer id);
    AbsenceTotal saveAbsenceTotal (AbsenceTotal absenceTotal);
    void deleteAbsenceTotal(Integer id);
}
