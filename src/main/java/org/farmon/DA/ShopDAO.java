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
import org.farmon.JPA.ShopJpaController;
import org.farmon.entities.Shop;

/**
 *
 * @author sb
 */
public class ShopDAO extends ShopJpaController{
    public ShopDAO(UserTransaction utx, EntityManagerFactory emf) {
        super(utx, emf);
    }
    
    public Shop getShopName(int id) {
        EntityManager em = getEntityManager();
        TypedQuery<Shop> query = em.createNamedQuery("Shop.shopNameForId", Shop.class); 
        query.setParameter("shopid", id);
        Shop shoprec = query.getSingleResult();
        return shoprec;
    }
    public List<Shop> getAllShops() {
        EntityManager em = getEntityManager();
        TypedQuery<Shop> query = em.createNamedQuery("Shop.allShops", Shop.class);            
        List<Shop> listofshop = query.getResultList();
        return listofshop;
    }
}
