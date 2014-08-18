/* 
 * Copyright 2014 Adam Darmanin.
 * 
 * Classified controller.
 */

'use strict';

var module = angular.module("controller.classifieds",
		[ "services.classifieds" ]);

/**
 * This controller will populate the view with the loaded domain objects, offer
 * filtering of the view and add new items.
 */
var cntrl = module.controller('classifiedsCtrl', [ '$scope',
		'classifiedsService', function($scope, classifiedsService) {
			var FILTER_CATEGORY_ALL = "ALL";

			$scope.classifieds = classifiedsService.query();
			$scope.newClassified = {
				title : "Title",
				price : "00",
				email : "XXX@XX.com",
				phone : "XXXXXXXXXX",
				city : "City",
				category : "CARS"
			};
			$scope.categoryFilter = FILTER_CATEGORY_ALL;

			/**
			 * Filters the list with the selected attributes.
			 * 
			 */
			$scope.filterByCategory = function() {
				if ($scope.categoryFilter === FILTER_CATEGORY_ALL) {
					$scope.classifieds = classifiedsService.query();
				} else {
					$scope.classifieds = classifiedsService.query({
						attribute : "categories",
						id : $scope.categoryFilter
					});
				}
			}

			/**
			 * Resets new classified to NOOP state.
			 */
			$scope.cancel = function() {
				$scope.newClassified = {
					title : "Title",
					price : 0,
					email : "XXX@XX.com",
					phone : "XXXXXXXXXX",
					city : "City",
					category : "CARS"
				};
			}

			/**
			 * Sends to server the newly created classified and resets the place
			 * holder to default.
			 */
			$scope.save = function() {
				classifiedsService.save($scope.newClassified, function(newId) {
					// Save was successful and we got back the Items ID.

					$scope.filterByCategory();
					$scope.cancel();

					$('#addClassifiedModalDiv').modal('hide');
				});
			}
		} ]);