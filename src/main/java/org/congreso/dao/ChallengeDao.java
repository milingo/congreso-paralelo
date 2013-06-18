package org.congreso.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import org.congreso.model.Challenge;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author miguel
 * 
 */
@Repository
@Transactional(readOnly = true)
public class ChallengeDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Challenge save(Challenge c) throws EntityDuplicatedException {
        try {
            entityManager.persist(c);
        } catch (PersistenceException e) {
            throw new EntityDuplicatedException(e);
        }
        return c;
    }

    public Challenge update(Challenge c) {
        entityManager.merge(c);
        entityManager.flush();
        return c;
    }

    public Challenge load(Long challangeId) {
        return entityManager.find(Challenge.class, challangeId);
    }

    public void remove(Challenge challange) {
        if (entityManager.contains(challange)) {
            entityManager.remove(challange);
        } else {
            entityManager.remove(entityManager.merge(challange));
        }
        entityManager.flush();
    }

    public List<Challenge> findAll() {
        return entityManager
                .createNamedQuery(Challenge.FIND_ALL, Challenge.class)
                .getResultList();
    }

}
