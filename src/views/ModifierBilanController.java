/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import GestionBilan.entities.Question;
import GestionBilan.entities.Reponse;
import GestionBilan.services.BilanService;
import GestionBilan.services.QuestionService;
import GestionBilan.services.ReponseService;
import GestionBilan.tools.SendMail;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Islem Oueslati
 */
public class ModifierBilanController implements Initializable {
BilanService bilanService = new BilanService();
    QuestionService questionService = new QuestionService();
    ReponseService reponseService = new ReponseService();
    // var gotten from the other interface
    private int index = bilanService.ind;
    private int idUser = 118;
    @FXML
    private GridPane gridPane;
    @FXML
    private Button editBilanButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

//        int a = questionService.showQuestionByIndexPeriode(1).get(0).getIdQuestion();
//        System.out.println("Quest: "+a);
        // Les questions par indexPeriode
        ArrayList<Question> questionArrayList = questionService.showQuestionByIndexPeriode(index);
        ArrayList<Reponse> reponseArrayList = reponseService.showReponseByIdUser(idUser, index);
        for (int i = 0; i < questionArrayList.size(); i++) {
            // labels[i] = new JLabel(questionArrayList.get(i).getQuest());
            Label questionLabel = new Label();
            TextField reponseTextField = new TextField();
            reponseTextField.setId("" + reponseArrayList.get(i).getIdReponse());
            reponseTextField.setText(""+reponseArrayList.get(i).getRep());
            questionLabel.setText(questionArrayList.get(i).getQuest());
            gridPane.add(questionLabel, 0, i);
            gridPane.add(reponseTextField, 1, i);
            System.out.println(questionArrayList.get(i).getQuest());
        }
        //Les questions repetitives
        ArrayList<Question> allQuestionArrayList = questionService.showQuestionByIndexPeriode(0);
        for (int i = 0; i < allQuestionArrayList.size(); i++) {
            // labels[i] = new JLabel(questionArrayList.get(i).getQuest());
            Label label = new Label();
            //RadioButton choixRadioButton+i = new RadioButton();
            TextField reponseTextField = new TextField();
            reponseTextField.setId("" + reponseArrayList.get(questionArrayList.size()+i).getIdReponse());
            reponseTextField.setText(""+reponseArrayList.get(questionArrayList.size() +i).getRep());
            label.setText(allQuestionArrayList.get(i).getQuest());
            gridPane.add(label, 0, allQuestionArrayList.size() + i);
            gridPane.add(reponseTextField, 1, allQuestionArrayList.size() + i);
            System.out.println(allQuestionArrayList.get(i).getQuest());
        }
        editBilanButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                for (Node node : gridPane.getChildren()) {
                    if (node instanceof TextField) {
                        // clear
                       Reponse reponse = new Reponse(((TextField) node).getText(), Integer.parseInt(node.getId()), idUser, index);
                reponseService.updateReponse(Integer.parseInt(node.getId()),reponse);
                    }
                }
                // send notification
                String title = "succes ";
        String message = "Bilan modifié avec succes";
        TrayNotification tray = new TrayNotification();
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndWait();
        // send Mail
                try {
                    SendMail.sendMail("islemoueslati2017@gmail.com");
                } catch (Exception ex) {
                    Logger.getLogger(AjoutBilanController.class.getName()).log(Level.SEVERE, null, ex);
                }   
    ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
            }
        });
        // TODO
    }    
    
}
