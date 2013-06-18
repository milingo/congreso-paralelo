package org.congreso.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import org.congreso.model.Votacion;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author miguel
 * 
 */
@Repository
@Transactional(readOnly = true)
public class VotacionDao {

    @PersistenceContext
    private EntityManager entityManager;

    public Votacion save(Votacion v) throws EntityDuplicatedException {
        try {
            entityManager.persist(v);
        } catch (PersistenceException e) {
            throw new EntityDuplicatedException(e);
        }
        return v;
    }

    public Votacion update(Votacion v) {
        entityManager.merge(v);
        entityManager.flush();
        return v;
    }

    public Votacion load(Long votacionId) {
        return entityManager.find(Votacion.class, votacionId);
    }

    public void remove(Votacion votacion) {
        if (entityManager.contains(votacion)) {
            entityManager.remove(votacion);
        } else {
            entityManager.remove(entityManager.merge(votacion));
        }
        entityManager.flush();
    }

    public List<Votacion> findAll() {
        return entityManager
                .createNamedQuery(Votacion.FIND_ALL, Votacion.class)
                .getResultList();
    }
    
    public List<Votacion> findAllVisible() {
        return entityManager
                .createNamedQuery(Votacion.FIND_ALL_VISIBLE, Votacion.class)
                .getResultList();
    }

}
