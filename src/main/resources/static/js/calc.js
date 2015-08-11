angular.module('demo', [])

    .controller('Calc', function($scope, $http) {
        $http.get('api/countries/codes').success(function(data) {
            $scope.countries = data;
        })

        $scope.selectedCountry = {
            code: ''
        };

        $scope.fixedCost = {};

        $scope.pickCountry = function(value) {
            var req = {
             method: 'GET',
             url: 'api/countries/'+value
            }

            $http(req).success(function(data) {
                //reset previous wage calculation
                $scope.wage = {};
                $scope.selectedCountry = data;
                if (data.economicFactors) {
                    $scope.fixedCost.amount = data.economicFactors.fixedCosts.amount;
                    $scope.fixedCost.currency = data.economicFactors.fixedCosts.currencyUnit.code;
                }
            }).error(function(data){
                $scope.wage = {};
            })
        }

        $scope.calculateWage = function(dayRate, countryCode) {
            var req = {
                  method: 'GET',
                  url: 'api/wage/calculate/?',
                  params: {
                    dayRate: dayRate,
                    dayRateType: 'NET',
                    countryCode: countryCode
                  }
            }
            $http(req).success(function(data) {
                $scope.wage = data;
                $scope.fixedCost.amount = data.metadata.fixedCosts.amount;
                $scope.fixedCost.currency = data.metadata.fixedCosts.currencyUnit.code;
            }).error(function(data){
                $scope.wage = {};
                $scope.fixedCost.amount = $scope.selectedCountry.economicFactors.fixedCosts.amount;
                $scope.fixedCost.currency = $scope.selectedCountry.economicFactors.fixedCosts.currencyUnit.code;
            })
        }
    });