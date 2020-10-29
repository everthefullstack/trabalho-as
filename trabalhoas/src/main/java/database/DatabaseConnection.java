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

    // Retorna a função que executa os comandos SQL
    // Exemplo: String sql = "select 'teste' from DUAL"
    // executor = DatabaseConnection().Executor()
    // executor.executeQuery(sql)
    public Statement Executor() {

        String url = "jdbc:oracle:thin:@" + server + ":" + port + ":" + database;      

        Connection conn;
        
		try {

            conn = DriverManager.getConnection(url, user, password);
            return conn.createStatement();
            
		} catch (SQLException e) {
			
            e.printStackTrace();
            return null;
        }
    }
}
