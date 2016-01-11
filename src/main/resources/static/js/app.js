'use strict';

var app = angular.module('myDemoApp', ['ngTouch','ngRoute','ui.grid','ui.router','ui.grid.pagination']);
app.config(function ($stateProvider) {

    $stateProvider
        .state('employees', {
            templateUrl: 'AboutUs.html',
            controller: 'MainCtrl',
            url:'/employees'
        })
        .state('teams', {
            templateUrl: 'ContactUs.html',
            controller: 'ContactUsCtrl',
            url:'/contactus'
        })
        .state('home', {
            templateUrl: 'Home.html',
            controller: 'HomeCtrl',
            url:'/home'
        })
        .state('editteams',{
            templateUrl: 'EditTeam.html',
            controller: 'TeamController',
            url:'/teams'
        });

});


app.factory('TeamService', ['$http', '$q', function($http, $q){

    return {

        fetchAllTeams: function() {
            return $http.get('http://localhost:8090/teams')
                .then(
                    function(response){
                        return response.data;
                    },
                    function(errResponse){
                        console.error('Error while fetching teams');
                        return $q.reject(errResponse);
                    }
                );
        },

        createTeam: function(team){
            return $http.post('http://localhost:8090/addteam', team)
                .then(
                    function(response){
                        return response.data;
                    },
                    function(errResponse){
                        console.error('Error while creating team');
                        return $q.reject(errResponse);
                    }
                );
        },

        updateTeam: function(team, id){
            return $http.put('http://localhost:8090/updateteam'+id, team)
                .then(
                    function(response){
                        return response.data;
                    },
                    function(errResponse){
                        console.error('Error while updating team');
                        return $q.reject(errResponse);
                    }
                );
        },

        deleteTeam: function(id){
            return $http.get('http://localhost:8090/deleteteam'+id)
                .then(
                    function(response){
                        return response.data;
                    },
                    function(errResponse){
                        console.error('Error while deleting team');
                        return $q.reject(errResponse);
                    }
                );
        }

    };

}]);


app.controller('MainCtrl', ['$scope', '$http', function ($scope, $http) {

    var vm = this;
    vm.editRow = RowEditor.editRow;

    vm.gridOptions1 = {
        paginationPageSizes: [5, 10, 20],
        paginationPageSize: 20,
        showGridFooter: true,
        showColumnFooter: true,
        enableFiltering: true,
        columnDefs: [
            {field: 'edit',name: '',enableCellEdit: false,
                enableColumnMenu: false,
                enableFiltering: false,
                enableHiding: false,
                enableSorting: false,
                                },
            {field: 'id'},
            {field: 'department' },
            {field: 'name'}
        ]
    };


    $http.get('http://localhost:8090/teams')
        .success(function (data) {

            vm.gridOptions1.data = data;
        });

    function editRow(grid, row) {
        $modal.open({
            templateUrl: 'table_edit_modal.html',
            controller: ['$modalInstance', 'grid', 'row', RowEditCtrl],
            controllerAs: 'vm',
            resolve: {
                grid: function () { return grid; },
                row: function () { return row; }
            }
        });
    }

}]).controller('ContactUsCtrl', function ($scope, $http) {
    $http.get('http://localhost:8090/teams').success(function(data){
        $scope.message = data;
    })
}).controller('HomeCtrl', function ($scope) {
    $scope.message = '1'
}).controller('TeamController', ['$scope', 'TeamService', function($scope, TeamService) {
    var self = this;
    self.team={id:null,name:'',departmentId:'1',department:''};
    self.teams=[];

    $scope.increment = function(team){
        team.id.count += 1;
    }

    self.fetchAllTeams = function(){
        TeamService.fetchAllTeams()
            .then(
                function(d) {
                    self.teams = d;
                },
                function(errResponse){
                    console.error('Error while fetching teams');
                }
            );
    };

    self.createTeam = function(team){

        TeamService.createTeam(team)
            .then(
                self.fetchAllTeams,
                function(errResponse){
                    console.error('Error while creating team.');
                }
            );
    };

    self.updateTeam = function(team, id){
        TeamService.updateTeam(team, id)
            .then(
                self.fetchAllTeams,
                function(errResponse){
                    console.error('Error while updating team.');
                }
            );
    };

    self.deleteTeam = function(id){
        TeamService.deleteTeam(id)
            .then(
                self.fetchAllTeams,
                function(errResponse){
                    console.error('Error while deleting team.');
                }
            );
    };

    self.fetchAllTeams();

    self.submit = function() {
        if(self.team.id===null){
            console.log('Saving New Team', self.team);
            self.createTeam(self.team);
        }else{
            self.updateTeam(self.team, self.team.id);
            console.log('Team updated with id ', self.team.id);
        }
        self.reset();
    };

    self.edit = function(id){
        console.log('id to be edited', id);
        for(var i = 0; i < self.teams.length; i++){
            if(self.teams[i].id === id) {
                self.team = angular.copy(self.teams[i]);
                break;
            }
        }
    };

    self.remove = function(id){
        console.log('id to be deleted', id);
        if(self.team.id === id) {//clean form if the team to be deleted is shown there.
            self.reset();
        }
        self.deleteTeam(id);
    };


    self.reset = function(){
        self.user={id:null,name:'',departmentId:'',department:''};
        $scope.teamForm.$setPristine(); //reset Form
    };

}]);



RowEditor.$inject = [ '$http', '$rootScope', '$modal' ];

function RowEditor($http, $rootScope, $modal) {

    var service = {};
    service.editRow = editRow;

    function editRow(grid, row) {
        $modal.open({
            templateUrl : 'service-edit.html',
            controller : [ '$http', '$modalInstance', 'grid', 'row', RowEditCtrl],
            controllerAs : 'vm',
            resolve : {
                grid : function() {
                    return grid;
                },
                row : function() {
                    return row;
                }
            }
        });
    }
    return service;
}


function RowEditCtrl($http, $modalInstance, grid, row) {
    var vm = this;
    vm.entity = angular.copy(row.entity);
    vm.save = save;
    function save() {
        if (row.entity.id == '0') {
            /*
             * $http.post('http://localhost:8080/service/save', row.entity).success(function(response) { $modalInstance.close(row.entity); }).error(function(response) { alert('Cannot edit row (error in console)'); console.dir(response); });
             */
            row.entity = angular.extend(row.entity, vm.entity);
            //real ID come back from response after the save in DB
            row.entity.id = Math.floor(100 + Math.random() * 1000);
            grid.data.push(row.entity);
        } else {
            row.entity = angular.extend(row.entity, vm.entity);
            /*
             * $http.post('http://localhost:8080/service/save', row.entity).success(function(response) { $modalInstance.close(row.entity); }).error(function(response) { alert('Cannot edit row (error in console)'); console.dir(response); });
             */
        }
        $modalInstance.close(row.entity);
    }

    vm.remove = remove;
    function remove() {
        console.dir(row)
        if (row.entity.id != '0') {
            row.entity = angular.extend(row.entity, vm.entity);
            var index = grid.appScope.vm.serviceGrid.data.indexOf(row.entity);
            grid.appScope.vm.serviceGrid.data.splice(index, 1);
            /*
             * $http.delete('http://localhost:8080/service/delete/'+row.entity.id).success(function(response) { $modalInstance.close(row.entity); }).error(function(response) { alert('Cannot delete row (error in console)'); console.dir(response); });
             */
        }
        $modalInstance.close(row.entity);
    }
}