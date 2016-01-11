package kn.inferno.domain.service;


import kn.inferno.domain.model.PublicHoliday;

import java.sql.Date;

public interface PublicHolidayService {
    Iterable<PublicHoliday> listAllPublicHolidays();
    Iterable<Date> getAllPublicHolidayDates();
    PublicHoliday getPublicHolidayByDate (Date date);
    PublicHoliday getPublicHolidayById(Integer id);
    PublicHoliday savePublicHoliday(PublicHoliday publicHoliday);
    void deletePublicHoliday(Integer id);
}
