package org.congreso.manager;

import java.util.List;

import org.congreso.dao.ChallengeDao;
import org.congreso.dao.DiputadoAlreadyExistsException;
import org.congreso.dao.DiputadoDao;
import org.congreso.dao.EntityDuplicatedException;
import org.congreso.dao.VotacionDao;
import org.congreso.dao.VotoDiputadoDao;
import org.congreso.model.Challenge;
import org.congreso.model.Diputado;
import org.congreso.model.Status;
import org.congreso.model.Votacion;
import org.congreso.model.VotoDiputado;
import org.congreso.xmlvotacion.Resultado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author miguel
 * 
 */
@Service
@Transactional(readOnly = true)
public class VotacionManager {
    
    @Autowired
    private VotacionDao votacionDao;
    @Autowired
    private VotoDiputadoDao votoDiputadoDao;
    @Autowired
    private DiputadoDao diputadoDao;
    @Autowired
    private ChallengeDao challengeDao;
    @Autowired
    private VotoDiputadoDao voteDao;

    /**
     * 
     * @param v
     * @return
     * @throws EntityDuplicatedException
     */
    @Transactional
    public Votacion create(Votacion votacion, Resultado resultadoFromXml) throws EntityDuplicatedException {       
        
        // Votacion
        votacion.setLegislatura("X");
        votacion.setShowVotesDiputados(true);
        votacion.setShowVotesPublic(false);
        votacion.setVisible(false);
        
        // Challenge
        Challenge c = new Challenge();
        c.setStatus(Status.INACTIVE);
        challengeDao.save(c);
        votacion.setChallenge(c);
        
        votacionDao.save(votacion);
        this.update(votacion, resultadoFromXml);
        
        return votacion;
    }

    /**
     * 
     * @param v
     * @return
     */
    public Votacion update(Votacion votacion, Resultado resultadoFromXml) {
        
        // If we have xml
        if (resultadoFromXml != null) {
            
            resultadoFromXml.fillVotacionModel(votacion);
            
            for (org.congreso.xmlvotacion.Votacion v : resultadoFromXml
                    .getVotaciones()) {
                
                // Diputado
                Diputado diputado = diputadoDao.findByName(v.getDiputado());
                if (diputado == null) {
                    diputado = new Diputado();
                    diputado.setName(v.getDiputado());
                    diputado.setSeat(v.getAsiento());
                    try {
                        diputadoDao.save(diputado);
                    } catch (DiputadoAlreadyExistsException e) {
                        e.printStackTrace();
                    }
                }

                // Votacion Diputado
                VotoDiputado voto = new VotoDiputado();
                voto.setVoto(v.getOption());
                voto.setDiputado(diputado);
                voto.setVotacion(votacion);
                votoDiputadoDao.save(voto);
            }
        }
        
        votacion = votacionDao.update(votacion);
        
        return votacion;
    }

    /**
     * 
     * @param votacionId
     * @return
     */
    public Votacion load(Long votacionId) {
        return votacionDao.load(votacionId);
    }

    /**
     * 
     * @param votacion
     */
    public void remove(Votacion votacion) {
        List<VotoDiputado> votos = voteDao.findByVotacion(votacion);
        for (VotoDiputado voto : votos) {
            voteDao.remove(voto);
        }
        
        votacionDao.remove(votacion);
    }

    /**
     * 
     * @return
     */
    public List<Votacion> findAll() {
        return votacionDao.findAll();
    }
    
    /**
     * 
     * @return
     */
    public List<Votacion> findAllVisible() {
        return votacionDao.findAllVisible();
    }

}
