package com.project.cms.user.service.test;

import com.project.cms.user.client.MailgunClient;
import com.project.cms.user.client.mailgun.SendMailForm;
import feign.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailSendService {

    private final MailgunClient mailgunClient;

    public Response sendEmail() {

        SendMailForm form = SendMailForm.builder()
            .from("test@my.com")
            .to("test@your.com")
            .subject("test mail")
            .text("text")
            .build();

        return mailgunClient.sendEmail(form).getBody();
    }

}
