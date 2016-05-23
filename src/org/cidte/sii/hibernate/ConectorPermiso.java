
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
import org.cidte.sii.entidades.Permiso;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author juanmartinez
 */
public class ConectorPermiso {

    public Object saveNew(Permiso r) {
        return HibernateConector.saveObject(r);
    }

    public void update(Permiso r) {
        HibernateConector.updateObject(r);
    }

    public void delete(Permiso r) {
        HibernateConector.deleteObject(r);
    }

    public ArrayList<Permiso> getAll() {
        String hql = "From Permiso";
        return (ArrayList<Permiso>) HibernateConector.executeHQLQuery(hql);
    }
    
    public Permiso get(String curp) {
        ArrayList<Permiso> result;
        Permiso u = null;

        String hql = "From Permiso where curp like '" + curp + "'";
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
