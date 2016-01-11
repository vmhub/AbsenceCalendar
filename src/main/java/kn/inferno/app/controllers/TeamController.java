package kn.inferno.app.controllers;

import kn.inferno.domain.model.Employee;
import kn.inferno.domain.model.Team;
import kn.inferno.domain.service.EmployeeService;
import kn.inferno.domain.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
public class TeamController {
    private TeamService teamService;
    private EmployeeService employeeService;

    @Autowired
    public void setEmployeeService (EmployeeService employeeService) {this.employeeService=employeeService;}
    @Autowired
    public void setTeamService (TeamService teamService) {this.teamService=teamService;}

    @RequestMapping(value = "/teams", method = RequestMethod.GET)
    public ArrayList<Team> lisaAllTeams() {
        ArrayList<Team> teams = (ArrayList<Team>) teamService.listAllTeams();
        if(teams.isEmpty()){
            return null;
        } else {
            return teams;
        }
    }

    @RequestMapping(value = "/addteam", method = RequestMethod.POST)
    public Team addTeam (@RequestBody Team team){
        try {
        teamService.saveTeam(team);
        return team; } catch (Exception e){
            return null;
        }
    }

    @RequestMapping(value = "/updateteam{id}", method = RequestMethod.PUT)
    public Team updateTeam (@RequestBody @Valid Team team, @PathVariable("id") int id){
        try {
            team.setId(id);
            teamService.updateTeam(team);
            return team;
        } catch (Exception e){
            return null;
        }
    }

    @RequestMapping(value = "/searchteam{name}")
    public ArrayList<Team> getTeamsByName(@PathVariable("name") String name) {
        ArrayList<Team> teamsByName = (ArrayList<Team>) teamService.getTeamsByName(name);
        if (teamsByName.isEmpty()) {
            return null;
        }
        return teamsByName;
    }

    @RequestMapping(value="/deleteteam{id}", method = RequestMethod.GET)
    public ArrayList<Team> deleteTeam (@PathVariable("id") int id){
        ArrayList<Employee> employees = (ArrayList<Employee>) employeeService.listAllEmployees();
        ArrayList<Team> teams = (ArrayList<Team>) teamService.listAllTeams();

        for (Employee employee: employees){
            if (employee.getTeamId()==id){
                if(teams.isEmpty()){
                    return null;
                } else {
                    return teams;
                }
            }
        }
        if(!teamService.getTeamById(id).equals(null))
        {
            teamService.deleteTeam(id);
        }
        teams = (ArrayList<Team>) teamService.listAllTeams();
        if(teams.isEmpty()){
            return null;
        } else {
            return teams;
        }
    }
}
