/*function Calc($scope, $http) {
    $http.get('/api/countries').
        success(function(data) {
            $scope.countries = data;
        });
}*/

angular.module('demo', []).controller('Calc', function($scope, $http) {
	$http.get('api/countries').success(function(data) {
		$scope.countries = data;
	})
});