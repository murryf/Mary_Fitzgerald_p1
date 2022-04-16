package dev.fitzgerald.entities;

public class Employee {

    private int employeeID;
    private String fname;
    private String lname;

    /**
     * Method to construct new Employee object.
     * @param fname the first name of the employee
     * @param lname the last name of the employee
     * @param employeeID the employee ID number
     * */
    public Employee(String fname, String lname, int employeeID) {
        this.fname = fname;
        this.lname = lname;
        this.employeeID = employeeID;
    }
    /**
     * Getter for Employee first name
     * @return the String of an employee's name
     * */
    public String getFName() {
        return fname;
    }

    /**
     * Method to return an employee's Last name
     * @return the last name of the employee
     * */
    public String getLName(){
        return lname;
    }

    /**
     * Method to get an employee's ID number
     * @return the Employee ID number
     * */
    public int getEmployeeID() {
        return employeeID;
    }
}
