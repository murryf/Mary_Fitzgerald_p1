package dev.fitzgerald.app;

import com.google.gson.Gson;
import dev.fitzgerald.data.EmployeeDAO;
import dev.fitzgerald.data.EmployeeDAOPostgresImpl;
import dev.fitzgerald.data.ExpenseDAOPostgresImpl;
import dev.fitzgerald.entities.Employee;
import dev.fitzgerald.service.ExpenseService;
import dev.fitzgerald.service.ExpenseServiceImpl;
import dev.fitzgerald.service.ReimbursementServiceImpl;
import io.javalin.Javalin;

import java.util.List;

public class WebApp {
    public static Gson gson  = new Gson();
    public static ReimbursementServiceImpl services = new ReimbursementServiceImpl(new EmployeeDAOPostgresImpl());
    public static ExpenseService expenseService = new ExpenseServiceImpl(new ExpenseDAOPostgresImpl());

    public static void main(String[] args){
        Javalin app = Javalin.create();

 //_______________Employee_Section_________________________________________
        /**
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
            context.result(employeeJSON);
        });

        /**
         * Get all employees
         * */
        //Read all
        app.get("/employees", context -> {
            List<Employee> emp = services.getAllEmployees();
            String emplJSON = gson.toJson(emp);
            context.result(emplJSON);
        });

        /**
         * get one employee
         * */
        //read one
        app.get("/employees/{id}", context ->{
            int id = Integer.parseInt(context.pathParam("id"));
            String employeeJSON = gson.toJson(services.getEmployeeById(id));
            if(employeeJSON!=null) {
                context.result(employeeJSON);
            } else {
                context.status(404);
            }
        });

        /**
         * Update an employee object
         * */
        //Update employee
        app.put("/employees/{id}", context -> {
            int id = Integer.parseInt(context.pathParam("id"));
            String body = context.body();
            Employee emp = gson.fromJson(body, Employee.class);
            if(emp != null) {
                emp.setID(id);
                services.updateEmployee(emp);
                context.status(201);
            } else {
                context.status(404);
            }

        });

        /**
        * Delete an employee object
         * */
        //Delete employee
        app.delete("/employees/{id}", context -> {
            int id = Integer.parseInt(context.pathParam("id"));
            Employee emp = services.getEmployeeById(id); //make sure it is in the table
            if(emp != null){
                services.deleteEmployeeByID(id);
                context.result("Employee " + id + " deleted");
                context.status(201);
            } else {
                context.result("Employee not found");
                context.status(404);
            }

        });

//  _______________________Expense_Section______________________________________
//
//        /**
//         * Post an expense
//         * */
//        //post an expense
//        app.post("/expenses", null);
//
//
//
//        /**
//         * Delete an expense item. Must be in pending status
//         * */
//        app.delete("/expenses/{id}",null);
//
//
////__________________________Nested_Routes________________________________________________
//
//        /**
//         * Read an employee's expense
//         * */
//        //read one expenses
//        app.get("/employees/{id}/expenses", null);
//
//        /**
//         * Post an expense to an employee
//         * */
//        //Post an expense to a specific employee
//        app.post("/employees/{id}/expenses", null);
//

//  _______________________________________________________________________________
        app.start(7000);
    }
}
