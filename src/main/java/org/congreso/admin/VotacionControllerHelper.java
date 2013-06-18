package org.congreso.admin;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.congreso.model.Party;
import org.congreso.model.Votacion;
import org.congreso.xmlvotacion.Resultado;
import org.springframework.stereotype.Service;

/**
 * 
 * @author miguel
 *
 */
@Service
public class VotacionControllerHelper {
    
    public VotacionControllerHelper() {
    }

    /**
     * 
     * @param votacionForm
     * @param votacion
     */
    public void fillVotacionForm(VotacionForm votacionForm, Votacion votacion) {
        votacionForm.setComment(votacion.getCustomComment());
        votacionForm.setLinkSesion(votacion.getLinkSesion());
        votacionForm.setLinkDetalleVotacion(votacion.getLinkDetalleVotacion());
        votacionForm.setLinkExpediente(votacion.getLinkExpediente());
        votacionForm.setTitle(votacion.getTitle());
        List<String> parties = new ArrayList<String>();
        for (Party party : votacion.getParties()) {
            parties.add(party.toString());
        }
        votacionForm.setSelectedParties(parties);
        votacionForm.setVisible(votacion.isVisible());
        votacionForm.setShowVotesDiputados(votacion.isShowVotesDiputados());
        votacionForm.setShowVotesPublic(votacion.isShowVotesPublic());
    }

    /**
     * 
     * @param votacionForm
     * @param votacion
     */
    public void fillVotacion(VotacionForm votacionForm, Votacion votacion) {
        votacion.setLinkSesion(votacionForm.getLinkSesion());
        votacion.setLinkDetalleVotacion(votacionForm.getLinkDetalleVotacion());
        votacion.setLinkExpediente(votacionForm.getLinkExpediente());
        votacion.setTitle(votacionForm.getTitle());
        votacion.setCustomComment(votacionForm.getComment());
        List<Party> parties = new ArrayList<Party>();
        for (String selectedParty : votacionForm.getSelectedParties()) {
            parties.add(Party.valueOf(selectedParty));
        }
        votacion.setParties(parties);
        votacion.setVisible(votacionForm.isVisible());
        votacion.setShowVotesDiputados(votacionForm.isShowVotesDiputados());
        votacion.setShowVotesPublic(votacionForm.isShowVotesPublic());
    }

    
    public Resultado getXmlVotacion(VotacionForm votacionForm) {

        // If we have xml
        Resultado resultadoFromXml = null;
        if (!votacionForm.getXmlVotacion().isEmpty()) {
            try {
                resultadoFromXml = unmarshallXml(votacionForm
                        .getXmlVotacion().getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultadoFromXml;
        
    }
    
    /**
     * 
     * @param xmlFile
     * @return
     */
    private Resultado unmarshallXml(InputStream xmlFile) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Resultado.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Resultado resultadoVotacion = (Resultado) jaxbUnmarshaller
                    .unmarshal(xmlFile);
            return resultadoVotacion;
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return null;
    }
    
}
