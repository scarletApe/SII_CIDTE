/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidte.sii.entidades;

import java.util.Objects;

/**
 *
 * @author juanmartinez
 */
public class DatosMedicos {

    private int id;
    private Usuario curp;
    private String numero_ss;
    private String tipo_sangre;
    private int peso;
    private String lentes;
    private String alergias;
    private String discapacidades;
    private String tratamiento_vida;
    private String entidad_medica;

    public DatosMedicos() {
    }

    public DatosMedicos(Usuario curp) {
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

    public String getNumero_ss() {
        return numero_ss;
    }

    public void setNumero_ss(String numero_ss) {
        this.numero_ss = numero_ss;
    }

    public String getTipo_sangre() {
        return tipo_sangre;
    }

    public void setTipo_sangre(String tipo_sangre) {
        this.tipo_sangre = tipo_sangre;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public String getLentes() {
        return lentes;
    }

    public void setLentes(String lentes) {
        this.lentes = lentes;
    }

    public String getAlergias() {
        return alergias;
    }

    public void setAlergias(String alergias) {
        this.alergias = alergias;
    }

    public String getDiscapacidades() {
        return discapacidades;
    }

    public void setDiscapacidades(String discapacidades) {
        this.discapacidades = discapacidades;
    }

    public String getTratamiento_vida() {
        return tratamiento_vida;
    }

    public void setTratamiento_vida(String tratamiento_vida) {
        this.tratamiento_vida = tratamiento_vida;
    }

    public String getEntidad_medica() {
        return entidad_medica;
    }

    public void setEntidad_medica(String entidad_medica) {
        this.entidad_medica = entidad_medica;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.id;
        hash = 97 * hash + Objects.hashCode(this.curp);
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
        final DatosMedicos other = (DatosMedicos) obj;
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
        return "DatosMedicos{" + "id=" + id + ", curp=" + curp + ", numero_ss=" + numero_ss + ", tipo_sangre=" + tipo_sangre + ", peso=" + peso + ", lentes=" + lentes + ", alergias=" + alergias + ", discapacidades=" + discapacidades + ", tratamiento_vida=" + tratamiento_vida + '}';
    }

}
