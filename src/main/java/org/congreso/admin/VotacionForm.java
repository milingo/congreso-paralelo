package org.congreso.admin;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class VotacionForm {

    public static final String NOT_BLANK_MESSAGE = "{error.notBlank}";

    private String linkSesion;
    private String linkDetalleVotacion;
    private String linkExpediente;
    @NotBlank(message = VotacionForm.NOT_BLANK_MESSAGE)
    private String title;
    @NotBlank(message = VotacionForm.NOT_BLANK_MESSAGE)
    private String comment;
    private List<String> selectedParties;

    private CommonsMultipartFile xmlVotacion;

    // LESSON: lo que determina si un checkbox esta seleccionado es el atributo checked (checked="" es falso, checked="lo que sea" es true)
    // visibility
    private boolean visible;
    private boolean showVotesDiputados;
    private boolean showVotesPublic;

    /**
     * @return the xmlVotacion
     */
    public CommonsMultipartFile getXmlVotacion() {
        return xmlVotacion;
    }

    /**
     * @param xmlVotacion
     *            the xmlVotacion to set
     */
    public void setXmlVotacion(CommonsMultipartFile xmlVotacion) {
        this.xmlVotacion = xmlVotacion;
    }

    /**
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment
     *            the comment to set
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * @return the visible
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * @param visible
     *            the visible to set
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    /**
     * @return the showVotesDiputados
     */
    public boolean isShowVotesDiputados() {
        return showVotesDiputados;
    }

    /**
     * @param showVotesDiputados
     *            the showVotesDiputados to set
     */
    public void setShowVotesDiputados(boolean showVotesDiputados) {
        this.showVotesDiputados = showVotesDiputados;
    }

    /**
     * @return the showVotesPublic
     */
    public boolean isShowVotesPublic() {
        return showVotesPublic;
    }

    /**
     * @param showVotesPublic
     *            the showVotesPublic to set
     */
    public void setShowVotesPublic(boolean showVotesPublic) {
        this.showVotesPublic = showVotesPublic;
    }

    /**
     * @return the linkExpediente
     */
    public String getLinkExpediente() {
        return linkExpediente;
    }

    /**
     * @param linkExpediente the linkExpediente to set
     */
    public void setLinkExpediente(String linkExpediente) {
        this.linkExpediente = linkExpediente;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the linkDetalleVotacion
     */
    public String getLinkDetalleVotacion() {
        return linkDetalleVotacion;
    }

    /**
     * @param linkDetalleVotacion the linkDetalleVotacion to set
     */
    public void setLinkDetalleVotacion(String linkDetalleVotacion) {
        this.linkDetalleVotacion = linkDetalleVotacion;
    }

    /**
     * @return the linkSesion
     */
    public String getLinkSesion() {
        return linkSesion;
    }

    /**
     * @param linkSesion the linkSesion to set
     */
    public void setLinkSesion(String linkSesion) {
        this.linkSesion = linkSesion;
    }

    /**
     * @return the selectedParties
     */
    public List<String> getSelectedParties() {
        return selectedParties;
    }

    /**
     * @param selectedParties the selectedParties to set
     */
    public void setSelectedParties(List<String> selectedParties) {
        this.selectedParties = selectedParties;
    }

}
