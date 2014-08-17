/* 
 * Copyright 2014 Adam Darmanin.
 * 
 * Classified controller.
 */

'use strict';

var module = angular
		.module("controller.classifieds", [ "service.classifieds" ]);

/**
 * This controller will populate the view with the loaded domain objects, offer
 * filtering of the view and add new items.
 */
var cntrl = module.controller('classifiedsCtrl', [ '$scope', 'classifiedsService',
		function($scope, classifiedsService) {
			var FILTER_CATEGORY_ALL = "ALL";

			$scope.classifieds = classifiedsService.query();
			$scope.newClassified = {};
			$scope.categoryFilter = FILTER_CATEGORY_ALL;

			/**
			 * Filters the list with the selected attributes.
			 * 
			 * Some optimisation not to refresh page if no new filters were
			 * selected.
			 */
			$scope.filterByCategory = function(newFilter) {
				if ($scope.categoryFilter !== newFilter) {
					if ($scope.categoryFilter === FILTER_CATEGORY_ALL) {
						$scope.classifieds = classifiedsService.query();
					} else {
						$scope.classifieds = classifiedsService.query({
							attribute : "categories",
							id : $scope.categoryFilter
						});
					}
				}
			}

			/**
			 * Resets new classified to NOOP state.
			 */
			$scope.cancel = function() {
				newClassified = {};
			}

			/**
			 * Sends to server the newly created classified and resets the place
			 * holder to NOOP.
			 */
			$scope.save = function() {
				$scope.classifieds.save($scope.newClassified);
				$scope.cancel();
			}
		} ]);