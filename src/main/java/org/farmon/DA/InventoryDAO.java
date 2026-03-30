/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.farmon.DA;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.UserTransaction;
import java.util.List;
import org.farmon.JPA.InventoryJpaController;
import org.farmon.entities.Crop;
import org.farmon.entities.Inventory;

/**
 *
 * @author sb
 */
public class InventoryDAO extends InventoryJpaController{
    
    public InventoryDAO(UserTransaction utx, EntityManagerFactory emf) {
        super(utx, emf);
    }
    public List<Inventory> getNonzeroInvForCrop(int cropid) {
        EntityManager em = getEntityManager();
        TypedQuery<Inventory> query = em.createNamedQuery("Inventory.listNonzeroInvForCrop", Inventory.class);            
        query.setParameter("cropid", cropid);
        List<Inventory> listofinv = query.getResultList();
        return listofinv;
    }
    public int getMaxInventoryId() {
        EntityManager em = getEntityManager();
        TypedQuery<Integer> query = em.createNamedQuery("Inventory.getMaxInventoryId", Integer.class);        
        return query.getSingleResult();
    }
}
