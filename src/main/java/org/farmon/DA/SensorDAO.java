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
import org.farmon.JPA.SensorJpaController;
import org.farmon.entities.Sensor;

/**
 *
 * @author sb
 */
public class SensorDAO extends SensorJpaController{
    public SensorDAO(UserTransaction utx, EntityManagerFactory emf) {
        super(utx, emf);
    }
    public int getMaxSenId() {
        EntityManager em = getEntityManager();
        TypedQuery<Integer> query = em.createNamedQuery("Sensor.getMaxSenId", Integer.class);        
        return query.getSingleResult();
    }
    
    public List<Sensor> getAllSensorData() {
        EntityManager em = getEntityManager();
        TypedQuery<Sensor> query = em.createNamedQuery("Sensor.listAll", Sensor.class);            
        List<Sensor> listofdata = query.getResultList();
        return listofdata;
    }
}
