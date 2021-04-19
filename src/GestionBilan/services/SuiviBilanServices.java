///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package GestionBilan.services;
//
//import GestionBilan.entities.SuiviBilans;
//import GestionBilan.tools.MyConnection;
//import java.sql.Date;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
///**
// *
// * @author Islem Oueslati
// */
//public class SuiviBilanServices {
//    MyConnection myConnection = new MyConnection();
//    
//        public ArrayList<SuiviBilans> showEtudiant() {
//        String query = "SELECT full_name,debut_stage FROM user where role='etudiant'";
//        ArrayList<SuiviBilans> SuiviBilansArrayList = new ArrayList<>();
//        PreparedStatement preparedStatement;
//        try {
//            preparedStatement = myConnection.getConnection().prepareStatement(query);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                System.out.println("Test: " + resultSet.toString());
//                String Etudiant = resultSet.getString("full_name");
//                Date DebutStage = resultSet.getDate("debut_stage");
//                SuiviBilans suiviBilans= new SuiviBilans(Etudiant, DebutStage);
//                SuiviBilansArrayList.add(suiviBilans);
//
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(SuiviBilanServices.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return SuiviBilansArrayList;
//    }
//         
//    //public static void main(String[] args) {
//        //SuiviBilanServices suivibilansservice = new SuiviBilanServices();
//        //suivibilansservice.showEtudiant();
//    //}
//}
