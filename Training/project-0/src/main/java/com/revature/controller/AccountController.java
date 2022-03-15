package com.revature.controller;

import com.revature.model.Account;
import com.revature.service.AccountService;
import io.javalin.Javalin;
import io.javalin.http.Handler;

import java.util.List;

public class AccountController implements Controller{

    private AccountService accountService;

    public AccountController(){
        this.accountService = new AccountService();
    }

//    private Handler getAccountById = (ctx) -> {
//        List<Account> accounts = accountService.getAcountById(client_id);
//
//        ctx.json(accounts);
//    };

    private Handler getAccountsById = (ctx) -> {
        String id = ctx.pathParam("clientId");
        List<Account> accounts = accountService.getAccountsById(id);


        ctx.json(accounts);
    };

    private Handler getAccountByIds = (ctx) -> {
        String clientId = ctx.pathParam("clientId");
        String accountId = ctx.pathParam("accountId");
        Account account = accountService.getAccountByIds(accountId, clientId);

        ctx.json(account);


    };

    private Handler createAccount = (ctx) -> {
        Account accountToAdd = ctx.bodyAsClass(Account.class);
        String clientId = ctx.pathParam("clientId");
        Account account = accountService.addAccount((clientId), accountToAdd);
        ctx.json(account);
    };


    private Handler deleteAccount = (ctx) -> {
        String accountId = ctx.pathParam("accountId");
        String clientId = ctx.pathParam("clientId");

        boolean account = accountService.deleteAccountById(accountId, clientId);

        ctx.json(account);

//        Account accountToDelete = ctx.bodyAsClass(Account.class);
//
//        Account deleteAccount = accountService.deleteAccount(ctx.pathParam("client_id"), accountToDelete);
//
//        ctx.status(400);
//        ctx.json(deleteAccount);
    };
    private Handler updateAccount = (ctx) -> {
        Account accountToEdit = ctx.bodyAsClass(Account.class);

    };

    @Override
    public void mapEndpoints(Javalin app) {
        app.get("/clients/{clientId}/accounts", getAccountsById);
        app.post("/clients/{clientId}/accounts", createAccount);
        app.delete("/clients/{clientId}/accounts/{accountId}", deleteAccount);
        app.put("/clients/{clientId}/accounts/{accountId}", updateAccount);
        app.get("/clients/{clientId}/accounts/{accountId}", getAccountByIds);
    }

}
