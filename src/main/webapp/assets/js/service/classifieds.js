/* 
 * Copyright 2014 Adam Darmanin.
 * 
 * Classified services.
 */

'use strict';

var module = angular.module("services.classifieds", [ 'ngResource' ]);

/**
 * Classified service, maps directly to the classified domain object.
 * 
 * note :attribute and :id are subdomains - being either category list (eg:
 * /classifieds/categories/CARS- for category attributes with id CARS) or a
 * direct entity (eg. /classifieds/123 - for the classified list attribute 123,
 * this being an individual classified).
 * 
 * TODO: Error checks and error handling.
 */
var service = module.factory('classifiedsService', [ '$resource',
		function($resource) {
			return $resource('/classifieds-web/classifieds/:attribute/:id');
		} ]);