'use strict';

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
            return $http.delete('http://localhost:8090/deleteteam'+id)
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