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
import org.farmon.JPA.EmpleaveJpaController;
import org.farmon.entities.Empleave;

/**
 *
 * @author sb
 */
public class EmpLeaveDAO extends EmpleaveJpaController{
    public EmpLeaveDAO(UserTransaction utx, EntityManagerFactory emf) {
        super(utx, emf);
    }
    public int getMaxEmpLeaveId() {
        EntityManager em = getEntityManager();
        TypedQuery<Integer> query = em.createNamedQuery("Empleave.getMaxEmpLeaveId", Integer.class);        
        return query.getSingleResult();
    }
    
    public Long getLeaveCount(int id) {
        EntityManager em = getEntityManager();
        TypedQuery<Long> query = em.createNamedQuery("Empleave.getLeaveCount", Long.class);   
        query.setParameter("employeeid", id);  
        return query.getSingleResult();
    }
    
    public List<Empleave> getLeaveList() {
        EntityManager em = getEntityManager();
        TypedQuery<Empleave> query = em.createNamedQuery("Empleave.leaveList", Empleave.class);
        return query.getResultList();
    }
}
