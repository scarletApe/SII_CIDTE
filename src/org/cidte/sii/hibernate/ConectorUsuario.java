/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidte.sii.hibernate;

import java.util.ArrayList;
import org.cidte.sii.entidades.Usuario;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author manuelmartinez
 */
public class ConectorUsuario {

    public Object saveNew(Usuario r) {
        return HibernateConector.saveObject(r);
    }

    public void update(Usuario r) {
        HibernateConector.updateObject(r);
    }

    public void delete(Usuario r) {
        HibernateConector.deleteObject(r);
    }

    public ArrayList<Usuario> getAll() {
        ArrayList<Usuario> result = null;
        String hql = "From Usuario ";
        Session session = HibernateConector.factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery(hql);
            result = (ArrayList) query.list();

//            if (result != null && !result.isEmpty()) {
//                for (Usuario b : result) {
//                    Hibernate.initialize(b.getTipoUsario());
//                }
//            }
            
            tx.commit();
        } catch (HibernateException he) {
            if (tx != null) {
                tx.rollback();
            }
            System.err.println(he.getMessage());
        } finally {
            session.close();
        }
        return result;
    }

    public Usuario get(String username, String contrasena) {
        ArrayList<Usuario> result = null;
        Usuario u = null;
        /*
        SELECT * FROM DONORS
        WHERE RELIGION_CODE <> (SELECT RELIGION_CODE FROM RELIGIONS
                            WHERE UPPER(RELIGION_NAME) LIKE 'PASTAFARISMO');
         */
        String hql = "From Usuario where username like '" + username
                + "' and password like '" + contrasena+"'";
        Session session = HibernateConector.factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery(hql);
            result = (ArrayList) query.list();

            if (result != null && !result.isEmpty()) {
//                for (Usuario b : result) {
//                    Hibernate.initialize(b.getTipoUsario());
//                }
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
