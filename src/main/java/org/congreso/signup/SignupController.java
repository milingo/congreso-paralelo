package org.congreso.signup;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import org.congreso.account.*;

@Controller
public class SignupController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "signup")
    public SignupForm signup() {
        return new SignupForm();
    }

    @RequestMapping(value = "signup", method = RequestMethod.POST)
    public String signup(@Valid @ModelAttribute SignupForm signupForm,
            Errors errors) {
        if (errors.hasErrors()) {
            return null;
        }

        try {
            userService.loadUserByUsername(signupForm.getEmail());
        } catch (UsernameNotFoundException e) {
            // OK
            Account account = accountRepository
                    .save(signupForm.createAccount());
            userService.signin(account);
            return "redirect:/";
        }

        // ERROR
        errors.rejectValue("email", "error.account.exists",
                "Usuario ya registrado");
        return null;
    }
}
