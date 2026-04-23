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
import java.math.BigDecimal;
import java.util.List;
import org.farmon.JPA.InventoryJpaController;
import org.farmon.entities.Inventory;

/**
 *
 * @author sb
 */
public class InventoryDAO extends InventoryJpaController{
    private UserTransaction myUtx;
    public InventoryDAO(UserTransaction utx, EntityManagerFactory emf) {
        super(utx, emf);
        this.myUtx = utx; // store reference for your own use
    }
//    public List<Inventory> getNonzeroInvForCrop(int cropid) {
//        EntityManager em = getEntityManager();
//        TypedQuery<Inventory> query = em.createNamedQuery("Inventory.listNonzeroInvForCrop", Inventory.class);            
//        query.setParameter("cropid", cropid);
//        List<Inventory> listofinv = query.getResultList();
//        return listofinv;
//    }
    public Inventory getLastInvHarForCrop(int id) {
        EntityManager em = getEntityManager();
        TypedQuery<Inventory> query = em.createNamedQuery("Inventory.lastInvHarForCrop", Inventory.class);
        query.setParameter("cropid", id);
        query.setMaxResults(1);            
        return query.getSingleResult();
    }
    
    public Inventory getLastInvForCrop(int cropid, int prodid, int harvestid) {
        EntityManager em = getEntityManager();
        TypedQuery<Inventory> query = em.createNamedQuery("Inventory.lastInvForCrop", Inventory.class);
        query.setParameter("cropid", cropid);
        query.setParameter("prodid", prodid);
        query.setParameter("harvestid", harvestid);
        query.setMaxResults(1);            
        return query.getSingleResult();
    }
    
    public List<Integer> getInvHarForCrop(int id) {
        EntityManager em = getEntityManager();
        TypedQuery<Integer> query = em.createNamedQuery("Inventory.invHarForCrop", Integer.class);
        query.setParameter("cropid", id);
        List<Integer> harvestids = query.getResultList();
        return harvestids;
    }
    
    public List<Integer> getDintictHarInv() {
        EntityManager em = getEntityManager();
        TypedQuery<Integer> query = em.createNamedQuery("Inventory.distinctHarInv", Integer.class);
        
        List<Integer> harvestids = query.getResultList();
        return harvestids;
    }
    
    public BigDecimal getTotalInvStock(int cropid, int prodid, int harvestid) {
        EntityManager em = getEntityManager();
        TypedQuery<BigDecimal> query = em.createNamedQuery("Inventory.totalCropProdHar", BigDecimal.class);
        query.setParameter("cropid", cropid);
        query.setParameter("prodid", prodid);
        query.setParameter("harvestid", harvestid);
        BigDecimal total = query.getSingleResult();
        return (total != null) ? total : BigDecimal.ZERO;
    }
    public int getMaxInventoryId() {
        EntityManager em = getEntityManager();
        TypedQuery<Integer> query = em.createNamedQuery("Inventory.getMaxInventoryId", Integer.class);        
        return query.getSingleResult();
    }
    
    public int deleteInvByCropId(int targetCropId) throws Exception {
        EntityManager em = null;
        int rowsDeleted = 0;
        try {
            myUtx.begin();
            em = getEntityManager();
            Query query = em.createNamedQuery("Inventory.deleteByCropId");
            query.setParameter("cropid", targetCropId);
            rowsDeleted = query.executeUpdate();
            myUtx.commit();
        } catch (Exception ex) {
            if (myUtx != null) myUtx.rollback();
            throw ex;
        } finally {
            if (em != null) em.close();
        }
        return rowsDeleted;
               
    }
}
