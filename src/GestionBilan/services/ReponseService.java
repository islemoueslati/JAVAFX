/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionBilan.services;

import GestionBilan.entities.Reponse;
import GestionBilan.tools.MyConnection;
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
public class ReponseService {

    MyConnection myConnection = new MyConnection();

    public void addReponse(Reponse reponse) {
        String query = "INSERT INTO reponse (rep, idQuestion, idUser, indexPeriode) VALUES (?,?,?,?)";
        try {
            PreparedStatement preparedStatement = myConnection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, reponse.getRep());
            preparedStatement.setInt(2, reponse.getIdQuestion());
            preparedStatement.setInt(3, reponse.getIdUser());
            preparedStatement.setInt(4, reponse.getIndexPeriode());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(QuestionService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void updateReponse(int idReponse, Reponse reponse) {
        String query = "UPDATE reponse SET rep=? WHERE idReponse = ?";
        try {
            PreparedStatement preparedStatement = myConnection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, reponse.getRep());
            preparedStatement.setInt(2, idReponse);
            preparedStatement.executeUpdate();
            System.out.println("Update Done");
        } catch (SQLException ex) {
            Logger.getLogger(QuestionService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void deleteReponse(int idReponse) {
        String query = "DELETE FROM reponse WHERE idReponse = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = myConnection.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, idReponse);
            preparedStatement.executeUpdate();
            System.out.println("Delete Done");
        } catch (SQLException ex) {
            Logger.getLogger(QuestionService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Reponse> showReponse() {
        String query = "SELECT * FROM reponse";
        ArrayList<Reponse> reponseArrayList = new ArrayList<>();
        PreparedStatement preparedStatement;
        try {
            preparedStatement = myConnection.getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println("Test: " + resultSet.toString());
                int idReponse = resultSet.getInt("idReponse");
                int idQuestion = resultSet.getInt("idQuestion");
                int idUser = resultSet.getInt("idUser");
                String rep = resultSet.getString("rep");
                int indexPeriode = resultSet.getInt("indexPeriode");
              
                Reponse reponse = new Reponse(idReponse, rep, idQuestion, idUser, indexPeriode);
                reponseArrayList.add(reponse);

            }
        } catch (SQLException ex) {
            Logger.getLogger(QuestionService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reponseArrayList;

    }

    public ArrayList<Reponse> showReponseByIdUser(int idUser, int indexPeriode) {
        String query = "SELECT * FROM reponse WHERE idUser=? AND indexPeriode=?";
        ArrayList<Reponse> reponseArrayList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = myConnection.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, idUser);
            preparedStatement.setInt(2, indexPeriode);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println("Test select by: " + resultSet.toString());
                int idReponse = resultSet.getInt("idReponse");
                int idQuestion = resultSet.getInt("idQuestion");
                String rep = resultSet.getString("rep");
                Reponse reponse = new Reponse(idReponse, rep, idQuestion, idUser, indexPeriode);
                reponseArrayList.add(reponse);

            }
        } catch (SQLException ex) {
            Logger.getLogger(QuestionService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reponseArrayList;

    }

    public ArrayList<Reponse> showReponseByIdQuestion(int idQuestion) {
        String query = "SELECT * FROM reponse WHERE idUser=?";
        ArrayList<Reponse> reponseArrayList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = myConnection.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, idQuestion);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println("Test select by: " + resultSet.toString());
                int idReponse = resultSet.getInt("idReponse");
                int idUser = resultSet.getInt("idUser");
                String rep = resultSet.getString("rep");
                int indexPeriode = resultSet.getInt("indexPeriode");
                Reponse reponse = new Reponse(idReponse, rep, idQuestion, idUser, indexPeriode);
                reponseArrayList.add(reponse);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuestionService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reponseArrayList;

    }
    public boolean isSubmited(int idUser, int indexPeriode){
        String query = "SELECT * FROM reponse WHERE idUser=? And indexPeriode=?";
         boolean submited = false;
        try {
           PreparedStatement preparedStatement = myConnection.getConnection().prepareStatement(query);
           preparedStatement.setInt(1, idUser);
            preparedStatement.setInt(2, indexPeriode);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                submited = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReponseService.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        return submited;
    }
}
