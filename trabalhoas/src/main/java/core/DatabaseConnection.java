package core;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.sql.*;

public class DatabaseConnection {

    private Properties getProps() throws IOException {

      Properties props = new Properties();
      FileInputStream file = new FileInputStream("trabalhoas\\src\\main\\java\\core\\Database.properties");
      props.load(file);
      return props;
    
    }

    public Statement Executor() throws IOException, SQLException {
        
      Properties prop = getProps();
      
      String server = prop.getProperty("prop.server");
      String port = prop.getProperty("prop.port");
      String database = prop.getProperty("prop.database");
      String user = prop.getProperty("prop.user");
      String password = prop.getProperty("prop.password");
      String url = "jdbc:oracle:thin:@" + server + ":" + port + ":" + database;      

      Connection conn;
      conn = DriverManager.getConnection(url, user, password);
      return conn.createStatement();
    }
}
