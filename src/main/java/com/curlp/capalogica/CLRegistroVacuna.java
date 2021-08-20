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
public class CLRegistroVacuna {

    int idRegistroVacuna;
    Date fechaVacunacion;
    String dniVacunador;
    int codEstablecimiento;
    String numLoteVacuna;
    String dniPaciente;
    int dosis;

    public int getIdRegistroVacuna() {
        return idRegistroVacuna;
    }

    public void setIdRegistroVacuna(int idRegistroVacuna) {
        this.idRegistroVacuna = idRegistroVacuna;
    }

    public Date getFechaVacunacion() {
        return fechaVacunacion;
    }

    public void setFechaVacunacion(Date fechaVacunacion) {
        this.fechaVacunacion = fechaVacunacion;
    }

    public String getDniVacunador() {
        return dniVacunador;
    }

    public void setDniVacunador(String dniVacunador) {
        this.dniVacunador = dniVacunador;
    }

    public int getCodEstablecimiento() {
        return codEstablecimiento;
    }

    public void setCodEstablecimiento(int codEstablecimiento) {
        this.codEstablecimiento = codEstablecimiento;
    }

    public String getNumLoteVacuna() {
        return numLoteVacuna;
    }

    public void setNumLoteVacuna(String numLoteVacuna) {
        this.numLoteVacuna = numLoteVacuna;
    }

    public String getDniPaciente() {
        return dniPaciente;
    }

    public void setDniPaciente(String dniPaciente) {
        this.dniPaciente = dniPaciente;
    }

    public int getDosis() {
        return dosis;
    }

    public void setDosis(int dosis) {
        this.dosis = dosis;
    }
    
    

}
