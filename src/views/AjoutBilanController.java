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
import com.sun.java.swing.plaf.windows.resources.windows;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Islem Oueslati
 */
public class AjoutBilanController implements Initializable {

    BilanService bilanService = new BilanService();
    QuestionService questionService = new QuestionService();
    ReponseService reponseService = new ReponseService();
    // var gotten from the other interface
    private int index = bilanService.ind;
    private int idUser = 119;
    @FXML
    private GridPane gridPane;
    @FXML
    private Button addBilanButton;
    @FXML
    private AnchorPane anchorPane;

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
        for (int i = 0; i < questionArrayList.size(); i++) {
            // labels[i] = new JLabel(questionArrayList.get(i).getQuest());
            Label questionLabel = new Label();
            TextField reponseTextField = new TextField();
            reponseTextField.setId("" + questionArrayList.get(i).getIdQuestion());
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
            reponseTextField.setId("" + allQuestionArrayList.get(i).getIdQuestion());
            label.setText(allQuestionArrayList.get(i).getQuest());
            gridPane.add(label, 0, allQuestionArrayList.size() + i);
            gridPane.add(reponseTextField, 1, allQuestionArrayList.size() + i);
            System.out.println(allQuestionArrayList.get(i).getQuest());
        }
        addBilanButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                for (Node node : gridPane.getChildren()) {
                    if (node instanceof TextField) {
                        // clear
                       System.out.println("FORId: " + node.getId());
                       Reponse reponse = new Reponse(((TextField) node).getText(), Integer.parseInt(node.getId()), idUser, index);
                reponseService.addReponse(reponse);
                    }
                }
                //Notification
                String title = "succes ";
        String message = "Bilan remplis avec succes";
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
                // get a handle to the stage
    //getScene().getWindow().close();
    ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
              
            }
        });
        
    }
}
