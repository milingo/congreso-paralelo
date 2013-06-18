package org.congreso.admin;

import org.congreso.dao.ChallengeDao;
import org.congreso.dao.VotacionDao;
import org.congreso.model.Status;
import org.congreso.model.Votacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 
 * @author miguel
 *
 */
@Controller
@RequestMapping(value = "/admin/votacion/{votacionId}/challenge")
public class ChallengeAdminController {

    @Autowired
    private VotacionDao votacionDao;
    @Autowired
    private ChallengeDao challengeDao;
    
    
    @RequestMapping(value = "/open", method = RequestMethod.POST)
    public String open(@PathVariable Long votacionId) {
        // TODO: Move this to Manager
        Votacion votacion = votacionDao.load(votacionId);
        votacion.getChallenge().setStatus(Status.OPENED);
        challengeDao.update(votacion.getChallenge());

        return "redirect:/admin/votacion/" + votacion.getId();
    }
    
    @RequestMapping(value = "/close", method = RequestMethod.POST)
    public String close(@PathVariable Long votacionId) {
        // TODO: Move this to Manager
        Votacion votacion = votacionDao.load(votacionId);
        votacion.getChallenge().setStatus(Status.CLOSED);
        challengeDao.update(votacion.getChallenge());

        return "redirect:/admin/votacion/" + votacion.getId();
    }
    
    @RequestMapping(value = "/inactive", method = RequestMethod.POST)
    public String init(@PathVariable Long votacionId) {
        // TODO: Move this to Manager
        Votacion votacion = votacionDao.load(votacionId);
        votacion.getChallenge().setStatus(Status.INACTIVE);
        challengeDao.update(votacion.getChallenge());

        return "redirect:/admin/votacion/" + votacion.getId();
    }
    
    @RequestMapping(value = "/reopen", method = RequestMethod.POST)
    public String reopen(@PathVariable Long votacionId) {
        // TODO: Move this to Manager
        Votacion votacion = votacionDao.load(votacionId);
        votacion.getChallenge().setStatus(Status.OPENED);
        challengeDao.update(votacion.getChallenge());

        return "redirect:/admin/votacion/" + votacion.getId();
    }

}
