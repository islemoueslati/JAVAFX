/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import GestionBilan.entities.SuiviBilans;
import GestionBilan.services.BilanService;
import GestionBilan.tools.MyConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Islem Oueslati
 */
public class SuiviBilansController implements Initializable {

    @FXML
    private TableColumn<SuiviBilans, String> Etudiant;
    @FXML
    private TableColumn<SuiviBilans, String> DebutStage;
    @FXML
    private TableColumn<SuiviBilans, String> MilieuStage;
    @FXML
    private TableColumn<SuiviBilans, String> FinStage;
    @FXML
    private TableColumn<SuiviBilans, Date> DateDebutStage;
    @FXML
    private TableView<SuiviBilans> ViewSuivi;
    @FXML
    private Button afficherPeriod1Button;
    @FXML
    private Button afficherPeriod2Button;
    @FXML
    private Button afficherPeriod3Button;

    MyConnection myConnection = new MyConnection();
    BilanService bilanService = new BilanService();
    @FXML
    private PieChart piechart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showsuivibilans();
        afficherPeriod1Button.setDisable(true);
        afficherPeriod2Button.setDisable(true);
        afficherPeriod3Button.setDisable(true);
        piechart.setData(getData());
        //   String a = ViewSuivi.getSelectionModel().getSelectedItem().getFull_name();
        //  System.out.println(a);
        afficherPeriod1Button.setOnAction((ActionEvent event) -> {
            onNavigate(1);
        });
        afficherPeriod2Button.setOnAction((ActionEvent event) -> {
            onNavigate(2);
        });
        afficherPeriod3Button.setOnAction((ActionEvent event) -> {
            onNavigate(3);
        });
    }

    public ObservableList<SuiviBilans> getsuivibilansList() {
        ObservableList<SuiviBilans> suivibilanslist = FXCollections.observableArrayList();
        // ArrayList<Integer> index = new ArrayList<>();
        MyConnection myConnection = new MyConnection();
        String testRemplissagePeriode1 = "Nom Rempli";
        String testRemplissagePeriode2 = "Non Rempli";
        String testRemplissagePeriode3 = "Non Rempli";
        SuiviBilans suivibilans;
        String query = "SELECT id_user , full_name , debut_stage FROM user where role='etudiant'";
        // String queryIndexPeriode = "SELECT DISTINCT indexPeriode FROM reponse WHERE idUser IN ( SELECT id_user FROM user WHERE full_name = ?)";
        String queryIndexPeriode = "SELECT DISTINCT indexPeriode FROM reponse WHERE idUser = ?";
        PreparedStatement preparedstatement, preparedStatement;
        ResultSet resultset, resultSet;
        try {
            preparedstatement = myConnection.getConnection().prepareStatement(query);
            resultset = preparedstatement.executeQuery();

            while (resultset.next()) {
                preparedStatement = myConnection.getConnection().prepareStatement(queryIndexPeriode);
                preparedStatement.setInt(1, resultset.getInt("id_user"));
                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    // index.add(resultSet.getInt("indexPeriode"));
                    if (resultSet.getInt("indexPeriode") == 1) {
                        testRemplissagePeriode1 = "Rempli";
                    }
                    if (resultSet.getInt("indexPeriode") == 2) {
                        testRemplissagePeriode2 = "Rempli";
                    }
                    if (resultSet.getInt("indexPeriode") == 3) {
                        testRemplissagePeriode3 = "Rempli";
                    }

                    System.out.println(resultset.getInt("id_user"));
                    suivibilans = new SuiviBilans(resultset.getString("full_name"), testRemplissagePeriode1, testRemplissagePeriode2, testRemplissagePeriode3, resultset.getDate("debut_stage"));
                    suivibilanslist.add(suivibilans);
                }

                testRemplissagePeriode1 = "Nom Rempli";
                testRemplissagePeriode2 = "Non Rempli";
                testRemplissagePeriode3 = "Non Rempli";
            }

        } catch (SQLException ex) {
            Logger.getLogger(SuiviBilansController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return suivibilanslist;

    }

    public void showsuivibilans() {
        ObservableList<SuiviBilans> suiviBilansArrayList = getsuivibilansList();
        for (int i = 0; i < suiviBilansArrayList.size(); i++) {

            for (int j = i + 1; j < suiviBilansArrayList.size(); j++) {
                if (suiviBilansArrayList.get(i).getFull_name().equals(suiviBilansArrayList.get(j).getFull_name())) {
                    suiviBilansArrayList.remove(i);
                    j--;
                }
            }
        }
            //ObservableList<SuiviBilans> list = getsuivibilansList();
           ObservableList<SuiviBilans> list = suiviBilansArrayList;
            Etudiant.setCellValueFactory(new PropertyValueFactory<>("full_name"));
            DebutStage.setCellValueFactory(new PropertyValueFactory<>("BilanDebut"));
            MilieuStage.setCellValueFactory(new PropertyValueFactory<>("BilanMilieu"));
            FinStage.setCellValueFactory(new PropertyValueFactory<>("BilanFin"));
            DateDebutStage.setCellValueFactory(new PropertyValueFactory<>("debut_stage"));
            ViewSuivi.setItems(list);
        }

        @FXML
        private void isSelected
        (MouseEvent event
        
            ) {
        afficherPeriod1Button.setDisable(true);
            afficherPeriod2Button.setDisable(true);
            afficherPeriod3Button.setDisable(true);
            String fullname = ViewSuivi.getSelectionModel().getSelectedItem().getFull_name();
            String debutStage = ViewSuivi.getSelectionModel().getSelectedItem().getBilanDebut();
            String milieuStage = ViewSuivi.getSelectionModel().getSelectedItem().getBilanMilieu();
            String finStage = ViewSuivi.getSelectionModel().getSelectedItem().getBilanFin();
            System.out.println(fullname);
            if (debutStage.equals("Rempli")) {
                afficherPeriod1Button.setDisable(false);
            }
            if (milieuStage.equals("Rempli")) {
                afficherPeriod2Button.setDisable(false);
            }
            if (finStage.equals("Rempli")) {
                afficherPeriod3Button.setDisable(false);
            }

        }
    

    private void onNavigate(int indexPeriode) {
        bilanService.selectedUser = searchUser(ViewSuivi.getSelectionModel().getSelectedItem().getFull_name());
        bilanService.ind = indexPeriode;
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/views/DisplayReponse.fxml"));
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

    private int searchUser(String fullName) {
        int id = 0;
        try {
            String query = "SELECT id_user FROM user WHERE full_name = ?";
            PreparedStatement preparedStatement = myConnection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, fullName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getInt("id_user");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SuiviBilansController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    private ObservableList<PieChart.Data> getData() {
        ObservableList<PieChart.Data> data = FXCollections.observableArrayList();

        try {
            for (int i = 0; i < 3; i++) {
                int a = i + 1;
                String req = "SELECT COUNT(DISTINCT idUser) AS users FROM reponse WHERE indexPeriode = " + a;
                PreparedStatement preparedStatement = myConnection.getConnection().prepareStatement(req);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    data.add(new PieChart.Data("Bilan " + a, resultSet.getInt("users")));
                    System.out.println(resultSet.getInt("users"));
                }
            }
        } catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage());
        }
        //   data.add(new PieChart.Data ("Participation", 1));
        //                               data.add(new PieChart.Data ("Participation", 3 ));
        return data;
    }

}


