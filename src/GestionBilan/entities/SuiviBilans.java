/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionBilan.entities;

import java.sql.Date;

/**
 *
 * @author Islem Oueslati
 */
public class SuiviBilans {
    private int idUser;
    private String full_name;
    private String BilanDebut;
    private String BilanMilieu;
    private String BilanFin;
    private Date debut_stage;

    public SuiviBilans() {
    }

    public SuiviBilans(String full_name, Date debut_stage) {
        this.full_name = full_name;
        this.debut_stage = debut_stage;
    }

    public SuiviBilans(String BilanDebut, String BilanMilieu, String BilanFin) {
        this.BilanDebut = BilanDebut;
        this.BilanMilieu = BilanMilieu;
        this.BilanFin = BilanFin;
    }

    public SuiviBilans(int idUser, String full_name, String BilanDebut, String BilanMilieu, String BilanFin, Date debut_stage) {
        this.idUser = idUser;
        this.full_name = full_name;
        this.BilanDebut = BilanDebut;
        this.BilanMilieu = BilanMilieu;
        this.BilanFin = BilanFin;
        this.debut_stage = debut_stage;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public SuiviBilans(String full_name, String BilanDebut, String BilanMilieu, String BilanFin, Date debut_stage) {
        this.full_name = full_name;
        this.BilanDebut = BilanDebut;
        this.BilanMilieu = BilanMilieu;
        this.BilanFin = BilanFin;
        this.debut_stage = debut_stage;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getBilanDebut() {
        return BilanDebut;
    }

    public void setBilanDebut(String BilanDebut) {
        this.BilanDebut = BilanDebut;
    }

    public String getBilanMilieu() {
        return BilanMilieu;
    }

    public void setBilanMilieu(String BilanMilieu) {
        this.BilanMilieu = BilanMilieu;
    }

    public String getBilanFin() {
        return BilanFin;
    }

    public void setBilanFin(String BilanFin) {
        this.BilanFin = BilanFin;
    }

    public Date getDebut_stage() {
        return debut_stage;
    }

    public void setDebut_stage(Date debut_stage) {
        this.debut_stage = debut_stage;
    }

    @Override
    public String toString() {
        return "SuiviBilans{" + "idUser=" + idUser + ", full_name=" + full_name + ", BilanDebut=" + BilanDebut + ", BilanMilieu=" + BilanMilieu + ", BilanFin=" + BilanFin + ", debut_stage=" + debut_stage + '}';
    }

    
    

    
}