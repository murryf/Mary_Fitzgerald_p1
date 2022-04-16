package dev.fitzgerald.entities;

public class Expense {



    private int expenseID;
    private String description;
    private double amount;
    private boolean hasBeenReviewed;
    private boolean approvalStatus;

    /**
     * Basic expense constructor. Sets hasBeenReviewed field to false
     * */
    public Expense(){
        hasBeenReviewed = false;
    }
    /**
     * Main Expense constructor. Takes a String description and an amount to be reported.
     * @param description the description of the expense
     * @param amount the ammount of the expense
     * */
    public Expense(String description, double amount){
        hasBeenReviewed = false;
        this.description = description;
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if(hasBeenReviewed != true) {
            this.description = description;
        }
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        if (hasBeenReviewed != true) {
            this.amount = amount;
        }
    }

    public boolean getHasBeenReviewed() {
        return hasBeenReviewed;
    }

    public boolean getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(boolean approvalStatus) {
        if(this.hasBeenReviewed != true) {
            this.approvalStatus = approvalStatus;
            this.hasBeenReviewed = true;
        }
    }

    public int getExpenseID() {
        return expenseID;
    }

    public void setExpenseID(int expenseID) {
        this.expenseID = expenseID;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "expenseID=" + expenseID +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", approvalStatus=" + approvalStatus +
                '}';
    }
}
