/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionBilan.entities;

/**
 *
 * @author Islem Oueslati
 */
public class Question {
    private int idQuestion;
    private String quest;
    private int indexPeriode;
    public Question() {
        
    }

    public Question(String quest, int indexPeriode) {
        this.quest = quest;
        this.indexPeriode = indexPeriode;
    }

    public Question(int idQuestion, String quest, int indexPeriode) {
        this.idQuestion = idQuestion;
        this.quest = quest;
        this.indexPeriode = indexPeriode;
    }

    public int getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(int idQuestion) {
        this.idQuestion = idQuestion;
    }

    public String getQuest() {
        return quest;
    }

    public void setQuest(String quest) {
        this.quest = quest;
    }

    public int getIndexPeriode() {
        return indexPeriode;
    }

    public void setIndexPeriode(int indexPeriode) {
        this.indexPeriode = indexPeriode;
    }

    @Override
    public String toString() {
        return "Question{" + "idQuestion=" + idQuestion + ", quest=" + quest + ", indexPeriode=" + indexPeriode + '}';
    }
    
    
    
    
    
    
}
