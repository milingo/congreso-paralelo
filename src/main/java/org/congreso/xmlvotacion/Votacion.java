package org.congreso.xmlvotacion;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.congreso.model.Option;

@XmlRootElement(name = "Votacion")
@XmlAccessorType(XmlAccessType.FIELD)
public class Votacion implements java.io.Serializable {

    private static final long serialVersionUID = 86364232345608L;
    
    @XmlElement(name = "Asiento")
    private String asiento;
    
    @XmlElement(name = "Diputado")
	private String diputado;
    
    @XmlElement(name = "Voto")
	private String voto;

    /**
     * @return the asiento
     */
    public String getAsiento() {
        return asiento;
    }

    /**
     * @param asiento the asiento to set
     */
    public void setAsiento(String asiento) {
        this.asiento = asiento;
    }

    /**
     * @return the diputado
     */
    public String getDiputado() {
        return diputado;
    }

    /**
     * @param diputado the diputado to set
     */
    public void setDiputado(String diputado) {
        this.diputado = diputado;
    }

    /**
     * @return the voto
     */
    public String getVoto() {
        return voto;
    }

    /**
     * @param voto the voto to set
     */
    public void setVoto(String voto) {
        this.voto = voto;
    }

    /**
     * 
     * @param voto
     * @return
     */
    public Option getOption() {
        if (voto.equalsIgnoreCase("si") || voto.equalsIgnoreCase("sí")) {
            return Option.YES;
        } else if (voto.equalsIgnoreCase("no")) {
           return Option.NO; 
        } else if (voto.equalsIgnoreCase("abstencion") || voto.equalsIgnoreCase("abstención") ) {
            return Option.ABS;
        } else {
            return Option.OUT;
        }
    }

}