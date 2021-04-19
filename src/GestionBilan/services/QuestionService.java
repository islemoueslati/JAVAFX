/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionBilan.services;

import GestionBilan.entities.Question;
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
public class QuestionService {

    MyConnection myConnection = new MyConnection();

    public void addQuestion(Question question) {
        String query = "INSERT INTO question (quest,indexPeriode) VALUES (?,?)";
        try {
            PreparedStatement preparedStatement = myConnection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, question.getQuest());
            preparedStatement.setInt(2, question.getIndexPeriode());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(QuestionService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void updateQuestion(Question question, int idQuestion) {
        String query = "UPDATE question SET quest=?,indexPeriode=? WHERE idQuestion = ?";
        try {
            PreparedStatement preparedStatement = myConnection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, question.getQuest());
            preparedStatement.setInt(2, question.getIndexPeriode());
            preparedStatement.setInt(3, idQuestion);
            preparedStatement.executeUpdate();
            System.out.println("Update Done");
        } catch (SQLException ex) {
            Logger.getLogger(QuestionService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void deleteQuestion(int idQuestion) {
        String query = "DELETE FROM question WHERE idQuestion = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = myConnection.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, idQuestion);
            preparedStatement.executeUpdate();
            System.out.println("Delete Done");
        } catch (SQLException ex) {
            Logger.getLogger(QuestionService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ArrayList<Question> showQuestion() {
        String query = "SELECT * FROM question";
        ArrayList<Question> questionArrayList = new ArrayList<>();
        PreparedStatement preparedStatement;
        try {
            preparedStatement = myConnection.getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println("Test: " + resultSet.toString());
                // DO NOT TOUCH
                int idQuestion = resultSet.getInt("idQuestion");
                String quest = resultSet.getString("quest");
                int indexPeriode = resultSet.getInt("indexPeriode");

                Question question = new Question(idQuestion, quest, indexPeriode);
                questionArrayList.add(question);
            }

        } catch (SQLException ex) {
            Logger.getLogger(QuestionService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return questionArrayList;
    }

    public ArrayList<Question> showQuestionByIndexPeriode(int indexPeriode) {
        String query = "SELECT * FROM question WHERE indexPeriode=?";
        ArrayList<Question> questionArrayList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = myConnection.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, indexPeriode);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                
                System.out.println("Test select by: " + resultSet.toString());
                // DO NOT TOUCH
                int idQuestion = resultSet.getInt("idQuestion");
                String quest = resultSet.getString("quest");

                Question question = new Question(idQuestion, quest, indexPeriode);
                questionArrayList.add(question);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuestionService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return questionArrayList;
    }

}
