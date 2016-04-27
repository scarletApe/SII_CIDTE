//<editor-fold defaultstate="collapsed" desc="licence">
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//</editor-fold>
package org.cidte.sii.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

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

    @Column(name = "curp")
    private String curp;

    @Column(name = "tipo_participacion")
    private String tipo_participacion;
    
    @Column(name = "horas")
    private String horas;

    @Column(name = "interno")
    private String interno;

    @Column(name = "institucion")
    private String institucion;

    @Column(name = "area")
    private String area;

    @Column(name = "programa")
    private String programa;

    @Column(name = "fecha_inicio")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha_inicio;

    @Column(name = "fecha_terminacion")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha_terminacion;

    public TipoContratacion() {
    }

    public TipoContratacion(String curp) {
        this.curp = curp;
    }

    public int getId_contratacion() {
        return id_contratacion;
    }

    public void setId_contratacion(int id_contratacion) {
        this.id_contratacion = id_contratacion;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getHoras() {
        return horas;
    }

    public void setHoras(String horas) {
        this.horas = horas;
    }

    public String getInterno() {
        return interno;
    }

    public void setInterno(String interno) {
        this.interno = interno;
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

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public Date getFecha_terminacion() {
        return fecha_terminacion;
    }

    public void setFecha_terminacion(Date fecha_terminacion) {
        this.fecha_terminacion = fecha_terminacion;
    }

    public String getTipo_participacion() {
        return tipo_participacion;
    }

    public void setTipo_participacion(String tipo_participacion) {
        this.tipo_participacion = tipo_participacion;
    }

    @Override
    public String toString() {
        return interno + " " + institucion + " " + area + " " + programa;
    }

    

}
