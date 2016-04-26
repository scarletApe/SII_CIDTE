/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidte.sii.entidades;

import java.util.Objects;
import java.util.logging.Logger;

/**
 *
 * @author juanmartinez
 */
public class DatosPersonales {

    private int id;
    private Usuario curp;
    private String religion;
    private String estado_civil;
    private int talla_altura;
    private int talla_cintura;
    private int talla_cuello;
    private int talla_calzado;

    public DatosPersonales() {
    }

    public DatosPersonales(Usuario curp) {
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

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getEstado_civil() {
        return estado_civil;
    }

    public void setEstado_civil(String estado_civil) {
        this.estado_civil = estado_civil;
    }

    public int getTalla_altura() {
        return talla_altura;
    }

    public void setTalla_altura(int talla_altura) {
        this.talla_altura = talla_altura;
    }

    public int getTalla_cintura() {
        return talla_cintura;
    }

    public void setTalla_cintura(int talla_cintura) {
        this.talla_cintura = talla_cintura;
    }

    public int getTalla_cuello() {
        return talla_cuello;
    }

    public void setTalla_cuello(int talla_cuello) {
        this.talla_cuello = talla_cuello;
    }

    public int getTalla_calzado() {
        return talla_calzado;
    }

    public void setTalla_calzado(int talla_calzado) {
        this.talla_calzado = talla_calzado;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + this.id;
        hash = 47 * hash + Objects.hashCode(this.curp);
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
        final DatosPersonales other = (DatosPersonales) obj;
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
        return "DatosPersonales{" + "id=" + id + ", curp=" + curp + ", religion=" + religion + ", estado_civil=" + estado_civil + ", talla_altura=" + talla_altura + ", talla_cintura=" + talla_cintura + ", talla_cuello=" + talla_cuello + ", talla_calzado=" + talla_calzado + '}';
    }
    
    
}
