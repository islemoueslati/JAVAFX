/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionBilan.services;

import GestionBilan.entities.Bilan;
import GestionBilan.tools.MyConnection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Islem Oueslati
 */
public class BilanService {

    MyConnection myConnection = new MyConnection();
public static int ind;
public static int selectedUser;
public static int loggedUser = 118;

// 7ot zokomha fi method login mtaa UserService  o nahi 118 
//  BilanService.loggedUser = resultSet.getInt("idUser");

    public void addBilan(Bilan bilan) {
        String query = "INSERT INTO bilan (titre, indexPeriode, periode) VALUES (?,?,?)";
        try {
            PreparedStatement preparedStatement = myConnection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, bilan.getTitre());
            preparedStatement.setInt(2, bilan.getIndexPeriode());
            preparedStatement.setDate(3, bilan.getPeriode());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(QuestionService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void updateBilan(int idBilan, String titre) {
        String query = "UPDATE bilan SET titre=? WHERE idBilan = ?";
        try {
            PreparedStatement preparedStatement = myConnection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, titre);
            preparedStatement.setInt(2, idBilan);
            preparedStatement.executeUpdate();
            System.out.println("Update Done");
        } catch (SQLException ex) {
            Logger.getLogger(QuestionService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void deleteBilan(int idBilan) {
        String query = "DELETE FROM bilan WHERE idBilan = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = myConnection.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, idBilan);
            preparedStatement.executeUpdate();
            System.out.println("Delete Done");
        } catch (SQLException ex) {
            Logger.getLogger(QuestionService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Bilan> showBilan() {
        String query = "SELECT * FROM bilan";
        ArrayList<Bilan> bilanArrayList = new ArrayList<>();
        PreparedStatement preparedStatement;
        try {
            preparedStatement = myConnection.getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println("Test: " + resultSet.toString());
                int idBilan = resultSet.getInt("idBilan");
                String titre = resultSet.getString("titre");
                int indexPeriode = resultSet.getInt("indexPeriode");
                Date periode = resultSet.getDate("periode");
                Bilan bilan = new Bilan(idBilan, titre, indexPeriode, periode);
                bilanArrayList.add(bilan);

            }
        } catch (SQLException ex) {
            Logger.getLogger(QuestionService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bilanArrayList;
    }

    public ArrayList<Bilan> showBilanByIndexPeriode(int indexPeriode) {
        String query = "SELECT * FROM bilan WHERE indexPeriode=?";
        ArrayList<Bilan> bilanArrayList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = myConnection.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, indexPeriode);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println("Test select by: " + resultSet.toString());
                int idBilan = resultSet.getInt("idBilan");
                String titre = resultSet.getString("titre");
                Date periode = resultSet.getDate("periode");
                Bilan bilan = new Bilan(idBilan, titre, indexPeriode, periode);
                bilanArrayList.add(bilan);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuestionService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return bilanArrayList;
    }
    


}
