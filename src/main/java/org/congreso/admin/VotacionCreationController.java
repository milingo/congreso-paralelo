package org.congreso.admin;

import javax.validation.Valid;

import org.congreso.dao.EntityDuplicatedException;
import org.congreso.manager.VotacionManager;
import org.congreso.model.Votacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 
 * @author miguel
 * 
 */
@Controller
@RequestMapping(value = "/admin/votaciones")
public class VotacionCreationController {

    @Autowired
    private VotacionManager votacionManager;
    @Autowired
    private VotacionControllerHelper votacionControllerHelper;

    // LESSON: By default, the name/path of the jsp is the same as the one
    // specified on @RequestMapping (/admin/votaciones.jsp)
    @RequestMapping(method = RequestMethod.GET)
    public VotacionForm showVotacion(Model model) {
        model.addAttribute("votaciones", votacionManager.findAll());
        return new VotacionForm();
    }

    /**
     * 
     * @param model
     * @param sesionAdminForm
     * @param errors
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public String createVotacion(Model model,
            @Valid @ModelAttribute VotacionForm votacionForm, Errors errors) {

        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                System.err.println("Error: " + error.getCode() + " - "
                        + error.getDefaultMessage());
            }
            return returnValidationError(model);
        }

        Votacion votacion = new Votacion();
        votacionControllerHelper.fillVotacion(votacionForm, votacion);

        try {
            votacion = votacionManager.create(votacion,
                    votacionControllerHelper.getXmlVotacion(votacionForm));
        } catch (EntityDuplicatedException e1) {
            errors.rejectValue("xmlVotacion", "error.votacion.exists",
                    "Esta votación ya se ha añadido al sistema");
            return returnValidationError(model);
        }

        return "redirect:/admin/votacion/" + votacion.getId();

    }

    /**
     * 
     * @param model
     * @return
     */
    private String returnValidationError(Model model) {
        model.addAttribute("votaciones", votacionManager.findAll());
        return null;
    }

}
