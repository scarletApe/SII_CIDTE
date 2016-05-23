//<editor-fold defaultstate="collapsed" desc="licence">
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//</editor-fold>
package org.cidte.sii.hibernate;

import java.util.ArrayList;
import org.cidte.sii.entidades.DatosPersonales;
import org.cidte.sii.entidades.DocumentoPDF;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author juanmartinez
 */
public class ConectorDocumentoPDF {

    public Object saveNew(DocumentoPDF r) {
        return HibernateConector.saveObject(r);
    }

    public void update(DocumentoPDF r) {
        HibernateConector.updateObject(r);
    }

    public void delete(DocumentoPDF r) {
        HibernateConector.deleteObject(r);
    }

    public ArrayList<DocumentoPDF> getAll() {
        String hql = "From DocumentoPDF";
        return (ArrayList<DocumentoPDF>) HibernateConector.executeHQLQuery(hql);
    }

    public DocumentoPDF get(int id) {
        ArrayList<DocumentoPDF> result;
        DocumentoPDF u = null;

        String hql = "From DocumentoPDF where id_documentopdf like '" + id + "'";
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
