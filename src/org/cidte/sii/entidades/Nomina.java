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
@Table(name = "Nomina")
public class Nomina implements Serializable {

     @Id
    @GeneratedValue
    @Column(name = "id_nomina")
    private int id_nomina;
     
     @Column(name = "participante")
    private String participante;
     
     @Column(name = "lugar_uaz")
    private String lugar_uaz;
     
     @Column(name = "internos")
    private String internos;
     
     @Column(name = "grupo_laboral")
    private String grupo_laboral;
     
     @Column(name = "nivel")
    private String nivel;
     
     @Column(name = "categorial")
    private String categorial;
     
     @Column(name = "forma_contratacion")
    private String forma_contratacion;
     
     @Column(name = "descripcion")
    private String descripcion ;
     
     @Column(name = "pdf")
    private byte[] pdf;

    public Nomina() {
    }

    public Nomina(String participante) {
        this.participante = participante;
    }

    public int getId_nomina() {
        return id_nomina;
    }

    public void setId_nomina(int id_nomina) {
        this.id_nomina = id_nomina;
    }

    public String getParticipante() {
        return participante;
    }

    public void setParticipante(String participante) {
        this.participante = participante;
    }

    public String getLugar_uaz() {
        return lugar_uaz;
    }

    public void setLugar_uaz(String lugar_uaz) {
        this.lugar_uaz = lugar_uaz;
    }

    public String getInternos() {
        return internos;
    }

    public void setInternos(String internos) {
        this.internos = internos;
    }

    public String getGrupo_laboral() {
        return grupo_laboral;
    }

    public void setGrupo_laboral(String grupo_laboral) {
        this.grupo_laboral = grupo_laboral;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getCategorial() {
        return categorial;
    }

    public void setCategorial(String categorial) {
        this.categorial = categorial;
    }

    public String getForma_contratacion() {
        return forma_contratacion;
    }

    public void setForma_contratacion(String forma_contratacion) {
        this.forma_contratacion = forma_contratacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public byte[] getPdf() {
        return pdf;
    }

    public void setPdf(byte[] pdf) {
        this.pdf = pdf;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.id_nomina;
        hash = 67 * hash + Objects.hashCode(this.lugar_uaz);
        hash = 67 * hash + Objects.hashCode(this.internos);
        hash = 67 * hash + Objects.hashCode(this.grupo_laboral);
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
        final Nomina other = (Nomina) obj;
        if (this.id_nomina != other.id_nomina) {
            return false;
        }
        if (!Objects.equals(this.lugar_uaz, other.lugar_uaz)) {
            return false;
        }
        if (!Objects.equals(this.internos, other.internos)) {
            return false;
        }
        if (!Objects.equals(this.grupo_laboral, other.grupo_laboral)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Nomina{" + "id_nomina=" + id_nomina + ", participante=" + participante + ", lugar_uaz=" + lugar_uaz + ", internos=" + internos + ", grupo_laboral=" + grupo_laboral + ", nivel=" + nivel + ", categorial=" + categorial + ", forma_contratacion=" + forma_contratacion + ", descripcion=" + descripcion + '}';
    }
    
    
}
