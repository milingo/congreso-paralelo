package org.congreso.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 
 * @author miguel
 * 
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "challenge")
@NamedQueries({
    //@NamedQuery(name = Challenge.FIND_BY_VOTACION, query = "SELECT c FROM Challenge c WHERE c.votacion.id = :votacionId"),
    @NamedQuery(name = Challenge.FIND_ALL_BY_STATUS, query = "SELECT c FROM Challenge c WHERE c.status = :status"),
    @NamedQuery(name = Challenge.FIND_ALL, query = "SELECT c FROM Challenge c") })
public class Challenge implements java.io.Serializable {
    
    //public static final String FIND_BY_VOTACION = "Challenge.findByVotacion";
    public static final String FIND_ALL_BY_STATUS = "Challenge.findAllByStatus";
    public static final String FIND_ALL = "Challenge.findAll";

    @Id
    @GeneratedValue
    private Long id;
    private Date beginDate;
    private Date endDate;
    @Enumerated(EnumType.STRING)
    private Status status;
//    @OneToOne
//    public Votacion votacion;

    /**
     * @return the beginDate
     */
    public Date getBeginDate() {
        return beginDate;
    }

    /**
     * @param beginDate
     *            the beginDate to set
     */
    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    /**
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate
     *            the endDate to set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

//    /**
//     * @return the votacion
//     */
//    public Votacion getVotacion() {
//        return votacion;
//    }
//
//    /**
//     * @param votacion the votacion to set
//     */
//    public void setVotacion(Votacion votacion) {
//        this.votacion = votacion;
//    }

}
