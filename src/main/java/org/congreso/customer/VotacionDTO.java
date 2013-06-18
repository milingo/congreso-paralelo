package org.congreso.customer;

import java.util.List;

import org.congreso.model.TotalPublico;
import org.congreso.model.Votacion;
import org.congreso.model.VotoDiputado;

/**
 * 
 * @author miguel
 * 
 */
public class VotacionDTO {

    private Votacion votacion;
    private List<VotoDiputado> votosDiputados;
    private TotalPublico totalPublico;
    private Boolean principalAlreadyVoted;
    // private List<Voto> votosPublico;

    /**
     * @return the votacion
     */
    public Votacion getVotacion() {
        return votacion;
    }

    /**
     * @param votacion
     *            the votacion to set
     */
    public void setVotacion(Votacion votacion) {
        this.votacion = votacion;
    }

    /**
     * @return the votosDiputados
     */
    public List<VotoDiputado> getVotosDiputados() {
        return votosDiputados;
    }

    /**
     * @param votosDiputados
     *            the votosDiputados to set
     */
    public void setVotosDiputados(List<VotoDiputado> votosDiputados) {
        this.votosDiputados = votosDiputados;
    }

    /**
     * @return the totalPublico
     */
    public TotalPublico getTotalPublico() {
        return totalPublico;
    }

    /**
     * @param totalPublico the totalPublico to set
     */
    public void setTotalPublico(TotalPublico totalPublico) {
        this.totalPublico = totalPublico;
    }

    /**
     * @return the principalAlreadyVoted
     */
    public Boolean getPrincipalAlreadyVoted() {
        return principalAlreadyVoted;
    }

    /**
     * @param principalAlreadyVoted the principalAlreadyVoted to set
     */
    public void setPrincipalAlreadyVoted(Boolean principalAlreadyVoted) {
        this.principalAlreadyVoted = principalAlreadyVoted;
    }


}
