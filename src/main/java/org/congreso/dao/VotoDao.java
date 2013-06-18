package org.congreso.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import org.congreso.account.Account;
import org.congreso.model.Option;
import org.congreso.model.Votacion;
import org.congreso.model.Voto;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class VotoDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
    @Transactional
    public Voto save(Voto voto) throws EntityDuplicatedException {
        try {
            entityManager.persist(voto);
        } catch (PersistenceException e) {
            throw new EntityDuplicatedException(e);
        }
        return voto;
    }

    public Voto load(Long votoId) {
        return entityManager.find(Voto.class, votoId);
    }
    
    public void remove(Voto voto) {
        if (entityManager.contains(voto)) {
            entityManager.remove(voto);
        } else {
            entityManager.remove(entityManager.merge(voto));
        }
        entityManager.flush();
    }
    
    public List<Voto> findByVotacion(Votacion votacion) {
        try {
            return entityManager.createNamedQuery(Voto.FIND_BY_VOTACION, Voto.class)
                    .setParameter("votacionId", votacion.getId())
                    .getResultList();
        } catch (PersistenceException e) {
            return null;
        }
    }
    
    public Voto load(Votacion votacion, Account account) {
        try {
            return entityManager.createNamedQuery(Voto.FIND_BY_VOTACION_AND_ACCOUNT, Voto.class)
                    .setParameter("votacionId", votacion.getId())
                    .setParameter("accountId", account.getId())
                    .getSingleResult();
        } catch (PersistenceException e) {
            return null;
        }
    }
    
    public Long optionCountForVotacion(Votacion votacion, Option option) {
        try {
            return (Long) entityManager.createNamedQuery(Voto.COUNT_BY_OPTION )
                    .setParameter("votacionId", votacion.getId())
                    .setParameter("opcion", option)
                    .getSingleResult();
        } catch (PersistenceException e) {
            return (long) 0;
        }
    }
    
    public List<Voto> findAll() {
        return entityManager.createNamedQuery(Voto.FIND_ALL, Voto.class).getResultList();
    }
    
}
