/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.farmon.DA;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.UserTransaction;
import java.util.Date;
import java.util.List;
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
    
    public Sales getLastSalesForCrop(int cropid, int prodid, int harvestid) {
        EntityManager em = getEntityManager();
        TypedQuery<Sales> query = em.createNamedQuery("Sales.lastSalesForCrop", Sales.class);
        query.setParameter("cropid", cropid);
        query.setParameter("prodid", prodid);
        query.setParameter("harvestid", harvestid);
        query.setMaxResults(1);            
        return query.getSingleResult();
    }
    
    public List<Sales> getNonzeroSalesForCrop(int cropid) {
        EntityManager em = getEntityManager();
        TypedQuery<Sales> query = em.createNamedQuery("Sales.listNonzeroSalesForCrop", Sales.class);
        query.setParameter("cropid", cropid);
        List<Sales> listofsales = query.getResultList();
        return listofsales;
    }
    
   
    
    public Object[] getSalesSumCropProdHar(int cropid, int prodid, int harvestid, 
            Date startdate, Date enddate) {
        EntityManager em = getEntityManager();
        Query query = em.createNamedQuery("Sales.salesSumCropProdHar");
        query.setParameter("cropid", cropid);
        query.setParameter("prodid", prodid);
        query.setParameter("harvestid", harvestid);
        query.setParameter("startdate", startdate);
        query.setParameter("enddate", enddate);
        // Cast the single result to an Object array
        return (Object[]) query.getSingleResult();
    }
}
