/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidte.sii.hibernate;

import java.util.ArrayList;

import org.cidte.sii.entidades.Organizacion;

/**
 *
 * @author manuelmartinez
 */
public class ConectorOrganizacion {

	public Object saveNew(Organizacion r) {
		return HibernateConector.saveObject(r);
	}

	public void update(Organizacion r) {
		HibernateConector.updateObject(r);
	}

	public void delete(Organizacion r) {
		HibernateConector.deleteObject(r);
	}

	public ArrayList<Organizacion> getAll() {
		String hql = "From Organizacion";
		return (ArrayList<Organizacion>) HibernateConector.executeHQLQuery(hql);
	}

}
