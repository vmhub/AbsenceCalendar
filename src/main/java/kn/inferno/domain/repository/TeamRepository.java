package kn.inferno.domain.repository;


import kn.inferno.domain.model.Team;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TeamRepository extends CrudRepository<Team, Integer> {
    List<Team> findByNameContainingIgnoreCaseOrderByName (String name);
    List<Team> findByDepartmentIdContainingOrderByDepartmentId (int department_id);
}