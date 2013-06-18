package org.congreso.xmlvotacion;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Totales")
@XmlAccessorType(XmlAccessType.FIELD)
@Embeddable
public class Totales implements java.io.Serializable {

    private static final long serialVersionUID = 8634423839670008L;
    
    @Column(nullable = true)
    @XmlElement(name = "Asentimiento")
    private String asentimiento;
    
    @Column(nullable = true)
    @XmlElement(name = "Presentes")
	private int presentes;
    
    @Column(nullable = true)
    @XmlElement(name = "AFavor")
	private int aFavor;
    
    @Column(nullable = true)
    @XmlElement(name = "EnContra")
	private int enContra;
    
    @Column(nullable = true)
    @XmlElement(name = "Abstenciones")
	private int abstenciones;
    
    @Column(nullable = true)
    @XmlElement(name = "NoVotan")
	private int noVotan;

    /**
     * @return the asentimiento
     */
    public String getAsentimiento() {
        return asentimiento;
    }

    /**
     * @param asentimiento the asentimiento to set
     */
    public void setAsentimiento(String asentimiento) {
        this.asentimiento = asentimiento;
    }

    /**
     * @return the presentes
     */
    public int getPresentes() {
        return presentes;
    }

    /**
     * @param presentes the presentes to set
     */
    public void setPresentes(int presentes) {
        this.presentes = presentes;
    }

    /**
     * @return the aFavor
     */
    public int getaFavor() {
        return aFavor;
    }

    /**
     * @param aFavor the aFavor to set
     */
    public void setaFavor(int aFavor) {
        this.aFavor = aFavor;
    }

    /**
     * @return the enContra
     */
    public int getEnContra() {
        return enContra;
    }

    /**
     * @param enContra the enContra to set
     */
    public void setEnContra(int enContra) {
        this.enContra = enContra;
    }

    /**
     * @return the abstenciones
     */
    public int getAbstenciones() {
        return abstenciones;
    }

    /**
     * @param abstenciones the abstenciones to set
     */
    public void setAbstenciones(int abstenciones) {
        this.abstenciones = abstenciones;
    }

    /**
     * @return the noVotan
     */
    public int getNoVotan() {
        return noVotan;
    }

    /**
     * @param noVotan the noVotan to set
     */
    public void setNoVotan(int noVotan) {
        this.noVotan = noVotan;
    }


}