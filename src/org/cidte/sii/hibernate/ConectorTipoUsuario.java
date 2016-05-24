/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidte.sii.hibernate;

import java.util.ArrayList;

import org.cidte.sii.entidades.TipoUsuario;

/**
 *
 * @author manuelmartinez
 */
public class ConectorTipoUsuario {

	public Object saveNew(TipoUsuario r) {
		return HibernateConector.saveObject(r);
	}

	public void update(TipoUsuario r) {
		HibernateConector.updateObject(r);
	}

	public void delete(TipoUsuario r) {
		HibernateConector.deleteObject(r);
	}

	public ArrayList<TipoUsuario> getAll() {
		String hql = "From TipoUsuario";
		return (ArrayList<TipoUsuario>) HibernateConector.executeHQLQuery(hql);
	}
}
