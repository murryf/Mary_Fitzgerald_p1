package dev.fitzgerald.app;

import com.google.gson.Gson;
import dev.fitzgerald.data.EmployeeDAO;
import dev.fitzgerald.data.EmployeeDAOPostgresImpl;
import dev.fitzgerald.entities.Employee;
//import dev.fitzgerald.services.ReimbursementService;
import io.javalin.Javalin;

public class WebApp {
    public static Gson gson  = new Gson();
    public static EmployeeDAO employeeDAO = new EmployeeDAOPostgresImpl();

    public static void main(String[] args){
        Javalin app = Javalin.create();


        //Create
        //Must use java end field names, not database
        app.post("/employees", context -> {
            String body = context.body();
            Employee employee = gson.fromJson(body, Employee.class);
            employeeDAO.createEmployee(employee);
            context.status(201);
            String employeeJSON = gson.toJson(employee);
            context.result(employeeJSON);
        });


        //Read all
        //app.get("/employees", null);
        //read two
        app.get("/employees/{id}", context ->{
            int id = Integer.parseInt(context.pathParam("id"));
            String employeeJSON = gson.toJson(employeeDAO.getEmployeeById(id));
            if(employeeJSON!=null) {
                context.result(employeeJSON);
            } else {
                context.status(404);
            }

        });

        //Update employee
        //app.put("/employees/{id}", null);


        app.start(7000);
    }
}
