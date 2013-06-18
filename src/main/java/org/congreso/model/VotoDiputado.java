package org.congreso.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "votoDiputado")
@NamedQueries({
    @NamedQuery(name = VotoDiputado.FIND_BY_VOTACION, query = "SELECT v FROM VotoDiputado v WHERE v.votacion.id = :votacionId"),
    @NamedQuery(name = VotoDiputado.FIND_ALL, query = "SELECT a FROM VotoDiputado a") })
public class VotoDiputado implements java.io.Serializable {
    
    public static final String FIND_BY_VOTACION = "VotoDiputado.findByVotacion";
    public static final String FIND_ALL = "VotoDiputado.findAll";

	@Id
	@GeneratedValue
	private Long id;
	@Enumerated(EnumType.STRING)
	private Option voto;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Diputado diputado;
	@ManyToOne(fetch = FetchType.EAGER)
	private Votacion votacion;
     
	
	public VotoDiputado() {
	}

	public Long getId() {
		return id;
	}

    /**
     * @return the diputado
     */
    public Diputado getDiputado() {
        return diputado;
    }

    /**
     * @param diputado the diputado to set
     */
    public void setDiputado(Diputado diputado) {
        this.diputado = diputado;
    }

    /**
     * @return the votacion
     */
    public Votacion getVotacion() {
        return votacion;
    }

    /**
     * @param votacion the votacion to set
     */
    public void setVotacion(Votacion votacion) {
        this.votacion = votacion;
    }

    /**
     * @return the voto
     */
    public Option getVoto() {
        return voto;
    }

    /**
     * @param voto the voto to set
     */
    public void setVoto(Option voto) {
        this.voto = voto;
    }

}
