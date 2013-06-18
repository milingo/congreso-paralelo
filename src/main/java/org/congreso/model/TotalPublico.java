package org.congreso.model;

/**
 * 
 * @author miguel
 * 
 */
@SuppressWarnings("serial")
public class TotalPublico implements java.io.Serializable {

    private Long total;
    private Long yes;
    private Long no;
    private Long abs;

    public TotalPublico() {
    }

    /**
     * @return the asentimiento
     */
    public String getAsentimiento() {
        if (getYes() > getNo()) {
            return "Si";
        }
        if (getNo() > getYes()) {
            return "No";
        }
        return "Empate";
    }

    /**
     * @return the total
     */
    public Long getTotal() {
        return total;
    }

    /**
     * @param total
     *            the total to set
     */
    public void setTotal(Long total) {
        this.total = total;
    }

    /**
     * @return the yes
     */
    public Long getYes() {
        return yes;
    }

    /**
     * @param yes
     *            the yes to set
     */
    public void setYes(Long yes) {
        this.yes = yes;
    }

    /**
     * @return the no
     */
    public Long getNo() {
        return no;
    }

    /**
     * @param no
     *            the no to set
     */
    public void setNo(Long no) {
        this.no = no;
    }

    /**
     * @return the abs
     */
    public Long getAbs() {
        return abs;
    }

    /**
     * @param abs
     *            the abs to set
     */
    public void setAbs(Long abs) {
        this.abs = abs;
    }

}
