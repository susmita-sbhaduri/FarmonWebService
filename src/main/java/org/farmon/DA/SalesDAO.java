/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.farmon.DA;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.UserTransaction;
import org.farmon.JPA.SalesJpaController;
import org.farmon.entities.Sales;

/**
 *
 * @author sb
 */
public class SalesDAO extends SalesJpaController{
    public SalesDAO(UserTransaction utx, EntityManagerFactory emf) {
        super(utx, emf);
    }
    public int getMaxSalesId() {
        EntityManager em = getEntityManager();
        TypedQuery<Integer> query = em.createNamedQuery("Sales.getMaxSalesId", Integer.class);        
        return query.getSingleResult();
    }
    
    public Sales getLastSalesHarForCrop(int id) {
        EntityManager em = getEntityManager();
        TypedQuery<Sales> query = em.createNamedQuery("Sales.lastSalesHarForCrop", Sales.class);
        query.setParameter("cropid", id);
        query.setMaxResults(1);            
        return query.getSingleResult();
    }
    
    public Sales getLastInvForCrop(int cropid, int prodid, int harvestid) {
        EntityManager em = getEntityManager();
        TypedQuery<Sales> query = em.createNamedQuery("Sales.lastSalesForCrop", Sales.class);
        query.setParameter("cropid", cropid);
        query.setParameter("prodid", prodid);
        query.setParameter("harvestid", harvestid);
        query.setMaxResults(1);            
        return query.getSingleResult();
    }
}
