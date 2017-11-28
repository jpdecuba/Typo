package Model.Database;

import java.io.*;
import java.sql.*;
import java.util.*;

public class Database {

    public static Connection connection(){
        Connection con = null;
        Properties properties = new Properties();
        try
        {
            InputStream input = new FileInputStream(new File("config.properties").getAbsolutePath());
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

    public static boolean checkConnection(){
        Connection con = connection();
        boolean isOpen = false;
        String query = "SELECT 1";
        try{
            PreparedStatement statement = con.prepareStatement(query);
            isOpen = true;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return isOpen;
    }
}
