package database;

import java.sql.*;

public class DatabaseConnection {
    
    private String server;
    private String port;
    private String database;
    private String user;
    private String password;

    public DatabaseConnection(String server, String port, String database, String user, String password) {
        this.server = server;
        this.port = port;
        this.database = database;
        this.user = user;
        this.password = password;
    }

    public void Executor(){

        Connection conn = DriverManager.getConnection(url, user, password);
    }

    
}
