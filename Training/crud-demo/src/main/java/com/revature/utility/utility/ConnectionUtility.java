package com.revature.utility.utility;

import org.postgresql.Driver;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionUtility {

    public static ConnectionUtility(){

        String url = System.getenv("db_url");
        String username = System.getenv("db_username");
        String password = System.getenv("db_password");

        DriverManager.registerDriver(new Driver());

        Connection connection = DriverManager.getConnection(url, username, password);
    }
}
