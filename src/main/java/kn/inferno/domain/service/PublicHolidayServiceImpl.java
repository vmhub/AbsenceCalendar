package kn.inferno.domain.service;

import kn.inferno.domain.model.PublicHoliday;
import kn.inferno.domain.repository.PublicHolidayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class PublicHolidayServiceImpl implements PublicHolidayService {
    private PublicHolidayRepository publicHolidayRepository;

    @Autowired
    public void setPublicHolidayRepository (PublicHolidayRepository publicHolidayRepository){this.publicHolidayRepository=publicHolidayRepository;}

    @Override
    public Iterable<PublicHoliday> listAllPublicHolidays() {
        return publicHolidayRepository.findAll();
    }

    @Override
    public Iterable<Date> getAllPublicHolidayDates() {
        List<Date> allPublicHolidayDates = new ArrayList<Date>();
        List<PublicHoliday> publicHolidayList = (List<PublicHoliday>) publicHolidayRepository.findAll();
        if(!publicHolidayList.isEmpty()) {
            for (PublicHoliday publicHoliday : publicHolidayList) {
                allPublicHolidayDates.add(publicHoliday.getDate());
            }
        }
        return allPublicHolidayDates;
    }

    @Override
    public PublicHoliday getPublicHolidayByDate(Date date) {
        return publicHolidayRepository.findByDate(date);
    }

    @Override
    public PublicHoliday getPublicHolidayById(Integer id) {
        return publicHolidayRepository.findOne(id);
    }

    @Override
    public PublicHoliday savePublicHoliday(PublicHoliday publicHoliday) {
        return publicHolidayRepository.save(publicHoliday);
    }

    @Override
    public void deletePublicHoliday(Integer id) {
       publicHolidayRepository.delete(id);
    }
}
