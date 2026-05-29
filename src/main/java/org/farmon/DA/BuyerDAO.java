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
import org.farmon.JPA.BuyerJpaController;
import org.farmon.entities.Buyer;
import org.farmon.entities.Shop;

/**
 *
 * @author sb
 */
public class BuyerDAO extends BuyerJpaController{
    public BuyerDAO(UserTransaction utx, EntityManagerFactory emf) {
        super(utx, emf);
    }
    public Buyer getBuyerName(int id) {
        EntityManager em = getEntityManager();
        TypedQuery<Buyer> query = em.createNamedQuery("Buyer.buyerNameForId", Buyer.class); 
        query.setParameter("buyerid", id);
        Buyer buyerrec = query.getSingleResult();
        return buyerrec;
    }
    public List<Buyer> getAllBuyers() {
        EntityManager em = getEntityManager();
        TypedQuery<Buyer> query = em.createNamedQuery("Buyer.allBuyers", Buyer.class);            
        List<Buyer> listofbuyer = query.getResultList();
        return listofbuyer;
    }
    
    public int getMaxBuyerId() {
        EntityManager em = getEntityManager();
        TypedQuery<Integer> query = em.createNamedQuery("Buyer.getMaxBuyerId", Integer.class);        
        return query.getSingleResult();
    }
}
