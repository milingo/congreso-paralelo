package org.congreso.dao;


public class EntityDuplicatedException extends RuntimeException {

    public EntityDuplicatedException() {
        super();
    }
    
    public EntityDuplicatedException(Exception e) {
        super(e);
    }

    /**
     * 
     */
    private static final long serialVersionUID = 6208333023453884L;

}
