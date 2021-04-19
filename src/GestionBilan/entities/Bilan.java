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
public class Bilan {

    private int idBilan;
    private String titre;
    private int indexPeriode;
    private Date periode;
    

    public int getIndexPeriode() {
        return indexPeriode;
    }

    public void setIndexPeriode(int indexPeriode) {
        this.indexPeriode = indexPeriode;
    }

    public Bilan() {
    }

    public Bilan(String titre, Date periode, int indexPeriode) {
        this.titre = titre;
        this.periode = periode;
        this.indexPeriode = indexPeriode;
    }

    public Bilan(int idBilan, String titre, int indexPeriode, Date periode) {
        this.idBilan = idBilan;
        this.titre = titre;
        this.periode = periode;
        this.indexPeriode = indexPeriode;
    }

    public int getIdBilan() {
        return idBilan;
    }

    public void setIdBilan(int idBilan) {
        this.idBilan = idBilan;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Date getPeriode() {
        return periode;
    }

    public void setPeriode(Date periode) {
        this.periode = periode;
    }

    @Override
    public String toString() {
        return "Bilan{" + "idBilan=" + idBilan + ", titre=" + titre + ", indexPeriode=" + indexPeriode + ", periode=" + periode + '}';
    }

}
