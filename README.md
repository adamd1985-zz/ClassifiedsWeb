# Webapp RAD exercise with SpringBoot

In this exercise I will create a Classifieds Web Application with RAD and technologies and BDD principles.

## The Requirements


* Page for Ad creation with fields:
	* Title (Required)
	* Price (Optional, but if present only numbers)
	* Descriptive text (Required)
	* Contact email (Required)
	* Contact phone (Optional)
	* City (Required)
	* Category (One of these three: Cars, Phones, Services)
* Page to list Ads - all or **one** category.
* Ads must be persisted to a DB.

## The Technologies used

* Spring Boot - out of the box:
	* MVC Servers, Security and Rest interfaces.
	* Datarepositories and database connections.
	* Boiler-plate code for properties and other utilities.
* Cucumber to test user stories within the integration tests.
* Angular and Bootstrap for quick dynamic apps.
* Jasmine for testing web front logic.
* Git for source control.

## Methodology

Using simple sprints within a day the following can be achieved:

* Stage 1: Skeleton App with necessary wirings.
* Stage 2: Middleware logic - handle rest and tests.
* Stage 3: Backend logic - datarepo and database plus tests.
* Stage 4: Frontend logic - website.
* OPTIONAL stage 5: security 

## Development

### Stage 1: Simple Skeleton code

I prepared the code base here to build on.

Functional administration controller was prepared to prove liveliness of the wiring:

org.adam.controller.AdminController

and its simple integration test: 

org.adam.integrationtest.AdminControllerIT

Which proves the REST contracts in a standalone environment.


### Stage 2/3: Functional middleware and Data repo

The next phase was to provide business logic in the form of a REST controller:

org.adam.controller.ClassifiedController

and its integration test:

org.adam.integrationtest.ClassifiedControllerIT

This controller creates a life spring driven environment (unlike the admin test which works standalone) 
and hits the rest controller with the given rest call and checks the HATEOS returns.

Using mockito, we inject the mocks within the spring context to abstract the controller from the persistence
layer, thus this is a top-down integration test.

The persistence layer was built upon using Spring JPA technology backed by the lightweight H2 database.
The Data Repository logic within spring will construct the necessary CRUD repository, session management and 
data source to backup our domain repository - all abstracted from the developer until further manipulation is
required. 

### Stage 4: Webfront

Wired in using Angular and boorstrap with minimal boilerplate code to handle view manipulation.
Angular seamlessly wired in to the Rest controllers using ngResource. 
Angular validation used to control input of the classified.

All are packaged alongside webjars, making this webapp as streamline as posible with minimal effort to 
run and maintain. 

## Planned Improvements and TODOs

* Proper HATEOS returns with links to access entities.
* Better form validations (which field is failing).
* Pagination
* Convert to proper WAR for scaling.
* Replace H2 with MySql or another DB for scaling to larger dataload.

-------------------------------------------------------------------------------

Copyright © 2014 Adam Darmanin. All rights reserved

