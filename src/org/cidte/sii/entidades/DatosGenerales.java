/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidte.sii.entidades;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author juanmartinez
 */
public class DatosGenerales {

	private int id;
	private Usuario usuario;

	private String rfc;
	private String nombre;
	private String apaterno;
	private String amaterno;
	private String calle;
	private String colonia;
	private String ciudad;
	private String estado;
	private int codigopostal;
	private int numero_ext;
	private int numero_int;
	private String telefono_casa;
	private String telefono_cell;
	private String telefono_oficina;
	private Date fecha_nacimiento;
	private String sexo;
	private byte[] foto;
	private String email;

	public DatosGenerales() {
	}

	public DatosGenerales(Usuario usuario) {
		this.usuario = usuario;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApaterno() {
		return apaterno;
	}

	public void setApaterno(String apaterno) {
		this.apaterno = apaterno;
	}

	public String getAmaterno() {
		return amaterno;
	}

	public void setAmaterno(String amaterno) {
		this.amaterno = amaterno;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getColonia() {
		return colonia;
	}

	public void setColonia(String colonia) {
		this.colonia = colonia;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public int getCodigopostal() {
		return codigopostal;
	}

	public void setCodigopostal(int codigopostal) {
		this.codigopostal = codigopostal;
	}

	public int getNumero_ext() {
		return numero_ext;
	}

	public void setNumero_ext(int numero_ext) {
		this.numero_ext = numero_ext;
	}

	public int getNumero_int() {
		return numero_int;
	}

	public void setNumero_int(int numero_int) {
		this.numero_int = numero_int;
	}

	public String getTelefono_casa() {
		return telefono_casa;
	}

	public void setTelefono_casa(String telefono_casa) {
		this.telefono_casa = telefono_casa;
	}

	public String getTelefono_cell() {
		return telefono_cell;
	}

	public void setTelefono_cell(String telefono_cell) {
		this.telefono_cell = telefono_cell;
	}

	public String getTelefono_oficina() {
		return telefono_oficina;
	}

	public void setTelefono_oficina(String telefono_oficina) {
		this.telefono_oficina = telefono_oficina;
	}

	public Date getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	public void setFecha_nacimiento(Date fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 67 * hash + this.id;
		hash = 67 * hash + Objects.hashCode(this.rfc);
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
		final DatosGenerales other = (DatosGenerales) obj;
		if (this.id != other.id) {
			return false;
		}
		if (!Objects.equals(this.rfc, other.rfc)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return apaterno + " " + amaterno + " " + nombre + " Curp:" + usuario.getCurp();
	}

}
