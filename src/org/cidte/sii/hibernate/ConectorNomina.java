
//<editor-fold defaultstate="collapsed" desc="licence">
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//</editor-fold>

package org.cidte.sii.hibernate;

import java.util.ArrayList;

import org.cidte.sii.entidades.Nomina;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author juanmartinez
 */
public class ConectorNomina {
	public Object saveNew(Nomina r) {
		return HibernateConector.saveObject(r);
	}

	public void update(Nomina r) {
		HibernateConector.updateObject(r);
	}

	public void delete(Nomina r) {
		HibernateConector.deleteObject(r);
	}

	public ArrayList<Nomina> getAll() {
		String hql = "From Nomina";
		return (ArrayList<Nomina>) HibernateConector.executeHQLQuery(hql);
	}

	public Nomina get(int id_tipo_contratacion) {
		ArrayList<Nomina> result;
		Nomina u = null;

		String hql = "From Nomina where id_contratacion like '" + id_tipo_contratacion + "'";
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
