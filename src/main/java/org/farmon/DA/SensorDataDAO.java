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
import org.farmon.JPA.SensordataJpaController;
import org.farmon.entities.Sensordata;

/**
 *
 * @author sb
 */
public class SensorDataDAO extends SensordataJpaController{
    public SensorDataDAO(UserTransaction utx, EntityManagerFactory emf) {
        super(utx, emf);
    }
    public int getMaxSenId() {
        EntityManager em = getEntityManager();
        TypedQuery<Integer> query = em.createNamedQuery("Sensordata.getMaxId", Integer.class);        
        return query.getSingleResult();
    }
    
    public List<Sensordata> getAllSensorData() {
        EntityManager em = getEntityManager();
        TypedQuery<Sensordata> query = em.createNamedQuery("Sensordata.listAll", Sensordata.class);            
        List<Sensordata> listofdata = query.getResultList();
        return listofdata;
    }
}
