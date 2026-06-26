/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.farmon.JPA;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.io.Serializable;
import jakarta.persistence.Query;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.UserTransaction;
import java.util.List;
import org.farmon.JPA.exceptions.NonexistentEntityException;
import org.farmon.JPA.exceptions.PreexistingEntityException;
import org.farmon.JPA.exceptions.RollbackFailureException;
import org.farmon.entities.Growthstage;

/**
 *
 * @author sb
 */
public class GrowthstageJpaController implements Serializable {

    public GrowthstageJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Growthstage growthstage) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            em.persist(growthstage);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findGrowthstage(growthstage.getId()) != null) {
                throw new PreexistingEntityException("Growthstage " + growthstage + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Growthstage growthstage) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            growthstage = em.merge(growthstage);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = growthstage.getId();
                if (findGrowthstage(id) == null) {
                    throw new NonexistentEntityException("The growthstage with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Growthstage growthstage;
            try {
                growthstage = em.getReference(Growthstage.class, id);
                growthstage.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The growthstage with id " + id + " no longer exists.", enfe);
            }
            em.remove(growthstage);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Growthstage> findGrowthstageEntities() {
        return findGrowthstageEntities(true, -1, -1);
    }

    public List<Growthstage> findGrowthstageEntities(int maxResults, int firstResult) {
        return findGrowthstageEntities(false, maxResults, firstResult);
    }

    private List<Growthstage> findGrowthstageEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Growthstage.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Growthstage findGrowthstage(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Growthstage.class, id);
        } finally {
            em.close();
        }
    }

    public int getGrowthstageCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Growthstage> rt = cq.from(Growthstage.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
