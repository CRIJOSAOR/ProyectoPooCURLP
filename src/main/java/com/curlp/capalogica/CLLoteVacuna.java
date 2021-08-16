/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curlp.capalogica;

import java.sql.Date;

/**
 *
 * @author sanch
 */
public class CLLoteVacuna {

    private String numLoteVacuna;
    private Date fechaFabricacion;
    private Date fechaVencimiento;
    private int idFbricante;
    private String nombreFabricante;

    public String getNombreFabricante() {
        return nombreFabricante;
    }

    public void setNombreFabricante(String nombreFabricante) {
        this.nombreFabricante = nombreFabricante;
    }

    public String getNumLoteVacuna() {
        return numLoteVacuna;
    }

    public void setNumLoteVacuna(String numLoteVacuna) {
        this.numLoteVacuna = numLoteVacuna;
    }

    public Date getFechaFabricacion() {
        return fechaFabricacion;
    }

    public void setFechaFabricacion(Date fechaFabricacion) {
        this.fechaFabricacion = fechaFabricacion;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }



 

    public int getIdFbricante() {
        return idFbricante;
    }

    public void setIdFbricante(int idFbricante) {
        this.idFbricante = idFbricante;
    }

}
