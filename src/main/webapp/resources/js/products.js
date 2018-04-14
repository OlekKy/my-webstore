var productsApp = angular.module('productsApp', []);
productsApp.controller('productsCtrl', function ($scope, $http) {
	$scope.showProducts = function() {
		$http.get('/webstore/products/all')
		.success(function(data){
			$scope.product = data;
		});
	};
});