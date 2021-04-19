/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import GestionBilan.services.BilanService;
import GestionBilan.services.ReponseService;
import GestionBilan.tools.MyConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Islem Oueslati
 */
public class BilansPeriodiquesController implements Initializable {

    @FXML
    private Button buttonAfficher1;
    @FXML
    private Button buttonRemplir1;
    @FXML
    private Button buttonModifier1;
    @FXML
    private Button buttonAfficher2;
    @FXML
    private Button buttonRemplir2;
    @FXML
    private Button buttonModifier2;
    @FXML
    private Button buttonAfficher3;
    @FXML
    private Button buttonRemplir3;
    @FXML
    private Button buttonModifier3;

    ReponseService reponseService = new ReponseService();
    BilanService bilanService = new BilanService();
    private int idUser = bilanService.loggedUser;

    MyConnection myConnection = new MyConnection();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        isSubmited(idUser, 1, buttonRemplir1);
        isSubmited(idUser, 2, buttonRemplir2);
        isSubmited(idUser, 3, buttonRemplir3);
        isAddSubmited(idUser, 1, buttonModifier1);
        isAddSubmited(idUser, 2, buttonModifier2);
        isAddSubmited(idUser, 3, buttonModifier3);
    }

    public void isSubmited(int idUser, int indexPeriode, Button button) {
        boolean submited = reponseService.isSubmited(idUser, indexPeriode);
        System.out.println(isAvailable(idUser));
        if ((isAvailable(idUser) <= 42) && (indexPeriode == 1)) {
            if (submited) {
                System.out.println("true");
                button.setDisable(true);
            } else {
                System.out.println("false");
                button.setDisable(false);
            }
        } else {
            System.out.println("else");
            button.setDisable(true);
        }
        if ((isAvailable(idUser) <= 98) && (indexPeriode == 2)) {
            if (submited) {
                System.out.println("true");
                button.setDisable(true);
            } else {
                System.out.println("false");
                button.setDisable(false);
            }
        } else {
            System.out.println("else");
            button.setDisable(true);
        }
        if ((isAvailable(idUser) <= 161) && (indexPeriode == 3)) {
            if (submited) {
                System.out.println("true");
                button.setDisable(true);
            } else {
                System.out.println("false");
                button.setDisable(false);
            }
        } else {
            System.out.println("else");
            button.setDisable(true);
        }

    }

    public int isAvailable(int idUser) {
        String query = "SELECT DATEDIFF((SELECT DATE(NOW())), (SELECT `debut_stage`FROM `user` WHERE `id_user` = ?)) AS duree; ";
        int duree = 0;
        try {
            PreparedStatement preparedStatement = myConnection.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, idUser);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                duree = resultSet.getInt("duree");
            }
        } catch (SQLException ex) {
            Logger.getLogger(BilansPeriodiquesController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return duree;
    }

    public void isAddSubmited(int idUser, int indexPeriode, Button button) {
        boolean submited = reponseService.isSubmited(idUser, indexPeriode);
        if (submited) {
            button.setDisable(false);
        } else {
//            if(isAvailable()){
//        SimpleDateFormat  startDate=      new SimpleDateFormat("yyyy-MM-dd").parse("2021-04-1")
//              dateNow = new java.time.LocalDate.now(); 
// if(dateNow.compareTo(date)>0){
// isAvailable();
//}else{
//
// }
//            }
            button.setDisable(true);
        }
    }

    @FXML
    void showbilan1(ActionEvent event) {
        navigateToShow(1);
    }

    @FXML
    private void showbilan2(ActionEvent event) {
        navigateToShow(2);
    }

    @FXML
    private void showbilan3(ActionEvent event) {
        navigateToShow(3);
    }

    @FXML
    private void remplirBilan1(ActionEvent event) {
        navigateToAdd(1);

    }

    @FXML
    private void modifierBilan1(ActionEvent event) {
        navigateToEdit(1);
    }

    @FXML
    private void remplirBilan2(ActionEvent event) {
        navigateToAdd(2);
    }

    @FXML
    private void modifierBilan2(ActionEvent event) {
        navigateToEdit(2);
    }

    @FXML
    private void remplirBilan3(ActionEvent event) {
        navigateToAdd(3);
    }

    @FXML
    private void modifierBilan3(ActionEvent event) {
        navigateToEdit(3);
    }

    public void navigateToShow(int indexPeriode) {
//        try {
//            FXMLLoader mainLoad = new FXMLLoader(getClass().getResource("/views/Bilan.fxml"));
//            mainLoad.getController().set
//            Parent mainRoot = (Parent) mainLoad.load();
//            Stage stage = new Stage();
//            stage.setScene(new Scene(mainRoot));
//            stage.setTitle("Milieu de stage");
//            stage.initStyle(StageStyle.UTILITY);
//            stage.show();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return;
//        }
        bilanService.ind = indexPeriode;
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/views/Bilan.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Milieu de stage");
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(BilansPeriodiquesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void navigateToEdit(int indexPeriode) {
        bilanService.ind = indexPeriode;
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/views/modifierBilan.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Modification de bilan de fin de stage");
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(BilansPeriodiquesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void navigateToAdd(int indexPeriode) {
        bilanService.ind = indexPeriode;
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/views/AjoutBilan.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Remplissage de bilan de fin de stage");
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(BilansPeriodiquesController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
