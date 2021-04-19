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
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author Islem Oueslati
 */
public class NewClass implements Initializable {
BilanService bilanService = new BilanService();
    QuestionService questionService = new QuestionService();
    ReponseService reponseService = new ReponseService();
    // var gotten from the other interface
    private int index = bilanService.ind;
    private int idUser = bilanService.selectedUser;
    @FXML
    private GridPane gridPane;

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
                 reponseTextField.setDisable(true);
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
            reponseTextField.setDisable(true);
            label.setText(allQuestionArrayList.get(i).getQuest());
            gridPane.add(label, 0, allQuestionArrayList.size() + i);
            gridPane.add(reponseTextField, 1, allQuestionArrayList.size() + i);
            System.out.println(allQuestionArrayList.get(i).getQuest());
        }
        // TODO
    }    
    
}
