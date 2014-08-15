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
