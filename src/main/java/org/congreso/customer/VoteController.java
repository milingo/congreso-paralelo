package org.congreso.customer;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.congreso.account.Account;
import org.congreso.account.AccountRepository;
import org.congreso.dao.VotacionDao;
import org.congreso.dao.VotoDao;
import org.congreso.model.Votacion;
import org.congreso.model.Voto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 
 * @author miguel
 * 
 */
@Controller
@RequestMapping(value = "/votacion/{votacionId}/vote")
public class VoteController {

    @Autowired
    private VotoDao votoDao;
    @Autowired
    private AccountRepository accountDao;
    @Autowired
    private VotacionDao votacionDao;

    // LESSON: This includes the voteForm as modelAttribute, that can
    // be later referenced on the jsp
    @ModelAttribute("voteForm")
    public VoteForm getVoteForm() {
        return new VoteForm();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String vote(Model model, Principal principal,
            @PathVariable Long votacionId,
            @Valid @ModelAttribute VoteForm voteForm, Errors errors) {
        
        if (principal != null) {
            Account account = accountDao.findByUsername(principal.getName());
            Votacion votacion = votacionDao.load(votacionId);
            Voto previousVoto = votoDao.load(votacion, account);
            if (previousVoto == null) {
                // TODO: Move this to Manager
                Voto voto = new Voto();
                voto.setVotacion(votacion);
                voto.setAccount(account);
                voto.setVoto(voteForm.getOption());
                votoDao.save(voto);
            } 
        }
        return "redirect:/votacion/{votacionId}";
    }

}
