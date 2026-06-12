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
import org.farmon.JPA.ProductstageJpaController;
import org.farmon.entities.Productstage;

/**
 *
 * @author sb
 */
public class ProdStageDAO extends ProductstageJpaController{
    public ProdStageDAO(UserTransaction utx, EntityManagerFactory emf) {
        super(utx, emf);
    }
    public List<Productstage> getStagesForCropProd(int cropid, int prodid) {
        EntityManager em = getEntityManager();
        TypedQuery<Productstage> query = em.createNamedQuery("Productstage.stageListCropProd", Productstage.class);            
        query.setParameter("cropid", cropid);
        query.setParameter("prodid", prodid);
        List<Productstage> listofcrop = query.getResultList();
        return listofcrop;
    }
}
