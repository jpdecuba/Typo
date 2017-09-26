package Model.Database;

import java.io.*;
import java.sql.*;
import java.util.*;

public class Database {

    public static Connection connection(){
        Connection con = null;
        Properties properties = null;
        try
        {
            InputStream input = new FileInputStream("config.properties");
            properties.load(input);
            
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(properties.getProperty("path"), properties.getProperty("user"), properties.getProperty("password"));
        }
        catch (FileNotFoundException ex)
        {
            ex.printStackTrace();
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        catch (ClassNotFoundException ex)
        {
            ex.printStackTrace();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return con;
    }
}
