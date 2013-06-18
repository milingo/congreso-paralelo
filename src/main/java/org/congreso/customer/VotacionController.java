package org.congreso.customer;

import java.security.Principal;
import java.util.List;

import org.congreso.account.Account;
import org.congreso.account.AccountRepository;
import org.congreso.customer.VoteController;
import org.congreso.dao.VotacionDao;
import org.congreso.dao.VotoDao;
import org.congreso.dao.VotoDiputadoDao;
import org.congreso.model.Option;
import org.congreso.model.TotalPublico;
import org.congreso.model.Votacion;
import org.congreso.model.Voto;
import org.congreso.model.VotoDiputado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 
 * @author miguel
 *
 */
@Controller
@RequestMapping(value = "/votacion/{votacionId}")
public class VotacionController extends VoteController {
    
    @Autowired
    private VotacionDao votacionDao;
    @Autowired
    private VotoDiputadoDao votoDiputadoDao;
    @Autowired
    private VotoDao votoDao;
    @Autowired
    private AccountRepository accountDao;
	
    /**
     * 
     * @param principal
     * @param model
     * @param votacionId
     * @return
     */
	@RequestMapping(method = RequestMethod.GET)
	public String index(Principal principal, Model model, @PathVariable Long votacionId) {
	    
	    Account account = null;
	    if (principal != null) {
	        account = accountDao.findByUsername(principal.getName());
	    }
	    
	    Votacion v = votacionDao.load(votacionId);
	    
	    List<VotoDiputado> votosDiputados = votoDiputadoDao.findByVotacion(v);
	    
        TotalPublico totalPublico = new TotalPublico();
        totalPublico.setYes(votoDao.optionCountForVotacion(v, Option.YES));
        totalPublico.setNo(votoDao.optionCountForVotacion(v, Option.NO));
        totalPublico.setAbs(votoDao.optionCountForVotacion(v, Option.ABS));
        
        // Create DTO
        VotacionDTO votacionDTO = new VotacionDTO();
        votacionDTO.setVotacion(v);
        votacionDTO.setVotosDiputados(votosDiputados);
        votacionDTO.setTotalPublico(totalPublico);
        votacionDTO.setPrincipalAlreadyVoted(userAlreadyVoted(account, v));

        model.addAttribute("votacionDTO", votacionDTO);
  
        return "showVotacion";
        
	}

	/**
	 * 
	 * @param account
	 * @param v
	 * @return
	 */
    private Boolean userAlreadyVoted(Account account, Votacion v) {
        Boolean voted = false;
        if (account != null) {
            Voto voto = votoDao.load(v, account);
            if (voto != null) {
                voted = true;
            }
        }
        return voted;
    }
}
