package org.congreso.dao;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import org.congreso.model.Diputado;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public class DiputadoDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional
	public Diputado save(Diputado diputado) throws DiputadoAlreadyExistsException {
	    try {
		    entityManager.persist(diputado);
	    } catch (EntityExistsException ex) {
	        throw new DiputadoAlreadyExistsException();
	    }
		return diputado;
	}

    public Diputado load(Long diputadoId) {
        return entityManager.find(Diputado.class, diputadoId);
    }

    public Diputado findByName(String name) {
        try {
            return entityManager.createNamedQuery(Diputado.FIND_BY_NAME, Diputado.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (PersistenceException e) {
            return null;
        }
    }
    
    public List<Diputado> findAll() {
        return entityManager.createNamedQuery(Diputado.FIND_ALL, Diputado.class).getResultList();
    }
    
}
