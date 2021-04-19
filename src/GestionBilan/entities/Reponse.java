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
public class Reponse {
    private int idReponse;
    private String rep;
    private int idQuestion;
    private int idUser;
    private int indexPeriode;

    public Reponse() {
    }

    public Reponse(int idReponse, String rep, int idQuestion, int idUser, int indexPeriode) {
        this.idReponse = idReponse;
        this.rep = rep;
        this.idQuestion = idQuestion;
        this.idUser = idUser;
        this.indexPeriode = indexPeriode;
    }

    public Reponse(String rep, int idQuestion, int idUser, int indexPeriode) {
        this.rep = rep;
        this.idQuestion = idQuestion;
        this.idUser = idUser;
                this.indexPeriode = indexPeriode;
    }

    public int getIndexPeriode() {
        return indexPeriode;
    }

    public void setIndexPeriode(int indexPeriode) {
        this.indexPeriode = indexPeriode;
    }

    public int getIdReponse() {
        return idReponse;
    }

    public void setIdReponse(int idReponse) {
        this.idReponse = idReponse;
    }

    public String getRep() {
        return rep;
    }

    public void setRep(String rep) {
        this.rep = rep;
    }

    public int getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(int idQuestion) {
        this.idQuestion = idQuestion;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Override
    public String toString() {
        return "Reponse{" + "idReponse=" + idReponse + ", rep=" + rep + ", idQuestion=" + idQuestion + ", idUser=" + idUser + ", indexPeriode=" + indexPeriode + '}';
    }

    
    
}
