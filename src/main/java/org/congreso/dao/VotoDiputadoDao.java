package org.congreso.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import org.congreso.model.Votacion;
import org.congreso.model.VotoDiputado;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class VotoDiputadoDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional
	public VotoDiputado save(VotoDiputado voto) {
		entityManager.persist(voto);
		return voto;
	}

    public VotoDiputado load(Long votoId) {
        return entityManager.find(VotoDiputado.class, votoId);
    }
    
    public void remove(VotoDiputado voto) {
        if (entityManager.contains(voto)) {
            entityManager.remove(voto);
        } else {
            entityManager.remove(entityManager.merge(voto));
        }
        entityManager.flush();
    }
    
    public List<VotoDiputado> findByVotacion(Votacion votacion) {
        try {
            return entityManager.createNamedQuery(VotoDiputado.FIND_BY_VOTACION, VotoDiputado.class)
                    .setParameter("votacionId", votacion.getId())
                    .getResultList();
        } catch (PersistenceException e) {
            return null;
        }
    }
    
    public List<VotoDiputado> findAll() {
        return entityManager.createNamedQuery(VotoDiputado.FIND_ALL, VotoDiputado.class).getResultList();
    }
    
}
