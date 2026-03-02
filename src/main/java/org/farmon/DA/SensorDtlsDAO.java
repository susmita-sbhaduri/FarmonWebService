/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.farmon.DA;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.UserTransaction;
import org.farmon.JPA.SensordetailJpaController;
import org.farmon.entities.Employee;
import org.farmon.entities.Sensordetail;

/**
 *
 * @author sb
 */
public class SensorDtlsDAO extends SensordetailJpaController{
    public SensorDtlsDAO(UserTransaction utx, EntityManagerFactory emf) {
        super(utx, emf);
    }
    public Sensordetail getParamName(int id) {
        EntityManager em = getEntityManager();
        TypedQuery<Sensordetail> query = em.createNamedQuery("Sensordetail.paramForId", Sensordetail.class);
        query.setParameter("id", id);       
        return query.getSingleResult();
    }
}
