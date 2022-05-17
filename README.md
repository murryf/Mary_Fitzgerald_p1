# Mary_Fitzgerald_p1
------
An app for employees to claim expenses. Users can post their own expenses, and have them approved or denied. After an expense is approved or denied it cannot be modified. 

------
### Technology Used:

- AWS
  - Elastic Beanstalk
  - EC2
  - RDS
  - S3
- Java
  - RESTful
  - Spring
  - Javalin
  - JUnit
- Other
  - Postman
  - PostgrSQL
  - DBever

------
### Features

- employee cases
  - get employee by ID
  - add an employee to database
  - get all employees
  - update an employee name
  - delete an employee with no expenses
- expense cases
  - post a new expense
  - get an expense by id number
  - delete an unreviewed expense
  - get all expenses, filtered by status
  - update an unreviewed expense
  - Update status
- Employee nested routes
  - get employee specific expenses
  - post an expense from an employee

### To-Do
- error handling
- input validation
- delete employee, even with previous expenses

-----
### Getting Started
- Must have:
  - Postman
  - InteliJ
  - Java 8
 
 To download this application, run "git clone https://github.com/murryf/Mary_Fitzgerald_p1.git"  in the command line shell of your choosing.
 
 The backend of this application can be reached at http://maryfitzgeraldp1-env.eba-rhwpi2pb.us-west-2.elasticbeanstalk.com through Postman. 

