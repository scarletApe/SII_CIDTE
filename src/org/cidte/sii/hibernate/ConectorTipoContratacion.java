
//<editor-fold defaultstate="collapsed" desc="licence">
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//</editor-fold>

package org.cidte.sii.hibernate;

import java.util.ArrayList;

import org.cidte.sii.entidades.TipoContratacion;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author juanmartinez
 */
public class ConectorTipoContratacion {
	public Object saveNew(TipoContratacion r) {
		return HibernateConector.saveObject(r);
	}

	public void update(TipoContratacion r) {
		HibernateConector.updateObject(r);
	}

	public void delete(TipoContratacion r) {
		HibernateConector.deleteObject(r);
	}

	public ArrayList<TipoContratacion> getAll() {
		String hql = "From TipoContratacion";
		return (ArrayList<TipoContratacion>) HibernateConector.executeHQLQuery(hql);
	}

	public TipoContratacion get(String curp) {
		ArrayList<TipoContratacion> result;
		TipoContratacion u = null;

		String hql = "From TipoContratacion where curp like '" + curp + "'";
		Session session = HibernateConector.factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery(hql);
			result = (ArrayList) query.list();

			if (result != null && !result.isEmpty()) {
				u = result.get(0);
			}

			tx.commit();
		} catch (HibernateException he) {
			if (tx != null) {
				tx.rollback();
			}
			System.err.println(he.getMessage());
		} finally {
			session.close();
		}
		return u;
	}
}
