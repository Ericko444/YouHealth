/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Ericko
 */
public class Connexion {
    
    private final static String URL = "jdbc:postgresql://localhost/eHealth";
    private final static String USER = "postgres";
    private final static String PASSWORD = "postgres";
//   private final static String PASSWORD = "Ericko_app24";


    public static Connection connect() throws Exception{
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }
    public static void main(String[] args) throws Exception {
        Connexion  app = new Connexion();
        connect();
    }
}