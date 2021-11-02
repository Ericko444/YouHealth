/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

/**
 *
 * @author Ericko
 */

import conn.Connexion;
import health.Admin;
import health.Medecin;
import health.Patient;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AccountsManager implements ServletContextListener{
    Connection conn;
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            conn = Connexion.connect();
        } catch (Exception ex) {
            Logger.getLogger(AccountsManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(AccountsManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Patient getPatient(String username, String motDePasse) throws SQLException, Exception
    {
        String qry = "SELECT * FROM patient WHERE username=? AND password=?";
        Connection con1 = Connexion.connect();
        PreparedStatement ps = con1.prepareStatement(qry);
        ps.setString(1, username);
        ps.setString(2, motDePasse);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            Patient newS = new Patient(rs.getString("idPatient"), rs.getString("username"), rs.getString("password"), rs.getDate("dateDeNaissance"));
            return newS;
        }
        return null;
    }
    
    public static Medecin getMedecin(String username, String motDePasse) throws Exception
    {
        String qry = "SELECT * FROM medecin WHERE username=? AND password=?";
        Connection con1 = Connexion.connect();
        PreparedStatement ps = con1.prepareStatement(qry);
        ps.setString(1, username);
        ps.setString(2, motDePasse);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            Medecin newM = new Medecin(rs.getString("idMedecin"), rs.getString("specialisation"), rs.getString("username"),rs.getString("password"));
            return newM;
        }
        return null;
    }
    
    public static Admin getAdmin(String username, String motDePasse) throws Exception
    {
        String qry = "SELECT * FROM admin WHERE username=? AND password=?";
        Connection con1 = Connexion.connect();
        PreparedStatement ps = con1.prepareStatement(qry);
        ps.setString(1, username);
        ps.setString(2, motDePasse);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            Admin newM = new Admin(rs.getInt("idAdmin"), rs.getString("username"), rs.getString("password"));
            newM.setPassword(rs.getString("password"));
            return newM;
        }
        return null;
    }
    
}
