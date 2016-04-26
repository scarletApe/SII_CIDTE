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
public class TipoUsuario {

    private int id_tipousario;
    private String nombre;
    private String descripcion;
    private String rol;

    public TipoUsuario() {
    }

    public TipoUsuario(String nombre, String descripcion, String rol) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.rol = rol;
    }

    public int getId_tipousario() {
        return id_tipousario;
    }

    public void setId_tipousario(int id_tipousario) {
        this.id_tipousario = id_tipousario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 13 * hash + this.id_tipousario;
        hash = 13 * hash + Objects.hashCode(this.nombre);
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
        final TipoUsuario other = (TipoUsuario) obj;
        if (this.id_tipousario != other.id_tipousario) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return rol +" "+nombre;
    }

}
