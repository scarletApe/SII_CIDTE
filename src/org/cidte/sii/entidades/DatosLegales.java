/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidte.sii.entidades;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author juanmartinez
 */
public class DatosLegales {

    private int id;
    private Usuario curp;
    private String nacionalidad;
    private String visa_americana;
    private String id_pasaporte;
    private Date vijencia_pasaporte;
    private String licencia_manejo;
    private Date vijencia_licencia;

    public DatosLegales() {
    }

    public DatosLegales(Usuario curp) {
        this.curp = curp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getCurp() {
        return curp;
    }

    public void setCurp(Usuario curp) {
        this.curp = curp;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getVisa_americana() {
        return visa_americana;
    }

    public void setVisa_americana(String visa_americana) {
        this.visa_americana = visa_americana;
    }

    public String getId_pasaporte() {
        return id_pasaporte;
    }

    public void setId_pasaporte(String id_pasaporte) {
        this.id_pasaporte = id_pasaporte;
    }

    public Date getVijencia_pasaporte() {
        return vijencia_pasaporte;
    }

    public void setVijencia_pasaporte(Date vijencia_pasaporte) {
        this.vijencia_pasaporte = vijencia_pasaporte;
    }

    public String getLicencia_manejo() {
        return licencia_manejo;
    }

    public void setLicencia_manejo(String licencia_manejo) {
        this.licencia_manejo = licencia_manejo;
    }

    public Date getVijencia_licencia() {
        return vijencia_licencia;
    }

    public void setVijencia_licencia(Date vijencia_licencia) {
        this.vijencia_licencia = vijencia_licencia;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + this.id;
        hash = 89 * hash + Objects.hashCode(this.curp);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DatosLegales other = (DatosLegales) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.curp, other.curp)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DatosLegales{" + "id=" + id + ", curp=" + curp + ", nacionalidad=" + nacionalidad + ", visa_americana=" + visa_americana + ", id_pasaporte=" + id_pasaporte + '}';
    }

}
