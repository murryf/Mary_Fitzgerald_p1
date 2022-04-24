package dev.fitzgerald.entities;

public class Expense {



    private int expenseID;
    private int employeeSource;
    private String description;
    private double amount;
    private String status;

    /**
     * Basic expense constructor. Sets status to Pending
     * */
    public Expense(){status = "Pending";}
    /**
     * Main Expense constructor. Takes a String description and an amount to be reported.
     * @param description the description of the expense
     * @param amount the amount of the expense
     * */
    public Expense(int employeeSource, String description, double amount){
        this.status = "Pending";
        this.employeeSource = employeeSource;
        this.description = description;
        this.amount = amount;
    }
    /**
     * Method returns the description of the expense
     * @return the description
     * */
    public String getDescription() {
        return description;
    }

    /**
     * Method to set the description of an expense if the status is pending
     * does nothing if not in the correct status
     * @param description saved as the new description of an expense object */
    public void setDescription(String description) {
        if(status.compareTo("Pending") == 0) {
            this.description = description;
        }
    }

    /**
     * Method to get the amount of the expense
     * @return the double value of the expense
     * */
    public double getAmount() {
        return this.amount;
    }

    /**
     * Method to set the amount of the expense if the expense is in pending state
     * @param amount the new ammount of the expense */
    public void setAmount(float amount) {
        if (status.compareTo("Pending") == 0) {
            this.amount = amount;
        }
    }

    /**
     * Method to get the status of an expense
     * @return the value of the string status
     * */
    public String getStatus() {
        return this.status;
    }

    /**
     * Method to set the status of an expense. May only be used once per expense
     * @param status the new status to be assigned */
    public void setStatus(String status) {
        if(this.status.compareTo("Pending") == 0 || this.status.compareTo(null) == 0) {
            this.status = status;
        }
    }

    /**
     * Method to get the expense ID of an expense object
     * @return the expense ID
     * */
    public int getExpenseID() {
        return this.expenseID;
    }

    /**
     * Method to set the expense ID of an expense object
     * @param expenseID the new expense ID */
    public void setExpenseID(int expenseID) {
        if(status.compareTo("Pending") == 0) {
            this.expenseID = expenseID;
        }
    }

    /**
     * Method to get an employee source id
     * @return the source ID
     * */
    public int getEmployeeSource() {
        return employeeSource;
    }

    /**
     * Method to set the employee source
     * @param employeeSource the new employee source
     * */
    public void setEmployeeSource(int employeeSource) {
        this.employeeSource = employeeSource;
    }

    /**
     * To string method for an expense object
     * @return the string representation of the object
     * */
    @Override
    public String toString() {
        return "Expense{" +
                "expenseID=" + expenseID +
                ", employeeSource=" + employeeSource +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                '}';
    }
}
