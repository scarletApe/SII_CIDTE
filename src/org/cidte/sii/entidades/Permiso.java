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

import org.hibernate.annotations.Type;

/**
 *
 * @author juanmartinez
 */
@Entity
@Table(name = "Permiso")
public class Permiso implements Serializable {

	/*
	 * id_permiso INT NOT NULL AUTO_INCREMENT, curp CHAR(18) NOT NULL,
	 * motivo_solicitud VARCHAR(120), fecha_solicitud DATE, autorizado CHAR(1),
	 * fecha_autorizacion DATE, documento Blob , PRIMARY KEY(id_permiso)
	 */
	@Id
	@GeneratedValue
	@Column(name = "id_permiso")
	private int id_permiso;

	@Column(name = "curp")
	private String curp;

	@Column(name = "motivo_solicitud")
	private String motivo_solicitud;

	@Column(name = "fecha_solicitud")
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date fecha_solicitud;

	@Type(type = "true_false")
	@Column(name = "autorizado")
	private boolean autorizado;

	@Column(name = "fecha_autorizacion")
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date fecha_autorizacion;

	@Column(name = "id_documentopdf")
	private int id_documentopdf;

	public Permiso() {
	}

	public Permiso(String curp, String motivo_solicitud, Date fecha_solicitud, boolean autorizado,
			Date fecha_autorizacion, int id_documentopdf) {
		this.curp = curp;
		this.motivo_solicitud = motivo_solicitud;
		this.fecha_solicitud = fecha_solicitud;
		this.autorizado = autorizado;
		this.fecha_autorizacion = fecha_autorizacion;
		this.id_documentopdf = id_documentopdf;
	}

	public int getId_permiso() {
		return id_permiso;
	}

	public void setId_permiso(int id_permiso) {
		this.id_permiso = id_permiso;
	}

	public String getCurp() {
		return curp;
	}

	public void setCurp(String curp) {
		this.curp = curp;
	}

	public String getMotivo_solicitud() {
		return motivo_solicitud;
	}

	public void setMotivo_solicitud(String motivo_solicitud) {
		this.motivo_solicitud = motivo_solicitud;
	}

	public Date getFecha_solicitud() {
		return fecha_solicitud;
	}

	public void setFecha_solicitud(Date fecha_solicitud) {
		this.fecha_solicitud = fecha_solicitud;
	}

	public boolean isAutorizado() {
		return autorizado;
	}

	public void setAutorizado(boolean autorizado) {
		this.autorizado = autorizado;
	}

	public Date getFecha_autorizacion() {
		return fecha_autorizacion;
	}

	public void setFecha_autorizacion(Date fecha_autorizacion) {
		this.fecha_autorizacion = fecha_autorizacion;
	}

	public int getDocumento() {
		return id_documentopdf;
	}

	public void setDocumento(int documento) {
		this.id_documentopdf = documento;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 41 * hash + this.id_permiso;
		hash = 41 * hash + Objects.hashCode(this.curp);
		hash = 41 * hash + Objects.hashCode(this.fecha_solicitud);
		hash = 41 * hash + (this.autorizado ? 1 : 0);
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
		final Permiso other = (Permiso) obj;
		if (this.id_permiso != other.id_permiso) {
			return false;
		}
		if (this.autorizado != other.autorizado) {
			return false;
		}
		if (!Objects.equals(this.curp, other.curp)) {
			return false;
		}
		if (!Objects.equals(this.fecha_solicitud, other.fecha_solicitud)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Permiso{" + "id_permiso=" + id_permiso + ", curp=" + curp + ", motivo_solicitud=" + motivo_solicitud
				+ ", fecha_solicitud=" + fecha_solicitud + ", autorizado=" + autorizado + ", fecha_autorizacion="
				+ fecha_autorizacion + '}';
	}

}
