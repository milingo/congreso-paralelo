package org.congreso.customer;

import org.congreso.model.Option;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 
 * @author miguel
 * 
 */
public class VoteForm {

    public static final String NOT_BLANK_MESSAGE = "{error.notBlank}";

    // @NotBlank(message = VoteForm.NOT_BLANK_MESSAGE)
    // private String name;
    // @NotBlank(message = VoteForm.NOT_BLANK_MESSAGE)
    // private String surname;
    // @NotBlank(message = VoteForm.NOT_BLANK_MESSAGE)
    // private String email;
    // @NotBlank(message = VoteForm.NOT_BLANK_MESSAGE)
    // private String postalCode;
    private Option option;

    /**
     * This is for the Form.
     * 
     * @return the vote
     */
    public String getVote() {
        if (option != null) {
            return option.toString().toLowerCase();   
        } else {
            return "";
        }
    }

    /**
     * This is for the Form.
     * 
     * @param vote
     *            the vote to set
     */
    public void setVote(String vote) {
        this.option = Option.valueOf(vote.toUpperCase());
    }

    /**
     * This is for the Model.
     * 
     * @return the option
     */
    public Option getOption() {
        return option;
    }

    /**
     * This is for the Model.
     * 
     * @param option
     *            the option to set
     */
    public void setOption(Option option) {
        this.option = option;
    }

}
