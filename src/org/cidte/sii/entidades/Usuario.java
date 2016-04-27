/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidte.sii.entidades;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author manuelmartinez
 */
public class Usuario implements Serializable {

    private String curp;
    private String username;
    private String password;
    private String rol;

//    private TipoUsuario tipoUsario;
    public Usuario() {
    }

    public Usuario(String curp, String username, String password, String rol) {
        this.curp = curp;
        this.username = username;
        this.password = password;
        this.rol = rol;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.curp);
        hash = 11 * hash + Objects.hashCode(this.username);
        hash = 11 * hash + Objects.hashCode(this.password);
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
        final Usuario other = (Usuario) obj;
        if (this.curp != other.curp) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
//        return "Usuario{" + "curp=" + curp + ", username=" + username + ", password=" + password + ", rol=" + rol + '}';
        return curp;
    }

}
