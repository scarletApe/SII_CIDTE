/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidte.sii.hibernate;

import java.util.ArrayList;

import org.cidte.sii.entidades.DatosMedicos;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author juanmartinez
 */
public class ConectorDatosMedicos {

	public Object saveNew(DatosMedicos r) {
		return HibernateConector.saveObject(r);
	}

	public void update(DatosMedicos r) {
		HibernateConector.updateObject(r);
	}

	public void delete(DatosMedicos r) {
		HibernateConector.deleteObject(r);
	}

	public ArrayList<DatosMedicos> getAll() {
		String hql = "From DatosMedicos";
		return (ArrayList<DatosMedicos>) HibernateConector.executeHQLQuery(hql);
	}

	public DatosMedicos get(String curp) {
		ArrayList<DatosMedicos> result;
		DatosMedicos u = null;

		String hql = "From DatosMedicos where curp like '" + curp + "'";
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
