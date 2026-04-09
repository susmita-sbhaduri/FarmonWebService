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
import org.farmon.JPA.CropproductJpaController;
import org.farmon.entities.Cropproduct;

/**
 *
 * @author sb
 */
public class CropprodDAO extends CropproductJpaController{
    private UserTransaction myUtx;
    public CropprodDAO(UserTransaction utx, EntityManagerFactory emf) {
        super(utx, emf);
        this.myUtx = utx; // store reference for your own use
    }
    public int getMaxCropProdId() {
        EntityManager em = getEntityManager();
        TypedQuery<Integer> query = em.createNamedQuery("Cropproduct.getMaxCropprodId", Integer.class);        
        return query.getSingleResult();
    }

    public List<Cropproduct> getNonzeroProdForCrop(int cropid) {
        EntityManager em = getEntityManager();
        TypedQuery<Cropproduct> query = em.createNamedQuery("Cropproduct.listNonzeroProdForCrop", Cropproduct.class);
        query.setParameter("cropid", cropid);
        List<Cropproduct> listofprod = query.getResultList();
        return listofprod;
    }
    
    public List<Cropproduct> getAllProdForCrop(int cropid) {
        EntityManager em = getEntityManager();
        TypedQuery<Cropproduct> query = em.createNamedQuery("Cropproduct.listAllProdForCrop", Cropproduct.class);
        query.setParameter("cropid", cropid);
        List<Cropproduct> listofprod = query.getResultList();
        return listofprod;
    }
    
    public Cropproduct getCropprodForcropProd(int cropid, int prodid) {
        EntityManager em = getEntityManager();
        TypedQuery<Cropproduct> query = em.createNamedQuery("Cropproduct.cropprodForcropProd", Cropproduct.class); 
        query.setParameter("cropid", cropid);
        query.setParameter("productid", prodid);
        Cropproduct cropsperpk = query.getSingleResult();
        return cropsperpk;
    }

    public int deleteByCropId(int targetCropId) throws Exception {
        EntityManager em = null;
        int rowsDeleted = 0;
        try {
            myUtx.begin();
            em = getEntityManager();
            Query query = em.createNamedQuery("Cropproduct.deleteByCropId");
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
