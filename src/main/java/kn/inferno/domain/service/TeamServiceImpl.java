package kn.inferno.domain.service;


import kn.inferno.domain.model.Team;
import kn.inferno.domain.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamServiceImpl implements TeamService {
    private TeamRepository teamRepository;

    @Autowired
    public void setTeamRepository (TeamRepository teamRepository) {this.teamRepository= teamRepository;}

    @Override
    public Iterable<Team> listAllTeams() {
        return teamRepository.findAll();
    }

    @Override
    public Team getTeamById(Integer id) {
        return teamRepository.findOne(id);
    }

    @Override
    public Team saveTeam(Team team) {
        return teamRepository.save(team);
    }

    @Override
    public Team updateTeam(Team team) {
        return teamRepository.save(team);
    }

    @Override
    public void deleteTeam(Integer id) {
        teamRepository.delete(id);
    }

    @Override
    public Iterable<Team> getTeamsByName(String name) {
        return teamRepository.findByNameContainingIgnoreCaseOrderByName(name);
    }

    @Override
    public Iterable<Team> getTeamsByDepartment(String department) {
//        if (Department.values().)
//        {
//
//        }
        return teamRepository.findByDepartmentIdContainingOrderByDepartmentId(1); // owerwrite it later
    }
}
