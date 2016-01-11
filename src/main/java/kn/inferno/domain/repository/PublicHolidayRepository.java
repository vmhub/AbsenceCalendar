package kn.inferno.domain.repository;


import kn.inferno.domain.model.PublicHoliday;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;

public interface PublicHolidayRepository extends CrudRepository<PublicHoliday, Integer> {
    PublicHoliday findByDate(Date date);
}
