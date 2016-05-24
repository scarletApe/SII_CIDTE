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
public class Organizacion {

	private int id_organizacion;
	private String nombre;
	private String mensaje;
	private byte[] logo;

	public Organizacion() {
	}

	public Organizacion(String nombre, String mensaje, byte[] logo) {
		this.nombre = nombre;
		this.mensaje = mensaje;
		this.logo = logo;
	}

	public int getId_organizacion() {
		return id_organizacion;
	}

	public void setId_organizacion(int id_organizacion) {
		this.id_organizacion = id_organizacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public byte[] getLogo() {
		return logo;
	}

	public void setLogo(byte[] logo) {
		this.logo = logo;
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 59 * hash + this.id_organizacion;
		hash = 59 * hash + Objects.hashCode(this.nombre);
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
		final Organizacion other = (Organizacion) obj;
		if (this.id_organizacion != other.id_organizacion) {
			return false;
		}
		if (!Objects.equals(this.nombre, other.nombre)) {
			return false;
		}
		return true;
	}

}
