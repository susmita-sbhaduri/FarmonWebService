/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.farmon.DA;

import jakarta.persistence.EntityManagerFactory;
import jakarta.transaction.UserTransaction;
import org.farmon.JPA.SalesJpaController;

/**
 *
 * @author sb
 */
public class SalesDAO extends SalesJpaController{
    public SalesDAO(UserTransaction utx, EntityManagerFactory emf) {
        super(utx, emf);
    }
}
