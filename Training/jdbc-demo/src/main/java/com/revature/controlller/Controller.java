package com.revature.controlller;


import io.javalin.Javalin;

public interface Controller {

    public abstract void mapEndpoints(Javalin app);

}
