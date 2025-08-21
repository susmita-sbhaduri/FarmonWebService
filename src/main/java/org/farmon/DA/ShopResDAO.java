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
import java.util.List;
import org.farmon.JPA.ShopresourceJpaController;
import org.farmon.entities.Shopresource;


public class ShopResDAO extends ShopresourceJpaController{
    private UserTransaction myUtx;
    public ShopResDAO(UserTransaction utx, EntityManagerFactory emf) {
        super(utx, emf);
        this.myUtx = utx; // store reference for your own use
    }
    public int getMaxId() {
        EntityManager em = getEntityManager();
        TypedQuery<Integer> query = em.createNamedQuery("Shopresource.getMaxId", Integer.class);        
        return query.getSingleResult();
    }
    public List<Shopresource> getShopResList() {
        EntityManager em = getEntityManager();
        TypedQuery<Shopresource> query = em.createNamedQuery("Shopresource.shopResList", Shopresource.class);            
        List<Shopresource> shopreslist = query.getResultList();
        return shopreslist;
    }
    
    public List<Integer> getShopsForRes(int id) {
        EntityManager em = getEntityManager();
        TypedQuery<Integer> query = em.createNamedQuery("Shopresource.shopsPerRes", Integer.class);
        query.setParameter("resourceid", id);
        List<Integer> shopids = query.getResultList();
        return shopids;
    }
    
    
    public List<Shopresource> getShopDtlssForRes(int id) {
        EntityManager em = getEntityManager();
        TypedQuery<Shopresource> query = em.createNamedQuery("Shopresource.shopDtlsPerRes", Shopresource.class);
        query.setParameter("resourceid", id);
        List<Shopresource> shopids = query.getResultList();
        return shopids;
    }
    
    public List<Shopresource> getShopResLstPerRes(int id) {
        EntityManager em = getEntityManager();
        TypedQuery<Shopresource> query = em.createNamedQuery("Shopresource.shopResListPerRes", Shopresource.class);
        query.setParameter("resourceid", id);
        List<Shopresource> shopids = query.getResultList();
        return shopids;
    }
    
    public List<Shopresource> getShopResPerAppid(int appid, int resid) {
        EntityManager em = getEntityManager();
        TypedQuery<Shopresource> query = em.createNamedQuery("Shopresource.shopResPerAppid", Shopresource.class);
        query.setParameter("resappid", appid);
        query.setParameter("resourceid", resid);
        List<Shopresource> shopids = query.getResultList();
        return shopids;
    }
    
    public List<Shopresource> getShopResList(int resid, int shopid) {
        EntityManager em = getEntityManager();
        TypedQuery<Shopresource> query = em.createNamedQuery("Shopresource.shopDtlsPerPk", Shopresource.class);
        query.setParameter("resourceid", resid);
        query.setParameter("shopid", shopid);
        List<Shopresource> recordlist = query.getResultList();
        return recordlist;
    }
    
    public Shopresource getShopResForId(int id) {
        EntityManager em = getEntityManager();
        TypedQuery<Shopresource> query = em.createNamedQuery("Shopresource.shopResPerId", Shopresource.class);
        query.setParameter("id", id);
//        List<Shopresource> shopids = query.getResultList();
        return query.getSingleResult();
    }
    
    public int delForResid(int resid) throws Exception {
        EntityManager em = null;
        int rowsDeleted = 0;
        try {
            myUtx.begin();
            em = getEntityManager();
//            Query query = em.createQuery("DELETE FROM Shopresource s WHERE s.shopresourcePK.resourceid = :resourceid");
            Query query = em.createNamedQuery("Shopresource.delForResid");
            query.setParameter("resourceid", resid);
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


