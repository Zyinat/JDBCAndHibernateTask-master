package peaksoft.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String URL="jdbc:postgresql://localhost:5432/postgres";
    private static final String USERNAME="postgres";
    private static final String PASSWORD="1987";

    public static Connection connection(){
        Connection connection=null;
        try {
            connection= DriverManager.getConnection(URL,USERNAME,PASSWORD);
            System.out.println("successfuly");

        }catch (SQLException e){
            e.printStackTrace();
        }
        return connection;


    }
}
