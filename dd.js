// ETOT KOD - PROSTO PIZDEC
var app =angular.module('modal.editing', ['ui.grid', 'ui.grid.edit', 'ui.bootstrap', 'schemaForm','ui.grid.pagination','ui.router'])


    .constant('PersonSchema', {

        type: 'object',

        properties: {
            id: { type: 'string', title: 'id' },

            department: { type: 'string', title: 'Department' },

            team_name: { type: 'string', title: 'Team name' },
        }

    })   .constant('emp', {

            type: 'object',

            properties: {

                id: { type: 'string', title: 'id' },

                name: { type: 'string', title: 'name' },

                surname: { type: 'string', title: 'surname' },

                roleId: { type: 'string', title: 'roleId' },

                teamId: { type: 'string', title: 'teamId' },

                departmentId: { type: 'string', title: 'departmentId' },

                locationId: { type: 'string', title: 'locationId' },

                location: { type: 'string', title: 'location' },

                role: { type: 'string', title: 'role' },

                department: { type: 'string', title: 'department' }
                // 'address.city': { type: 'string', title: 'City' }

            }

        })

    .controller('MainCtrl', MainCtrl)
    .controller('Main2Ctrl', Main2Ctrl)
    .controller('RowEditCtrl', RowEditCtrl)
    .service('RowEditor', RowEditor)
    .controller('RowEditCtrl2', RowEditCtrl2)
    .service('RowEditor2', RowEditor2)
;
app.config(['$stateProvider', function ($stateProvider) {

    var home= {
            url: '/home',
            templateUrl: 'views/Home.html',
            controller: 'HomeCtrl'

        },
        contactUs= {
            url: '/contactus',
            templateUrl: 'views/ContactUs.html',
            controller: 'Main2Ctrl'
        },

        aboutUs= {
            url: '/aboutus',
            templateUrl: 'views/AboutUs.html',
            controller: 'MainCtrl'
        };
    $stateProvider
        .state('aboutus', aboutUs)
        .state('contactUs', contactUs)
        .state('home', home);
}]).controller('HomeCtrl', function ($scope) {
    $scope.message = '1'
});
Main2Ctrl.$inject = ['$http', 'RowEditor2'];

function Main2Ctrl ($http, RowEditor2) {

    var vm = this;



    vm.editRow= RowEditor2.editRow2;



    vm.gridOptions = {
        paginationPageSizes: [5, 10, 20],
        paginationPageSize: 20,
        showGridFooter: true,
        showColumnFooter: true,
        enableFiltering: true,

        columnDefs: [

            { field: 'edit', name: '',enableCellEdit: false,
                enableColumnMenu: false,
                enableFiltering: false,
                enableHiding: false,
                enableSorting: false, cellTemplate: 'edit-button.html', width: 34 },

            { name: 'id' },

            { name: 'name' },

            { name: 'surname' },
            { name: 'roleId' },
            { name: 'teamId' },
            {field: 'departmentId', name: 'deptId' },
            { name: 'locationId' },


            { name: 'location' },
            { name: 'role' },
            { field: 'department',name: 'dept' }
            //     { name: 'City', field: 'address.city' },
            // {"id":4,"name":"dfd","surename":"dfds","roleId":1,"teamId":1,"departmentId":1,"locationId":1,"location":"HAM","role":"TM","department":"GI_PK"}]
        ]

    };



    $http.get('data2.json')

        .success(function (data) {

            vm.gridOptions.data = data;

        });

}
MainCtrl.$inject = ['$http', 'RowEditor'];

function MainCtrl ($http, RowEditor) {

    var vm = this;



    vm.editRow = RowEditor.editRow;



    vm.gridOptions = {
        paginationPageSizes: [5, 10, 20],
        paginationPageSize: 20,
        showGridFooter: true,
        showColumnFooter: true,
        enableFiltering: true,
        columnDefs: [

            { field: 'edit', name: '',enableCellEdit: false,
                enableColumnMenu: false,
                enableFiltering: false,
                enableHiding: false,
                enableSorting: false, cellTemplate: 'edit-button.html', width: 34 },

            {name: 'id'},
            {name: 'department' },
            {name: 'team_name'}

        ]

    };



    $http.get('data.json')

        .success(function (data) {

            vm.gridOptions.data = data;

        });

}


RowEditor.$inject = ['$rootScope', '$modal'];
RowEditor2.$inject = ['$rootScope', '$modal'];
function RowEditor($rootScope, $modal) {

    var service = {};

    service.editRow = editRow;



    function editRow(grid, row) {

        $modal.open({

            templateUrl: 'edit-modal.html',

            controller: ['$modalInstance', 'PersonSchema', 'grid', 'row', RowEditCtrl],

            controllerAs: 'vm',

            resolve: {

                grid: function () { return grid; },

                row: function () { return row; }

            }

        });

    }



    return service;

}


function RowEditCtrl($modalInstance, PersonSchema, grid, row) {

    var vm = this;



    vm.schema = PersonSchema;

    vm.entity = angular.copy(row.entity);

   vm.form = [

       'id',

       'department',

       'team_name',

   ];


    vm.save = save;



    function save() {

        // Copy row values over

        row.entity = angular.extend(row.entity, vm.entity);

        $modalInstance.close(row.entity);

    }

}
/// PRO COPY PASTE NAHUY

function RowEditor2($rootScope, $modal) {

    var service = {};

    service.editRow2 = editRow2;



    function editRow2(grid, row) {

        $modal.open({

            templateUrl: 'edit-modal.html',

            controller: ['$modalInstance', 'emp', 'grid', 'row', RowEditCtrl2],

            controllerAs: 'vm',

            resolve: {

                grid: function () { return grid; },

                row: function () { return row; }

            }

        });

    }



    return service;

}


function RowEditCtrl2($modalInstance, PersonSchema, grid, row) {

    var vm = this;



    vm.schema = PersonSchema;

    vm.entity = angular.copy(row.entity);

    vm.form = [

        'id' ,
        'name',
        'surname',
        'roleId' ,
        'teamId',
        'departmentId',
        'locationId',
        'location',
        'role',
        'department',

    ];


    vm.save = save;



    function save() {

        // Copy row values over

        row.entity = angular.extend(row.entity, vm.entity);

        $modalInstance.close(row.entity);

    }

}
