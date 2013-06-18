package org.congreso.admin;

import java.util.List;

import javax.validation.Valid;

import org.congreso.customer.VoteController;
import org.congreso.dao.VotoDiputadoDao;
import org.congreso.manager.VotacionManager;
import org.congreso.model.Votacion;
import org.congreso.model.VotoDiputado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
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
@RequestMapping(value = "/admin/votacion/{votacionId}")
public class VotacionAdminController extends VoteController {

    @Autowired
    private VotoDiputadoDao voteDao;
    @Autowired
    private VotacionManager votacionManager;
    @Autowired
    private VotacionControllerHelper votacionControllerHelper;

    // LESSON: This includes the VotacionForm as modelAttribute, that can
    // be later referenced on the jsp
    @ModelAttribute("votacionForm")
    public VotacionForm getVotacionFormObject(@PathVariable Long votacionId) {
        Votacion votacion = votacionManager.load(votacionId);
        VotacionForm votacionForm = new VotacionForm();
        votacionControllerHelper.fillVotacionForm(votacionForm, votacion);
        return votacionForm;
    }

    // LESSON: By default, the name/path of the jsp is the same as the one
    // specified on @RequestMapping (/admin/sesion.jsp)
    @RequestMapping(method = RequestMethod.GET)
    public String showVotacion(@PathVariable Long votacionId, Model model) {
        Votacion votacion = votacionManager.load(votacionId);
        model.addAttribute("votacion", votacion);
        List<VotoDiputado> votos = voteDao.findByVotacion(votacion);
        model.addAttribute("votos", votos);
        return "votacion";
    }

    /**
     * 
     * @param model
     * @param sesionAdminForm
     * @param votacionId
     * @param errors
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateVotacion(Model model,
            @Valid @ModelAttribute VotacionForm votacionForm,
            @PathVariable Long votacionId, Errors errors) {

        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                System.err.println("Error: " + error.getCode() + " - "
                        + error.getDefaultMessage());
            }
            return null;
        }

        Votacion votacion = votacionManager.load(votacionId);
        votacionControllerHelper.fillVotacion(votacionForm, votacion);
        votacion = votacionManager.update(votacion,
                votacionControllerHelper.getXmlVotacion(votacionForm));

        return "redirect:/admin/votacion/" + votacion.getId();

    }

    // LESSON: We cannot have 2 methods with the same RequestMapping path (or
    // empty path)
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteVotacion(@PathVariable Long votacionId) {

        Votacion votacion = votacionManager.load(votacionId);
        votacionManager.remove(votacion);

        return "redirect:/admin/votaciones";
    }

}
