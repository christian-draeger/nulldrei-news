package de.nulldrei.frontend.contact;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.mkopylec.recaptcha.validation.RecaptchaValidator;
import com.github.mkopylec.recaptcha.validation.ValidationResult;

@RestController
public class ContactController {

    @Autowired
    private SmtpMailSender mail;
    @Autowired
    private RecaptchaValidator recaptchaValidator;

    @PostMapping("/feedback")
    public void sendFeedback(@RequestParam String from, @RequestParam String message, HttpServletRequest request) {
        ValidationResult validationResult = recaptchaValidator.validate(request);
        if (!validationResult.isSuccess()) {
            throw new CaptchaValidationException();
        }

        mail.send(from, message);
    }
}
