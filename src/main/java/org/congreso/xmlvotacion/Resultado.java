package org.congreso.xmlvotacion;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Resultado")
@XmlAccessorType(XmlAccessType.FIELD)
public class Resultado implements java.io.Serializable {

    private static final long serialVersionUID = 22423839670008L;
    
    @XmlElement(name = "Informacion")
    private Informacion informacion;
    
    @XmlElement(name = "Totales")
	private Totales totales;
    
    @XmlElementWrapper(name="Votaciones")
    @XmlElement(name = "Votacion")
	private List<Votacion> votaciones;

    /**
     * @return the informacion
     */
    public Informacion getInformacion() {
        return informacion;
    }

    /**
     * @param informacion the informacion to set
     */
    public void setInformacion(Informacion informacion) {
        this.informacion = informacion;
    }

    /**
     * @return the totales
     */
    public Totales getTotales() {
        return totales;
    }

    /**
     * @param totales the totales to set
     */
    public void setTotales(Totales totales) {
        this.totales = totales;
    }

    /**
     * @return the votaciones
     */
    public List<Votacion> getVotaciones() {
        return votaciones;
    }

    /**
     * @param votaciones the votaciones to set
     */
    public void setVotaciones(List<Votacion> votaciones) {
        this.votaciones = votaciones;
    }

    public org.congreso.model.Votacion fillVotacionModel(org.congreso.model.Votacion votacion) {
        votacion.setSesion(getInformacion().getSesion());
        votacion.setNumber(Integer.valueOf(getInformacion().getNumeroVotacion()));
        votacion.setDate(getInformacion().getFecha());
        votacion.setTitulo(getInformacion().getTitulo());
        votacion.setTextoExpediente(getInformacion().getTextoExpediente());
        votacion.setGroupTitle(getInformacion().getTituloSubGrupo());
        votacion.setGroupDescription(getInformacion().getTextoSubGrupo());
        votacion.setVotes(getTotales());
        return votacion;
    }
   

}