package com.revature.main;

import com.revature.controller.AccountController;
import com.revature.controller.ClientController;
import com.revature.controller.Controller;
import com.revature.controller.ExceptionController;
import io.javalin.Javalin;


public class Driver {
    public static void main(String[] args){

        Javalin app = Javalin.create().start(8080);

        mapControllers(app, new ClientController(), new AccountController(), new ExceptionController());
//        app.get("/", ctx -> ctx.result("Hello World"));

        app.start();
    }

    public static void mapControllers(Javalin app, Controller... controllers){
        for (Controller c: controllers){
            c.mapEndpoints(app);
        }
    }
}
