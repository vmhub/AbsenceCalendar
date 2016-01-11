package kn.inferno.domain.repository;

import kn.inferno.domain.model.Absence;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;
import java.util.List;

public interface AbsenceRepository  extends CrudRepository<Absence, Integer> {
    List<Absence> findByDateFromBetween(Date dateFrom, Date dateTo);
    List<Absence> findByEmployeeIdAndDateFromBetween(int employeeId, Date dateFrom, Date dateTo);
    List<Absence> findByEmployeeIdAndDateToBetween(int employeeId, Date dateFrom, Date dateTo);
    List<Absence> findByEmployeeId (int employeeId);
}
