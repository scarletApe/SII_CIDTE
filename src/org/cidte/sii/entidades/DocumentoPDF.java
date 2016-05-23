//<editor-fold defaultstate="collapsed" desc="licence">
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//</editor-fold>
package org.cidte.sii.entidades;

import java.io.Serializable;
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
@Table(name = "DocumentoPDF")
public class DocumentoPDF implements Serializable {

    /*
    id_documentopdf INT NOT NULL auto_increment,
	nombre VARCHAR(160) NOT NULL,
	documento Longblob NOT NULL,
     */
    @Id
    @GeneratedValue
    @Column(name = "id_documentopdf")
    private int id_documentopdf;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "documento")
    private byte[] documento;

    public DocumentoPDF() {
    }

    public DocumentoPDF(String nombre, byte[] documento) {
        this.nombre = nombre;
        this.documento = documento;
    }

    public int getId_documentopdf() {
        return id_documentopdf;
    }

    public void setId_documentopdf(int id_documentopdf) {
        this.id_documentopdf = id_documentopdf;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public byte[] getDocumento() {
        return documento;
    }

    public void setDocumento(byte[] documento) {
        this.documento = documento;
    }

    @Override
    public String toString() {
        return "DocumentoPDF{" + "id_documentopdf=" + id_documentopdf + ", nombre=" + nombre + '}';
    }

}
