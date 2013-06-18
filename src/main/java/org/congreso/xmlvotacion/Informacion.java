package org.congreso.xmlvotacion;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name = "Informacion")
@XmlAccessorType(XmlAccessType.FIELD)
public class Informacion implements java.io.Serializable {

    private static final long serialVersionUID = 8636221583839670008L;
    
    @XmlElement(name = "Sesion")
    private String sesion;
    
    @XmlElement(name = "NumeroVotacion")
	private int numeroVotacion;
    
    @XmlElement(name = "Fecha")
    @XmlJavaTypeAdapter(DateAdapter.class)
	private Date fecha;
    
    @XmlElement(name = "Titulo")
	private String titulo;
    
    @XmlElement(name = "TextoExpediente")
	private String textoExpediente;
    
    @XmlElement(name = "TituloSubGrupo")
	private String tituloSubGrupo;
    
    @XmlElement(name = "TextoSubGrupo")
	private String textoSubGrupo;
	
    /**
     * 
     */
	protected Informacion() {
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
     * @return the numeroVotacion
     */
    public int getNumeroVotacion() {
        return numeroVotacion;
    }

    /**
     * @param numeroVotacion the numeroVotacion to set
     */
    public void setNumeroVotacion(int numeroVotacion) {
        this.numeroVotacion = numeroVotacion;
    }

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
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
     * @return the tituloSubGrupo
     */
    public String getTituloSubGrupo() {
        return tituloSubGrupo;
    }

    /**
     * @param tituloSubGrupo the tituloSubGrupo to set
     */
    public void setTituloSubGrupo(String tituloSubGrupo) {
        this.tituloSubGrupo = tituloSubGrupo;
    }

    /**
     * @return the textoSubGrupo
     */
    public String getTextoSubGrupo() {
        return textoSubGrupo;
    }

    /**
     * @param textoSubGrupo the textoSubGrupo to set
     */
    public void setTextoSubGrupo(String textoSubGrupo) {
        this.textoSubGrupo = textoSubGrupo;
    }

}