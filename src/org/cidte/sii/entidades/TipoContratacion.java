//<editor-fold defaultstate="collapsed" desc="licence">
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//</editor-fold>
package org.cidte.sii.entidades;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author juanmartinez
 */
@Entity
@Table(name = "TipoContratacion")
public class TipoContratacion implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id_contratacion")
    private int id_contratacion;
    
    @Column(name = "participante")
    private String participante;
    
    @Column(name = "horas")
    private String horas;
    
    @Column(name = "lugar_uaz")
    private String lugar_uaz;
    
    @Column(name = "institucion")
    private String institucion;
    
    @Column(name = "area")
    private String area;
    
    @Column(name = "programa")
    private String programa;

    public TipoContratacion() {
    }

    public TipoContratacion(String participante) {
        this.participante = participante;
    }

    public int getId_contratacion() {
        return id_contratacion;
    }

    public void setId_contratacion(int id_contratacion) {
        this.id_contratacion = id_contratacion;
    }

    public String getParticipante() {
        return participante;
    }

    public void setParticipante(String participante) {
        this.participante = participante;
    }

    public String getHoras() {
        return horas;
    }

    public void setHoras(String horas) {
        this.horas = horas;
    }

    public String getLugar_uaz() {
        return lugar_uaz;
    }

    public void setLugar_uaz(String lugar_uaz) {
        this.lugar_uaz = lugar_uaz;
    }

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.id_contratacion;
        hash = 53 * hash + Objects.hashCode(this.horas);
        hash = 53 * hash + Objects.hashCode(this.lugar_uaz);
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
        final TipoContratacion other = (TipoContratacion) obj;
        if (this.id_contratacion != other.id_contratacion) {
            return false;
        }
        if (!Objects.equals(this.horas, other.horas)) {
            return false;
        }
        if (!Objects.equals(this.lugar_uaz, other.lugar_uaz)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TipoContratacion{" + "id_contratacion=" + id_contratacion + ", participante=" + participante + ", horas=" + horas + ", lugar_uaz=" + lugar_uaz + ", institucion=" + institucion + ", area=" + area + ", programa=" + programa + '}';
    }

}
