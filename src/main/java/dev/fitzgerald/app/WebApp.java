package dev.fitzgerald.app;

import com.google.gson.Gson;
import dev.fitzgerald.data.EmployeeDAOPostgresImpl;
import dev.fitzgerald.data.ExpenseDAOPostgresImpl;
import dev.fitzgerald.entities.Employee;
import dev.fitzgerald.entities.Expense;
import dev.fitzgerald.service.ExpenseService;
import dev.fitzgerald.service.ExpenseServiceImpl;
import dev.fitzgerald.service.ReimbursementServiceImpl;
import io.javalin.Javalin;

import java.util.ArrayList;
import java.util.List;

public class WebApp {
    public static final Gson gson  = new Gson();
    public static final ReimbursementServiceImpl services = new ReimbursementServiceImpl(new EmployeeDAOPostgresImpl());
    public static final ExpenseService expenseService = new ExpenseServiceImpl(new ExpenseDAOPostgresImpl());

    public static void main(String[] args){
        Javalin app = Javalin.create();


 //_______________Employee_Section_________________________________________
        /*
         * Post a new employee through the app
         * */
        //Create
        //Must use java end field names, not database
        app.post("/employees", context -> {
            String body = context.body();
            Employee employee = gson.fromJson(body, Employee.class);
            services.createEmployee(employee);
            context.status(201);
            String employeeJSON = gson.toJson(employee);

            context.result("Employee: "+employeeJSON+" Added");
        });

        /*
         * Get all employees
         * */
        //Read all
        app.get("/employees", context -> {
            List<Employee> emp = services.getAllEmployees();
            String emplJSON = gson.toJson(emp);
            context.result(emplJSON);
        });

        /*
         * get one employee
         * */
        //read one
        app.get("/employees/{id}", context ->{
            int id = Integer.parseInt(context.pathParam("id"));
            Employee employee = services.getEmployeeById(id);
            if(employee != null){
                String employeeJSON = gson.toJson(services.getEmployeeById(id));
                context.status(201);
                context.result(employeeJSON);
            } else {
                context.status(404);
                context.result("No such employee");
            }
        });

        /*
         * Update an employee object
         * */
        //Update employee
        app.put("/employees/{id}", context -> {
            int id = Integer.parseInt(context.pathParam("id"));
            String body = context.body();
            Employee emp = gson.fromJson(body, Employee.class);
            if(emp != null) {
                emp.setID(id);
                if(services.getEmployeeById(id) != null) {
                    services.updateEmployee(emp);
                    context.status(201);
                }else {
                    context.result("Employee not found");
                    context.status(404);
                }
            } else {
                context.status(404);
            }

        });

        /*
        * Delete an employee object
         * */
        //Delete employee
        app.delete("/employees/{id}", context -> {
            int id = Integer.parseInt(context.pathParam("id"));
            Employee emp = services.getEmployeeById(id); //make sure it is in the table
            if(emp != null){

                if(services.deleteEmployeeByID(id)) {
                    context.result("Employee " + id + " deleted");
                    context.status(201);
                } else {
                    context.result("Employee not deleted.");
                    context.status(409);
                }
            } else {
                context.result("Employee not found");
                context.status(404);
            }

        });

//  _______________________Expense_Section______________________________________

        /*
         * Post an expense
         * */
        //post an expense
        app.post("/expenses", context -> {
            String body = context.body();
            Expense expense = gson.fromJson(body, Expense.class);
            expenseService.createExpense(expense);
            context.status(201);
            String expenseJSON = gson.toJson(expense);
            context.result(expenseJSON);
        });

        /*
         * get an expense by id number
         * */
        app.get("/expenses/{id}", context ->{
            int id = Integer.parseInt(context.pathParam("id"));
            if(expenseService.getExpenseById(id) != null) {
                String expenseJSON = gson.toJson(expenseService.getExpenseById(id));
                context.result(expenseJSON);
                context.status(201);
            }else {
                context.status(404);
                context.result("No such expense");
            }

        });

        /*
         * get all expenses expenses. contains filters
         * */
        app.get("/expenses", context -> {
            String status = context.queryParam("approval");
            List<Expense> exp = expenseService.getAllExpenses();
            if(status == null){
                String expJSON = gson.toJson(exp);
                context.result(expJSON);
            } else {
                List<Expense> tripleFilter = new ArrayList<>();
                for(Expense expense : exp){
                    if(expense.getStatus().compareTo(status) == 0){
                        tripleFilter.add(expense);
                    }
                }
                String expJSON = gson.toJson(tripleFilter);
                context.result(expJSON);
            }
        });

        /*
         * Update an expense when in pending status
         * */
        app.put("/expenses/{id}", context -> {
            int id = Integer.parseInt(context.pathParam("id"));
            String body = context.body();
            Expense exp = gson.fromJson(body, Expense.class);
            if(exp != null) {
                exp.setExpenseID(id);
                if(expenseService.getExpenseById(id) != null) {
                    if(expenseService.updateExpense(exp, id)) {
                        context.result("Expense Update Saved");
                        context.status(201);
                    } else {
                        context.result("Invalid Expense Status");
                        context.status(409);
                    }
                }else {
                    context.result("Expense not found");
                    context.status(404);
                }
            } else {
                context.status(404);
            }

        });

        /*
         * Approve or Deny a given expense
         * */
        app.patch("/expenses/{id}/{status}", context -> {
            int id = Integer.parseInt(context.pathParam("id"));
            String status = context.pathParam("status");
            if(status.compareTo("Approved") == 0 || status.compareTo("Denied") == 0){
                if(expenseService.getExpenseById(id).getStatus().compareTo("Pending") == 0) {
                    expenseService.updateExpenseStatus(id, status);
                    context.status(201);
                    context.result("Updated");
                } else {
                    context.status(409);
                    context.result("Cannot modify Approved/Denied expenses");
                }
            } else {
                context.status(409);
                context.result("Invalid input");
            }


        });

        /*
         * Delete an expense item. Must be in pending status
         * */
        app.delete("/expenses/{id}",context -> {
            int id = Integer.parseInt(context.pathParam("id"));
            Expense exp = expenseService.getExpenseById(id); //make sure it is in the table
            if(exp != null){
                expenseService.deleteExpense(id);
                context.result("Expense " + id + " deleted");
                context.status(201);
            } else {
                context.result("Expense not found");
                context.status(404);
            }
        });


////__________________________Nested_Routes________________________________________________

        /*
         * Read an employee's expense
         * */
        //read one expenses
        app.get("/employees/{id}/expenses", context -> {
            int id = Integer.parseInt(context.pathParam("id"));
            List<Expense> expenses = expenseService.getAllExpenses();
            List<Expense> filteredExpenses = new ArrayList<>();
            for(Expense expense : expenses){
                if(expense.getEmployeeSource() == id){
                    filteredExpenses.add(expense);
                }
            }
            String filteredJSON = gson.toJson(filteredExpenses);
            context.result(filteredJSON);
            context.status(201);

        });


        /*
         * Post an expense to an employee
         * */
        //Post an expense to a specific employee
        app.post("/employees/{id}/expenses", context -> {
            int id = Integer.parseInt(context.pathParam("id"));
            String body = context.body();
            Expense expense = gson.fromJson(body, Expense.class);
            expense.setEmployeeSource(id);
            expenseService.createExpense(expense);
            context.status(201);
            context.result("Added expense: " + gson.toJson(expense) + " to employee " + id);

        });

//  _______________________________________________________________________________
        app.start(5000);
    }
}
