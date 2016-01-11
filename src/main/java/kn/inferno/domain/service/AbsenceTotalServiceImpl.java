package kn.inferno.domain.service;


import kn.inferno.domain.model.AbsenceTotal;
import kn.inferno.domain.repository.AbsenceTotalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AbsenceTotalServiceImpl implements AbsenceTotalService{
    private AbsenceTotalRepository absenceTotalRepository;

    @Autowired
    public void setAbsenceTotalRepository (AbsenceTotalRepository absenceTotalRepository){this.absenceTotalRepository=absenceTotalRepository;}

    @Override
    public Iterable<AbsenceTotal> listAllAbsenceTotals() {
        return absenceTotalRepository.findAll();
    }

    @Override
    public AbsenceTotal getAbsenceTotalById(Integer id) {
        return absenceTotalRepository.findOne(id);
    }

    @Override
    public AbsenceTotal saveAbsenceTotal(AbsenceTotal absenceTotal) {
        return absenceTotalRepository.save(absenceTotal);
    }

    @Override
    public void deleteAbsenceTotal(Integer id) {
        absenceTotalRepository.delete(id);
    }
}
