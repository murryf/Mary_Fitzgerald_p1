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
        return this.fname;
    }

    /**
     * Setter for Employee First name
     * @param fname the new name
     * */
    public void setFname(String fname){this.fname=fname;}

    /**
     * Method to return an employee's Last name
     * @return the last name of the employee
     * */
    public String getLName(){
        return this.lname;
    }

    /**
     * Setter for lname field
     * @param lname the new last name
     * */
    public void setLname(String lname){this.lname = lname;}

    /**
     * Method to get an employee's ID number
     * @return the Employee ID number
     * */
    public int getEmployeeID() {
        return this.employeeID;
    }

    /**
     * Method to set the employee ID
     * @param newID the new ID for the employee
     * */
    public void setID(int newID) { this.employeeID = newID;}

    /**
     * To string method override formatted for printing
     * @return the String representation of the employee object
     * */
    @Override
    public String toString() {
        return "Employee{" +
                "employeeID=" + employeeID +
                ", First Name='" + fname + '\'' +
                ", Last Name='" + lname + '\'' +
                '}';
    }
}