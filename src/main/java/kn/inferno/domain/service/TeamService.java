package kn.inferno.domain.service;


import kn.inferno.domain.model.Team;


public interface TeamService {

    Iterable<Team> listAllTeams();
    Team getTeamById(Integer id);
    Team saveTeam(Team team);
    Team updateTeam (Team team);
    void deleteTeam(Integer id);
    Iterable<Team> getTeamsByName(String name);
    Iterable<Team> getTeamsByDepartment (String department);
}
