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
import org.farmon.JPA.GrowthstageJpaController;
import org.farmon.entities.Growthstage;
import org.farmon.entities.Sales;

/**
 *
 * @author sb
 */
public class GrowthstageDAO extends GrowthstageJpaController{
    public GrowthstageDAO(UserTransaction utx, EntityManagerFactory emf) {
        super(utx, emf);
    }
    public List<Growthstage> getGrowthStagesForCropProd(int cropid, int prodid, int stageid) {
        EntityManager em = getEntityManager();
        TypedQuery<Growthstage> query = em.createNamedQuery("Growthstage.growthStagesCropProd", Growthstage.class);
        query.setParameter("cropid", cropid);
        query.setParameter("productid", prodid);
        query.setParameter("currentstageid", stageid);
        return query.getResultList();
    }
}
