package kn.inferno.app.controllers;

import kn.inferno.domain.model.PublicHoliday;
import kn.inferno.domain.service.PublicHolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
public class PublicHolidayController {
    private PublicHolidayService publicHolidayService;

    @Autowired
    public void setPublicHolidayService (PublicHolidayService publicHolidayService){this.publicHolidayService=publicHolidayService;}

    @RequestMapping(value = "/publicholidays", method = RequestMethod.GET)
    public ArrayList<PublicHoliday> listAllPublicHolidays(){
        ArrayList<PublicHoliday> publicHolidays = (ArrayList<PublicHoliday>) publicHolidayService.listAllPublicHolidays();
        if(publicHolidays.isEmpty()){
            return null;
        } else {
            return publicHolidays;
        }
    }

    @RequestMapping(value = "/addpublicholiday", method = RequestMethod.POST)
    public PublicHoliday addPublicHoliday (@RequestBody @Valid PublicHoliday publicHoliday){
        try {
            publicHolidayService.savePublicHoliday(publicHoliday);
            return publicHoliday;
        } catch (Exception e){
            return null;
        }
    }

    @RequestMapping(value = "/updatepublicholiday{id}", method = RequestMethod.POST)
    public PublicHoliday updatePublicHoliday (@RequestBody @Valid PublicHoliday publicHoliday, @PathVariable("id") int id){
        try {
            publicHoliday.setId(id);
            publicHolidayService.savePublicHoliday(publicHoliday);
            return publicHoliday;
        } catch (Exception e){
            return null;
        }
    }

    @RequestMapping (value = "/deletepublicholiday{id}", method = RequestMethod.GET)
    public ArrayList<PublicHoliday> deletePublicHoliday (@PathVariable("id") int id){
        if(!publicHolidayService.getPublicHolidayById(id).equals(null)){
            publicHolidayService.deletePublicHoliday(id);
        }
        ArrayList<PublicHoliday> publicHolidays = (ArrayList<PublicHoliday>) publicHolidayService.listAllPublicHolidays();
        if(publicHolidays.isEmpty()){
            return null;
        } else {
            return publicHolidays;
        }
    }
}
