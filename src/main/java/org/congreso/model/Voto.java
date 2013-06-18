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

import org.congreso.account.Account;

@SuppressWarnings("serial")
@Entity
@Table(name = "voto")
@NamedQueries({
    @NamedQuery(name = Voto.COUNT_BY_OPTION, query = "SELECT count(v) FROM Voto v WHERE v.votacion.id = :votacionId and v.voto = :opcion"),
    @NamedQuery(name = Voto.FIND_BY_VOTACION, query = "SELECT v FROM Voto v WHERE v.votacion.id = :votacionId"),
    @NamedQuery(name = Voto.FIND_BY_VOTACION_AND_ACCOUNT, query = "SELECT v FROM Voto v WHERE v.votacion.id = :votacionId AND v.account.id = :accountId"),
    @NamedQuery(name = Voto.FIND_ALL, query = "SELECT a FROM Voto a") })
public class Voto implements java.io.Serializable {
    
    public static final String FIND_BY_VOTACION = "Voto.findByVotacion";
    public static final String FIND_BY_VOTACION_AND_ACCOUNT = "Voto.findByVotacionAndAccount";
    public static final String FIND_ALL = "Voto.findAll";
    public static final String COUNT_BY_OPTION = "Voto.CountByOption";

	@Id
	@GeneratedValue
	private Long id;
	@Enumerated(EnumType.STRING)
	private Option voto;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Account account;
	@ManyToOne(fetch = FetchType.EAGER)
	private Votacion votacion;
     
	
	public Voto() {
	}

	public Long getId() {
		return id;
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

    /**
     * @return the account
     */
    public Account getAccount() {
        return account;
    }

    /**
     * @param account the account to set
     */
    public void setAccount(Account account) {
        this.account = account;
    }

}
