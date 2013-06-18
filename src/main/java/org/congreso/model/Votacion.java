package org.congreso.model;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.congreso.xmlvotacion.Totales;

@SuppressWarnings("serial")
@Entity
@Table(name = "votacion")
//@Table(name = "votacion", uniqueConstraints=@UniqueConstraint(columnNames = {"legislatura", "sesion", "number"}) )
@NamedQueries({
    @NamedQuery(name = Votacion.FIND_ALL, query = "SELECT v FROM Votacion v"),
    @NamedQuery(name = Votacion.FIND_ALL_VISIBLE, query = "SELECT v FROM Votacion v WHERE v.visible = true"),
    @NamedQuery(name = Votacion.FIND_ALL_BY_CHALLENGE_STATUS, query = "SELECT v FROM Votacion v WHERE v.challenge.status = status")})
public class Votacion implements java.io.Serializable {
    
    public static final String FIND_ALL = "Votacion.findAll";
    public static final String FIND_ALL_VISIBLE = "Votacion.findVisible";
    public static final String FIND_ALL_BY_CHALLENGE_STATUS = "Votacion.findAllByChallengeStatus";

	@Id
	@GeneratedValue
	private Long id;
	
	// from xml
	@Embedded
	private Totales votes;
	private String sesion;
	private Integer number;
	private Date date;
	private String titulo;
	private String textoExpediente;
	private String groupTitle;
	private String groupDescription;
	
	// custom
	private String legislatura;
	private String customComment;
	private String linkSesion;
	private String linkDetalleVotacion;
	private String linkExpediente;
	private String title;
	@ElementCollection(targetClass = Party.class, fetch = FetchType.EAGER)
	@JoinTable(name = "party", joinColumns = @JoinColumn(name = "id"))
	@Column(name = "party_id", nullable = false)
	@Enumerated(EnumType.STRING)
	private List<Party> parties;
	
	// visibility
	private boolean visible;
	private boolean showVotesDiputados;
	private boolean showVotesPublic;
	
	// relationships
	@OneToOne
	private Challenge challenge;
     
	
	public Votacion() {
	}

	public Long getId() {
		return id;
	}

    /**
     * @return the votes
     */
    public Totales getVotes() {
        return votes;
    }

    /**
     * @param votes the votes to set
     */
    public void setVotes(Totales votes) {
        this.votes = votes;
    }

    /**
     * @return the sesion
     */
    public String getSesion() {
        return sesion;
    }

    /**
     * @param sesion the sesion to set
     */
    public void setSesion(String sesion) {
        this.sesion = sesion;
    }

    /**
     * @return the number
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * @param number the number to set
     */
    public void setNumber(Integer number) {
        this.number = number;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
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
     * @return the groupTitle
     */
    public String getGroupTitle() {
        return groupTitle;
    }

    /**
     * @param groupTitle the groupTitle to set
     */
    public void setGroupTitle(String groupTitle) {
        this.groupTitle = groupTitle;
    }

    /**
     * @return the groupDescription
     */
    public String getGroupDescription() {
        return groupDescription;
    }

    /**
     * @param groupDescription the groupDescription to set
     */
    public void setGroupDescription(String groupDescription) {
        this.groupDescription = groupDescription;
    }

    /**
     * @return the legislatura
     */
    public String getLegislatura() {
        return legislatura;
    }

    /**
     * @param legislatura the legislatura to set
     */
    public void setLegislatura(String legislatura) {
        this.legislatura = legislatura;
    }

    /**
     * @return the customComment
     */
    public String getCustomComment() {
        return customComment;
    }

    /**
     * @param customComment the customComment to set
     */
    public void setCustomComment(String customComment) {
        this.customComment = customComment;
    }

    /**
     * @return the challenge
     */
    public Challenge getChallenge() {
        return challenge;
    }

    /**
     * @param challenge the challenge to set
     */
    public void setChallenge(Challenge challenge) {
        this.challenge = challenge;
    }

    /**
     * @return the showVotesDiputados
     */
    public boolean isShowVotesDiputados() {
        return showVotesDiputados;
    }

    /**
     * @param showVotesDiputados the showVotesDiputados to set
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
     * @param showVotesPublic the showVotesPublic to set
     */
    public void setShowVotesPublic(boolean showVotesPublic) {
        this.showVotesPublic = showVotesPublic;
    }

    /**
     * @return the visible
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * @param visible the visible to set
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the textoExpediente
     */
    public String getTextoExpediente() {
        return textoExpediente;
    }

    /**
     * @param textoExpediente the textoExpediente to set
     */
    public void setTextoExpediente(String textoExpediente) {
        this.textoExpediente = textoExpediente;
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
     * @return the parties
     */
    public List<Party> getParties() {
        return parties;
    }

    /**
     * @param parties the parties to set
     */
    public void setParties(List<Party> parties) {
        this.parties = parties;
    }


}
