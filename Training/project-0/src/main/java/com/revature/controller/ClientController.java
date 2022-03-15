package com.revature.controller;

import com.revature.model.Client;
import com.revature.service.ClientService;
import io.javalin.Javalin;
import io.javalin.http.Handler;

import java.util.List;

public class ClientController implements  Controller {

    private ClientService clientService;

    public ClientController() {
        this.clientService = new ClientService();
    }

    private Handler getAllClients = (ctx) -> {
        List<Client> clients = clientService.getAllClients();

        ctx.json(clients);
    };

    private Handler getClientById = (ctx) -> {
        String id = ctx.pathParam("clientId");

        Client client = clientService.getClientById(id);

        ctx.json(client);
    };

    private Handler createClient = (ctx) -> {
        Client clientToAdd = ctx.bodyAsClass(Client.class);

        Client c = clientService.createClient(clientToAdd);

        ctx.status(201);
        ctx.json(c);
    };

    private Handler updateClient = (ctx) -> {
        Client clientToUpdate = ctx.bodyAsClass(Client.class);

        Client updatedClient = clientService.updateClient(ctx.pathParam("clientId"), clientToUpdate);

        ctx.status(200);
        ctx.json(updatedClient);
    };

    private Handler deleteClient = (ctx) -> {
//        Client clientToDelete = ctx.bodyAsClass(Client.class);
//
//        boolean deletedClient = clientService.deleteClient((ctx.pathParam("clientId")), clientToDelete);
        String id = ctx.pathParam("clientId");

        Client client = clientService.getClientById(id);

        ctx.status(200);
        ctx.json(client);
    };


    @Override
    public void mapEndpoints(Javalin app) {
        app.get("/clients", getAllClients);
        app.get("/clients/{clientId}", getClientById);
        app.post("/clients", createClient);
        app.put("/clients/{clientId}", updateClient);
        app.delete("/clients/{clientId}", deleteClient);

    }
}