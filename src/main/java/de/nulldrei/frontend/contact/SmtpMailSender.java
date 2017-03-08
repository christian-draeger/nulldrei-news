package de.nulldrei.frontend.contact;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.web.util.HtmlUtils;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;

@Component
public class SmtpMailSender {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${recipient.mail}")
    private String recipient;

    void send(String from, String message) throws MailException {
        String trimmedFrom = StringUtils.trim(from);
        EmailValidator emailValidator = EmailValidator.getInstance(false);
        if (!emailValidator.isValid(trimmedFrom)) {
            throw new InvalidEmailException(trimmedFrom);
        }
        String escapedMessage = HtmlUtils.htmlEscape(message);

        SendMailCommand sendMailCommand = new SendMailCommand(recipient, trimmedFrom, escapedMessage, javaMailSender);
        sendMailCommand.execute();
        if (sendMailCommand.isFailedExecution()) {
            throw new UnableToSendEmailException();
        }
    }

    static class SendMailCommand extends HystrixCommand<String> {

        private String recipient;
        private String from;
        private String message;
        private JavaMailSender javaMailSender;

        SendMailCommand(String recipient, String from, String message, JavaMailSender javaMailSender) {
            super(HystrixCommand.Setter
                    .withGroupKey(HystrixCommandGroupKey.Factory.asKey("feedback"))
                    .andCommandKey(HystrixCommandKey.Factory.asKey("send")));
            this.recipient = recipient;
            this.from = from;
            this.message = message;
            this.javaMailSender = javaMailSender;
        }

        @Override
        protected String run() throws Exception {
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setTo(recipient);
            mail.setFrom(from);
            mail.setSubject("WidgetsStore Request von " + from);
            mail.setText(message);

            javaMailSender.send(mail);
            return "success";
        }

        @Override
        protected String getFallback() {
            return "fail";
        }
    }
}
