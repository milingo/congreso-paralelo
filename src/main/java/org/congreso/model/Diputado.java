package org.congreso.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "diputado")
@NamedQueries({
        @NamedQuery(name = Diputado.FIND_BY_NAME, query = "SELECT a FROM Diputado a WHERE a.name = :name"),
        @NamedQuery(name = Diputado.FIND_ALL, query = "SELECT a FROM Diputado a") })
public class Diputado implements java.io.Serializable {

    public static final String FIND_BY_NAME = "Diputado.findByName";
    public static final String FIND_ALL = "Diputado.findAll";

    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String name;
    private String seat;
    private String legislatura;
    private String link;
    private Date birth;
    private String party;

    public Diputado() {
    }

    public Long getId() {
        return id;
    }

    /**
     * @return the link
     */
    public String getLink() {
        return link;
    }

    /**
     * @param link
     *            the link to set
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     * @return the birth
     */
    public Date getBirth() {
        return birth;
    }

    /**
     * @param birth
     *            the birth to set
     */
    public void setBirth(Date birth) {
        this.birth = birth;
    }

    /**
     * @return the party
     */
    public String getParty() {
        return party;
    }

    /**
     * @param party
     *            the party to set
     */
    public void setParty(String party) {
        this.party = party;
    }

    /**
     * @return the legislatura
     */
    public String getLegislatura() {
        return legislatura;
    }

    /**
     * @param legislatura
     *            the legislatura to set
     */
    public void setLegislatura(String legislatura) {
        this.legislatura = legislatura;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the seat
     */
    public String getSeat() {
        return seat;
    }

    /**
     * @param seat
     *            the seat to set
     */
    public void setSeat(String seat) {
        this.seat = seat;
    }

}
