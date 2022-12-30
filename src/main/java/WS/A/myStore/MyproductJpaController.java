/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package WS.A.myStore;

import WS.A.myStore.exceptions.NonexistentEntityException;
import WS.A.myStore.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author eunannana
 */
public class MyproductJpaController implements Serializable {

    public MyproductJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("WS.A_myStore_jar_0.0.1-SNAPSHOTPU");

    public MyproductJpaController()
    {
        
    }
    
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Myproduct myproduct) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(myproduct);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMyproduct(myproduct.getProductID()) != null) {
                throw new PreexistingEntityException("Myproduct " + myproduct + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Myproduct myproduct) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            myproduct = em.merge(myproduct);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = myproduct.getProductID();
                if (findMyproduct(id) == null) {
                    throw new NonexistentEntityException("The myproduct with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Myproduct myproduct;
            try {
                myproduct = em.getReference(Myproduct.class, id);
                myproduct.getProductID();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The myproduct with id " + id + " no longer exists.", enfe);
            }
            em.remove(myproduct);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Myproduct> findMyproductEntities() {
        return findMyproductEntities(true, -1, -1);
    }

    public List<Myproduct> findMyproductEntities(int maxResults, int firstResult) {
        return findMyproductEntities(false, maxResults, firstResult);
    }

    private List<Myproduct> findMyproductEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Myproduct.class));
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

    public Myproduct findMyproduct(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Myproduct.class, id);
        } finally {
            em.close();
        }
    }

    public int getMyproductCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Myproduct> rt = cq.from(Myproduct.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
