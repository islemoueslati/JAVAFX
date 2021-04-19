/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import GestionBilan.entities.Question;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import GestionBilan.services.BilanService;
import GestionBilan.services.QuestionService;
import java.util.ArrayList;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javax.swing.JLabel;

/**
 * FXML Controller class
 *
 * @author Islem Oueslati
 */
public class BilanController implements Initializable {

    @FXML
    private Label labelTitre;
    
    // var 
   
        BilanService bilanService = new BilanService();
        QuestionService questionService =  new QuestionService();
        
        //UI Elements
       // JLabel[] labels = new JLabel[100];
        
        // var gotten from the other interface
        private int index = bilanService.ind;
    @FXML
    private GridPane gridPane;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        System.out.println("Hello WOorld");
        labelTitre.setText(bilanService.showBilanByIndexPeriode(index).get(0).getTitre());
//        int a = questionService.showQuestionByIndexPeriode(1).get(0).getIdQuestion();
//        System.out.println("Quest: "+a);
        // Les questions par indexPeriode
        ArrayList<Question> questionArrayList = questionService.showQuestionByIndexPeriode(index);
        for (int i = 0; i < questionArrayList.size(); i++) {
          // labels[i] = new JLabel(questionArrayList.get(i).getQuest());
          Label label = new Label();
          label.setText(questionArrayList.get(i).getQuest());
          gridPane.add(label, 0, i);
            System.out.println(questionArrayList.get(i).getQuest());
        }
        //Les questions repetitives
        ArrayList<Question> allQuestionArrayList = questionService.showQuestionByIndexPeriode(0);
        for (int i = 0; i < allQuestionArrayList.size(); i++) {
          // labels[i] = new JLabel(questionArrayList.get(i).getQuest());
          Label label = new Label();
          label.setText(allQuestionArrayList.get(i).getQuest());
          gridPane.add(label, 0, questionArrayList.size()+i);
            System.out.println(allQuestionArrayList.get(i).getQuest());
        }




//    BilansPeriodiquesController bilanperiodiquecontroller =new BilansPeriodiquesController();
//    BilanService bilanservice = new BilanService();
//    if(bilanperiodiquecontroller.showbilan1(event)){
//        labelTitre.setText(bilanservice.showTitreByIndexPeriode(1));
//    }
    }
    // TODO
}
