/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import conn.Connexion;
import health.DetailsRendezVous;
import health.Medecin;
import health.Patient;
import health.RendezVous;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import util.Util;

/**
 *
 * @author Ericko
 */
public class RendezVousService {
    public static ArrayList<RendezVous> findRendezVousEfaMisy(String nomMedecin, String specialisation, String dateTime) throws Exception
    {
        Medecin med;
        RendezVous rdv;
        ArrayList<RendezVous> valiny;
        if("".equals(nomMedecin) && "".equals(specialisation) && "".equals(dateTime))
        {
            valiny = RendezVous.findAll();
        }
        else if("".equals(nomMedecin) && !"".equals(specialisation) && "".equals(dateTime))
        {
            med = new Medecin();
            med.setSpecialisation(specialisation);
            rdv = new RendezVous();
            valiny = rdv.findSpecificMed(med);
        }
        else if("".equals(nomMedecin) && "".equals(specialisation) && !"".equals(dateTime))
        {
            rdv = new RendezVous();
            rdv.setDateRdv(Timestamp.valueOf(Util.convertDateTimeToTimestamp(dateTime)));
            valiny = rdv.findOnDate();
        }
        else if("".equals(nomMedecin) && !"".equals(specialisation) && !"".equals(dateTime))
        {
            med = new Medecin();
            med.setSpecialisation(specialisation);
            rdv = new RendezVous();
            rdv.setDateRdv(Timestamp.valueOf(Util.convertDateTimeToTimestamp(dateTime)));
            valiny = rdv.findSpecificMedOnDate(med);
        }
        else if(!"".equals(nomMedecin) && "".equals(specialisation) && !"".equals(dateTime))
        {
            med = new Medecin();
            med.setUsername(nomMedecin);
            rdv = new RendezVous();
            rdv.setDateRdv(Timestamp.valueOf(Util.convertDateTimeToTimestamp(dateTime)));
            valiny = rdv.findSpecificMedOnDate(med);
        }
        else
        {
            med = new Medecin();
            med.setUsername(nomMedecin);
            med.setSpecialisation(specialisation);
            rdv = new RendezVous();
            rdv.setDateRdv(Timestamp.valueOf(Util.convertDateTimeToTimestamp(dateTime)));
            valiny = rdv.findSpecificMedOnDate(med);
        }
        return valiny;
    }
    
    public static void insertNewRdv(String idMedecin, String idPatient, String dateTime) throws Exception {
        RendezVous rdv = new RendezVous(idMedecin, idPatient, Timestamp.valueOf(dateTime));
        rdv.insert();
    }
    
    public static void AnnulerRdv(String idRdv) throws Exception {
        RendezVous rdv = new RendezVous();
        rdv.setIdRdv(idRdv);
        rdv.delete();
    }
    
    public static void ModifierRdv(String idRdv, String dateTime) throws Exception {
        RendezVous rdv = new RendezVous();
        rdv.setIdRdv(idRdv);
        rdv.setDateRdv(Timestamp.valueOf(Util.convertDateTimeToTimestamp(dateTime)));
        rdv.update();
    }
    
    public static ArrayList<RendezVous> listByPatient(Patient p) throws Exception {
        ArrayList<RendezVous> valiny = p.findAllRendezVous();
        return valiny;
    }
    
    public static ArrayList<DetailsRendezVous> findByMed(String idMedecin) throws Exception {
        DetailsRendezVous filtre = new DetailsRendezVous();
        filtre.setIdMedecin(idMedecin);
        ArrayList<DetailsRendezVous> listRdv = filtre.findByMedecin();
        return listRdv;
    }
    
     public static ArrayList<DetailsRendezVous> findByPat(String idPatient) throws Exception {
        DetailsRendezVous filtre = new DetailsRendezVous();
        filtre.setIdPatient(idPatient);
        ArrayList<DetailsRendezVous> listRdv = filtre.findByPatient();
        return listRdv;
    }
    
     public static int countItems(HashMap<String, String> filtre) throws Exception {
        int valiny = 0;
        try {
            Connection con1 = Connexion.connect();
            String query = "SELECT  COUNT(*) FROM rendezvous";
            if (filtre != null) {
                query += " WHERE ";
                for (String key : filtre.keySet()) {
                    query += (key + " =?");
                }
            }
            PreparedStatement ps1 = con1.prepareStatement(query);
            int counter = 1;
            if (filtre != null) {
                for (String key : filtre.keySet()) {
                    ps1.setString(counter, filtre.get(key));
                    counter++;
                }
            }
            ResultSet rs1 = ps1.executeQuery();
            if (rs1.next()) {
                valiny = rs1.getInt(1);
            }
            con1.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return valiny;
    }
     
     public static ArrayList<RendezVous> listAllRendezVous(int limit, int offset, HashMap<String, String> filtre) throws Exception {
        ArrayList<RendezVous> list = new ArrayList();
        try {
            Connection con1 = Connexion.connect();
            String query = "SELECT  * FROM rendezvous";
            if (filtre != null) {
                query += " WHERE ";
                for (String key : filtre.keySet()) {
                    query += (key + " =?");
                }
            }
            query += " LIMIT ? OFFSET ?";
            PreparedStatement ps1 = con1.prepareStatement(query);
            int counter = 1;
            if (filtre != null) {
                for (String key : filtre.keySet()) {
                    ps1.setString(counter, filtre.get(key));
                    counter++;
                }
            }
            ps1.setInt(counter, limit);
            counter++;
            ps1.setInt(counter, offset);
            ResultSet rs1 = ps1.executeQuery();
            while (rs1.next()) {
                RendezVous e1 = new RendezVous(rs1.getString(1), rs1.getString(2), rs1.getString(3), rs1.getTimestamp(4));
                list.add(e1);
            }
            con1.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
}
